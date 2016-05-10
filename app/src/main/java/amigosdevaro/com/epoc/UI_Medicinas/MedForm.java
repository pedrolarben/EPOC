package amigosdevaro.com.epoc.UI_Medicinas;


import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import amigosdevaro.com.epoc.tipos.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;


import amigosdevaro.com.epoc.DB_SQLite.EpocDB;
import amigosdevaro.com.epoc.tipos.DiasSemana;
import amigosdevaro.com.epoc.R;
import amigosdevaro.com.epoc.tipos.AdministracionFarmaco;

/**
 * Created by belladona on 26/4/16.
 */
public class MedForm extends AppCompatActivity {

    //Fields de la clase:
    private Calendar calendar;




    //Datos recogidos del formulario

    //Para crear un Farmaco se necesita esta info:
    private String nombreMedicamento;
    private TipoFarmaco tipoFarmaco = null;
    private AdministracionFarmaco administracionFarmaco = null;

    //Posologia:
    private Set<DiasSemana> Semana = new HashSet<DiasSemana>();
    int dosisCada = -1;
    private GregorianCalendar primeraDosis = new GregorianCalendar();

    //Objetos que crear con estos campos luego:
    Posologia posologia;
    Farmaco farmaco;

    int accentColor;


    /*Lo que es obligatorio rellenar es: Dias, tipoFarmaco, administracionFarmaco, primeraDosis*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medoform);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Flechita
        //Barra Arriba
        getSupportActionBar().setTitle("Mis medicamentos");

        //Añadiendo flechita atras:
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        //Cambiar el background de los botones a uno mas cool:

        accentColor = ContextCompat.getColor(this, R.color.colorAccent);

        Button btn1 = (Button) findViewById(R.id.btn_selecthour);
        Button btn2 = (Button) findViewById(R.id.btn_selectWeek);
        Button btn3 = (Button) findViewById(R.id.btn_save);
        Button btn4 = (Button) findViewById(R.id.btn_cancel);

        btn1.setBackgroundResource(android.R.drawable.btn_default);
        btn2.setBackgroundResource(android.R.drawable.btn_default);
        btn3.setBackgroundResource(android.R.drawable.btn_default);
        btn4.setBackgroundResource(android.R.drawable.btn_default);



        //Cambiando titulo toolbar:
        //TODO: pensar que poner
        getSupportActionBar().setTitle("Mi Medicina");

        //Form buttons:

        Button save = (Button) findViewById(R.id.btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast message = Toast.makeText(getApplicationContext(),"Los campos de Tipo y Administración del Farmaco deben rellenarse.",Toast.LENGTH_LONG);
                //Se comprueba que el formulario se relleno al completo antes de guardar los datos.



                if(tipoFarmaco == null || administracionFarmaco==null|| tipoFarmaco == TipoFarmaco._TOFIX_ || administracionFarmaco==AdministracionFarmaco._TOFIX_){
                    message.show();
                }

               else if(Semana.isEmpty()){
                    message.setText("Elige al menos un Dia de la Semana.");
                    message.show();
                }

                 else{
                    //Cojo los datos del nombre del medicamento del campo de texto:
                    EditText nombreMed = (EditText) findViewById(R.id.nombre_farmaco);
                    nombreMedicamento = nombreMed.getText().toString().trim();

                    //Por defecto le doy el nombre del tipo de farmaco en general.
                    if(nombreMedicamento.isEmpty()){
                        //Por defecto le doy el nombre del tipo de farmaco.
                        nombreMedicamento = tipoFarmaco.toString();
                    }


                    //Compruebo que en la base de datos no existe un medicamento con el mismo nombre:

                    int repetido = 1;
                    for(Farmaco x: EpocDB.getFarmacos()){

                        //Si existe le cambio el nombre:
                        if(x.getNombre().equals(nombreMedicamento)){
                            repetido++;
                            if(repetido>2){
                                nombreMedicamento = nombreMedicamento.substring(0,nombreMedicamento.length()-3);
                            }
                            nombreMedicamento += "("+repetido+")";
                        }
                    }

                    //TODO: Preguntar a varo si el ya hace algo cuando la dosis es cada -1 IMPORTANTE.



                    posologia = new PosologiaImpl(Semana,dosisCada,primeraDosis,administracionFarmaco);
                    farmaco = new FarmacoImpl(nombreMedicamento,tipoFarmaco,posologia);


                    //Save new data
                    EpocDB.addFarmaco(farmaco);

                    //EpocDB.addFarmacoTomado(farmaco,new GregorianCalendar() );//BORRAR

                    //Goes back to main activity
                    finish();
                    startActivity(new Intent(MedForm.this, DisplayMeds.class));
                }


            }
        });


        Button cancel = (Button) findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //En este caso no se guarda nada.
                //Goes back to main activity
                finish();
                startActivity(new Intent(MedForm.this, DisplayMeds.class));

            }
        });


        //Managing spinners:
        //Adapter: adapta los strings a vistas:

        //Tipo spinner

        Spinner tipo = (Spinner) findViewById(R.id.spinner_tipo);

        //Adapter con los datos:
        final ArrayAdapter tipoAdapter = ArrayAdapter.createFromResource(this, R.array.tipo_farmaco, android.R.layout.simple_list_item_checked);
        tipoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipo.setAdapter(new NothingSelectedSpinnerAdapter(tipoAdapter, R.layout.nadaseleccionado_tipofarmaco, this));

        //Listener:
        tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*He puesto que en la lista desplegable los datos se muestren en el mismo orden que
                * en la clase enum para que sea más fácil. Pero entonces tened en cuenta que EL ORDEN
                * IMPORTA, NO CAMBIARLO*/

                tipoFarmaco = TipoFarmaco.values()[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tipoFarmaco = null;
            }
        });


        //Administracion spinner

        Spinner administracion = (Spinner) findViewById(R.id.spinner_administracion);

        //Adapter con los datos:
        ArrayAdapter adminAdapter = ArrayAdapter.createFromResource(this, R.array.administracion, android.R.layout.simple_list_item_checked);
        adminAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        administracion.setAdapter(new NothingSelectedSpinnerAdapter(adminAdapter, R.layout.nadaseleccionado_administracion, this));

        //Listener:

        administracion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*He puesto que en la lista desplegable los datos se muestren en el mismo orden que
                * en la clase enum para que sea más fácil. Pero entonces tened en cuenta que EL ORDEN
                * IMPORTA, NO CAMBIARLO*/

                administracionFarmaco = AdministracionFarmaco.values()[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                administracionFarmaco = null;
            }
        });
        //Dosiscada spinner
        Spinner dosiscada = (Spinner) findViewById(R.id.spinner_dosiscada);

        //Adapter con los datos:
        ArrayAdapter dcadaAdapter = ArrayAdapter.createFromResource(this, R.array.horas, android.R.layout.simple_list_item_checked);
        dcadaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dosiscada.setAdapter(new NothingSelectedSpinnerAdapter(dcadaAdapter, R.layout.nadaseleccionado_dosiscada, this));

        //Listener:

        dosiscada.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Posicion porque vamos del 1 al 12

                dosisCada = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                dosisCada = -1;
            }
        });



    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {

        if(keyCode == KeyEvent.KEYCODE_BACK)
        {

            Intent intent = new Intent(MedForm.this, DisplayMeds.class);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            finish();
            startActivity(intent);
            return true;
        }
        else{
            return super.onKeyDown(keyCode,event);
        }

    }

    //Metodo onCLick del boton 1 dosis
    //TODO: probar que esto funciona correctamente.
    public void selectHora(View view) {
        //Cojo el tiempo actual.
        calendar = Calendar.getInstance();

        final Button pDosis = (Button) findViewById(R.id.btn_selecthour);

        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker vista, int hora, int minuto) {
                //Aquí llega la hora y minutos seleccionados en la vista.
                //Los guardo con un GregorianCalendar, el año mes y dia de semana lo saco del tiempo actual.
                primeraDosis = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_WEEK), hora, minuto);

                pDosis.setBackgroundColor(accentColor);

            }
        };

        TimePickerDialog horas = new TimePickerDialog(this, listener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        horas.setTitle("¿A qué hora fue la primera dosis?");
        horas.setButton(DialogInterface.BUTTON_POSITIVE, "Listo", horas);
        horas.show();
    }


    //Metodo onClick del boton repetir
    public void selectDiaSemana(View view) {

        //Por si se llama mas de una vez nos conviene limpiar el set de dias cada vez,
        //Para que luego funcionen los checkers.
        Semana.clear();
        final String[] dias = getResources().getStringArray(R.array.dias);

        //boton que cambiar color:
        final Button repetir = (Button) findViewById(R.id.btn_selectWeek);

        //Me lo tuve que inventar sino al clicar dos veces se caia la app.
        final boolean[] waschecked = new boolean[dias.length];

        for (boolean x : waschecked) {
            x = false;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Cuándo debe repetirse la alarma?");
        builder.setMultiChoiceItems(R.array.dias, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                if (waschecked[which]) {
                    isChecked = false;
                    waschecked[which] = false;
                } else {
                    waschecked[which] = true;
                }

            }
        }).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Lo que hace el boton de aceptar

                //En el caso de que se acepte cojo los datos
                //Me fijo en los valores del array de booleanos:
                int count=0;
                for(int i = 0; i<waschecked.length;i++){
                    if(waschecked[i]){
                        Semana.add(DiasSemana.values()[i]);
                       count++;
                    }
                }
                if(count!=0){
                repetir.setBackgroundColor(accentColor);} else {
                    repetir.setBackgroundResource(android.R.drawable.btn_default);
                }


            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Lo que hace el boton de cancelar
                repetir.setBackgroundResource(android.R.drawable.btn_default);

            }
        });




        builder.create();
        builder.show();
    }

}

