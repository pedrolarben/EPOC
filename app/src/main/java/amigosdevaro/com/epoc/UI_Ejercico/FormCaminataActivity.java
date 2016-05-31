package amigosdevaro.com.epoc.UI_Ejercico;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import amigosdevaro.com.epoc.DB_SQLite.EpocDB;
import amigosdevaro.com.epoc.R;
import amigosdevaro.com.epoc.tipos.Caminata;
import amigosdevaro.com.epoc.tipos.CaminataImpl;

public class FormCaminataActivity extends AppCompatActivity {

    Calendar calendar ;
    String strFechaInit  = "";
    String strHoraInit = "";
    String strDuracionInit = "";
    String strDisneaInit ="";
    String strEjercicioInit ="";
    String strObservacionesInit ="";

    static Map<Double,String> disneaValues;

    public FormCaminataActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_caminata);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        disneaValues = new HashMap<Double,String>();
        disneaValues.put(0.,"Sin disnea");
        disneaValues.put(0.5,"Muy ligera, practicamente no se nota");
        disneaValues.put(1., "Muy ligera");
        disneaValues.put(2.,"Ligera");
        disneaValues.put(3.,"Moderada");
        disneaValues.put(4.,"En ocaciones severa");
        disneaValues.put(5.,"Severa");
        disneaValues.put(7., "Muy severa");
        disneaValues.put(9.,"Muy Severa, en ocaciones máxima");
        disneaValues.put(10., "Máxima");

        Button buttonFecha = (Button) findViewById(R.id.caminata_dia_button);
        Button buttonHora = (Button) findViewById(R.id.caminata_hora_button);
        Button buttonDuracion = (Button) findViewById(R.id.caminata_duracion_button);
        Button buttonDisnea = (Button) findViewById(R.id.caminata_disnea_button);
        Button buttonBrazos = (Button) findViewById(R.id.caminata_brazos_button);
        EditText editTextObservaciones = (EditText) findViewById(R.id.caminata_observaciones_editText);

        strFechaInit += buttonFecha.getText();
        strHoraInit += buttonHora.getText();
        strDuracionInit += buttonDuracion.getText();
        strDisneaInit += buttonDisnea.getText();
        strEjercicioInit += buttonBrazos.getText();
        strObservacionesInit+=editTextObservaciones.getText();

        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public void selectDiaCaminata(View view) {
        calendar = Calendar.getInstance();
        final Button buttonDia = (Button) findViewById(R.id.caminata_dia_button);

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String diaText = "";
                if(dayOfMonth<10)
                    diaText+="0";
                diaText+=dayOfMonth+"/";
                if(monthOfYear<10)
                    diaText+="0";
                diaText+=monthOfYear+"/";
                String yearStr = new String(""+year);
                yearStr = yearStr.substring(2);
                diaText+=yearStr;
                buttonDia.setText(diaText);

                buttonDia.setBackgroundColor(getResources().getColor(R.color.colorAccent));

            }
        };
        DatePickerDialog date = new DatePickerDialog(this, listener,calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        date.setTitle("¿Cuándo saliste a caminar?");
        date.setButton(DialogInterface.BUTTON_POSITIVE, "Listo", date);
        date.show();
    }

    public void selectHoraCaminata(View view) {
        //Cojo el tiempo actual.
        calendar = Calendar.getInstance();

        final Button buttonHora = (Button) findViewById(R.id.caminata_hora_button);

        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker vista, int hora, int minuto) {
                //Aquí llega la hora y minutos seleccionados en la vista.
                //Los guardo con un GregorianCalendar, el año mes y dia de semana lo saco del tiempo actual.


                buttonHora.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                String timeText="" ;
                if(hora<10)
                    timeText += "0";
                timeText +=hora+":";
                if(minuto<10)
                    timeText+="0";
                timeText+=minuto;

                buttonHora.setText(timeText);

            }
        };

        TimePickerDialog horas = new TimePickerDialog(this, listener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        horas.setTitle("¿A qué hora saliste a caminar?");
        horas.setButton(DialogInterface.BUTTON_POSITIVE, "Listo", horas);
        horas.show();

    }
    public void selectDuracionCaminata(View view){
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);

        final Button buttonDuracion = (Button) findViewById(R.id.caminata_duracion_button);

        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker vista, int hora, int minuto) {
                //Aquí llega la hora y minutos seleccionados en la vista.
                //Los guardo con un GregorianCalendar, el año mes y dia de semana lo saco del tiempo actual.


                buttonDuracion.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                String timeText="" ;
                if(hora>1){
                    timeText+=hora+" horas y ";
                }
                if(hora==1){
                    timeText+=hora+" hora y ";
                }
                timeText+=minuto+" minuto";
                if(minuto>1||minuto==0){
                    timeText+="s";
                }



                buttonDuracion.setText(timeText);

            }
        };

        TimePickerDialog horas = new TimePickerDialog(this, listener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        horas.setTitle("¿Cuánto tiempo has estado caminando?");
        horas.setButton(DialogInterface.BUTTON_POSITIVE, "Listo", horas);
        horas.show();

    }
    public void selectDisneaCaminata(View view){
        DialogoDisnea dialog = new DialogoDisnea();
        dialog.init(this);
        FragmentManager fragmentManager= getSupportFragmentManager();
        dialog.show(fragmentManager, "tagDisnea");


    }
    public void selectBrazosCaminata(View view){
        DialogoBrazos dialog = new DialogoBrazos();
        FragmentManager fragmentManager = getSupportFragmentManager();
        dialog.show(fragmentManager,"tagDisnea");

    }

    public void onSaveCaminata(View view) {
        Caminata caminata = new CaminataImpl();

        String strFecha = "";
        String strHora = "";
        String strDuracion = "";
        String strDisnea ="";
        String strEjercicio="";
        String strObservaciones ="";

        Button buttonFecha = (Button) findViewById(R.id.caminata_dia_button);
        Button buttonHora = (Button) findViewById(R.id.caminata_hora_button);
        Button buttonDuracion = (Button) findViewById(R.id.caminata_duracion_button);
        Button buttonDisnea = (Button) findViewById(R.id.caminata_disnea_button);
        Button buttonBrazos = (Button) findViewById(R.id.caminata_brazos_button);
        EditText editTextObservaciones = (EditText) findViewById(R.id.caminata_observaciones_editText);

        strFecha += buttonFecha.getText();
        strHora += buttonHora.getText();
        strDuracion += buttonDuracion.getText();
        strDisnea += buttonDisnea.getText();
        strEjercicio += buttonBrazos.getText();
        strObservaciones+=editTextObservaciones.getText();

        calendar = Calendar.getInstance();

        if(strFecha.equals(strFechaInit)){
            strFecha = "";
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            int monthOfYear = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            if(dayOfMonth<10)
                strFecha+="0";
            strFecha+=dayOfMonth+"/";
            if(monthOfYear<10)
                strFecha+="0";
            strFecha+=monthOfYear+"/";
            String yearStr = new String(""+year);
            yearStr = yearStr.substring(2);
            strFecha+=yearStr;
        }
        if(strHora.equals(strHoraInit)){
            strHora="" ;
            int hora = calendar.get(Calendar.HOUR_OF_DAY);
            int minuto = calendar.get(Calendar.MINUTE);
            if(hora<10)
                strHora += "0";
            strHora +=hora+":";
            if(minuto<10)
                strHora+="0";
            strHora+=minuto;


        }
        if(strDuracion.equals(strDuracionInit)){
            Toast tDuracion = Toast.makeText(this, "Selecciona la duración",Toast.LENGTH_LONG);
            TextView v = (TextView) tDuracion.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.WHITE);
            tDuracion.show();
            return ;
        }
        if(strDisnea.equals(strDisneaInit)){
            Toast tDisnea = Toast.makeText(this, "Selecciona el nivel de disnea",Toast.LENGTH_LONG);
            TextView v = (TextView) tDisnea.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.WHITE);
            tDisnea.show();
            return ;
        }
        if(strEjercicio.equals(strEjercicioInit)){
            strEjercicio = "NO";
        }
        int duracion = 0;


        caminata.setDisnea(DialogoDisnea.getDisnea(strDisnea));
        caminata.setDuracion(getDuracion(strDuracion));
        caminata.setEjercicios(strEjercicio);
        caminata.setFecha(strFecha);
        caminata.setHora(strHora);
        caminata.setObservaciones(strObservaciones);

        EpocDB.addCaminata(caminata);

        Intent intent = new Intent(this, TablaCaminatasActivity.class);
        startActivity(intent);



    }
    private Integer getDuracion(String duracion){
        String[] array = duracion.split("\\s+");
        if(array.length==2){
            return new Integer(array[0]);
        }
        if(array.length==5){
            return new Integer(array[0])*60+new Integer(array[3]);
        }
        return -1;
    }


    public static class DialogoDisnea extends DialogFragment  {
        TextView disnea ;



        public void init(Context context){
            disnea= new TextView(context);
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            List<String> lsItems = new ArrayList<String>(FormCaminataActivity.disneaValues.values());
            Collections.sort(lsItems, new Comparator<String>() {
                @Override
                public int compare(String lhs, String rhs) {
                    return getDisnea(lhs).compareTo(getDisnea(rhs));
                }
            });
            final String[] items = lsItems.toArray(new String[lsItems.size()]);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("¿Cómo te has sentido?").setItems(items, new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Button buttonDisnea = (Button) getActivity().findViewById(R.id.caminata_disnea_button);
                    buttonDisnea.setText(items[which]);
                    buttonDisnea.setBackgroundColor(getResources().getColor(R.color.colorAccent));

                }
            });


            return builder.create();

        }

        public static Double getDisnea(String value){
            Double res = -1.;
            if(FormCaminataActivity.disneaValues.containsValue(value)){
                for(double k : disneaValues.keySet()){
                    if(disneaValues.get(k).equals(value)){
                        res =k;break;}
                }
            }
            return res;
        }

    }

    public static class DialogoBrazos extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Button buttonBrazos = (Button) getActivity().findViewById(R.id.caminata_brazos_button);

            AlertDialog.Builder builder =
                    new AlertDialog.Builder(getActivity());

            builder.setMessage("¿Has realizado ejercicios de brazos?")
                    .setTitle("Ejercicios de brazos")
                    .setPositiveButton("SI", new DialogInterface.OnClickListener()  {
                        public void onClick(DialogInterface dialog, int id) {
                            buttonBrazos.setText("SI");
                            buttonBrazos.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        }
                    })
                    .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            buttonBrazos.setText("NO");
                            buttonBrazos.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        }
                    });

            return builder.create();
        }
    }
}
