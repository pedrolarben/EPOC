package amigosdevaro.com.epoc.UI_Medicinas;


import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import amigosdevaro.com.epoc.tipos.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import amigosdevaro.com.epoc.DB_SQLite.EpocDB;
import amigosdevaro.com.epoc.MainActivity;
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
    int dosisCada = 24;
    private GregorianCalendar primeraDosis;

    //Objetos que crear con estos campos luego:
    Posologia posologia;
    Farmaco farmaco;


    /*Lo que es obligatorio rellenar es: Dias, tipoFarmaco, administracionFarmaco, primeraDosis*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medoform);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Form buttons:

        Button save = (Button) findViewById(R.id.btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //TODO: comprobar que el formulario se relleno al completo antes de guardar los datos.

                boolean comprobacion;

                if(Semana.isEmpty()){
                    comprobacion = false;
                }

                if(true){
                    //Cojo los datos del nombre del medicamento del campo de texto:
                    EditText nombreMed = (EditText) findViewById(R.id.nombre_farmaco);
                    nombreMedicamento = nombreMed.getText().toString();

                    //TODO: tratar si el nombre esta empty

                    //Por defecto le doy el nombre del tipo de farmaco en general.
                    if(nombreMedicamento.isEmpty()){
                        //Por defecto le doy el nombre del tipo de farmaco.
                        nombreMedicamento = tipoFarmaco.toString();
                    }
                    posologia = new PosologiaImpl(Semana,dosisCada,primeraDosis,administracionFarmaco);
                    farmaco = new FarmacoImpl(nombreMedicamento,tipoFarmaco,posologia);


                    //Save new data
                    //TODO: salvar en base de datos.


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
                //TODO: evitar que con la flechita atras del movil se entre en un ciclo de ida y vuelta.
                //Goes back to main activity
                finish();
                startActivity(new Intent(MedForm.this, DisplayMeds.class));

            }
        });


        //Managing spinners:
        //Adapter: adapta los strings a vistas:

        //TODO: comprobar que se seleccionan correctamente los valores, por lo visto el OnItemSelected listener tambien se ejecuta al principio.

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
                //TODO: que pasa si no selecciona
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
                //TODO: que pasa si no selecciona
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
                //TODO: que pasa si no selecciona
                dosisCada = 24;
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
        return false;
    }

    //Metodo onCLick del boton 1 dosis
    //TODO: probar que esto funciona correctamente.
    public void selectHora(View view) {

        //Cojo el tiempo actual.
        calendar = Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker vista, int hora, int minuto) {
                //Aquí llega la hora y minutos seleccionados en la vista.
                //Los guardo con un GregorianCalendar, el año mes y dia de semana lo saco del tiempo actual.
                primeraDosis = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_WEEK), hora, minuto);
            }
        };

        TimePickerDialog horas = new TimePickerDialog(this, listener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        horas.setTitle("¿A qué hora fue la primera dosis?");
        horas.setButton(DialogInterface.BUTTON_POSITIVE, "Listo", horas);
        horas.show();
    }


    //Metodo onClick del boton repetir
    public void selectDiaSemana(View view) {

        final String[] dias = getResources().getStringArray(R.array.dias);

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

                for(int i = 0; i<waschecked.length;i++){
                    if(waschecked[i]){
                        Semana.add(DiasSemana.values()[i]);
                    }
                }


            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Lo que hace el boton de cancelar
                //TODO: que hacemos si cancela lo de los dias.
            }
        });

        builder.create();
        builder.show();
    }

}

