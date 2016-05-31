package amigosdevaro.com.epoc.UI_exacerbaciones;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import amigosdevaro.com.epoc.MainActivity;
import amigosdevaro.com.epoc.R;

public class Exacerbaciones extends AppCompatActivity {

    //Switches
    final static Integer NUM_PREGUNTAS = 9;
    Map<Integer, Boolean> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exacerbaciones);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_exacerbacion);
        setSupportActionBar(toolbar);


        values = new HashMap<Integer, Boolean>();
        for(Integer i = 1; i <= NUM_PREGUNTAS; i++ ){
            values.put(i,false);
        }


    }


    //OnClick del boton
    public void enviarFormulario(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if ((values.get(1) && values.get(2) && values.get(3))||(values.get(1) &&  values.get(4)&&values.get(5))||(values.get(2) &&  values.get(4)&&values.get(5))||(values.get(3) &&  values.get(4)&&values.get(5)) ) {

            DialogoLlamar dialog = new DialogoLlamar();
            FragmentManager fragmentManager = getSupportFragmentManager();
            dialog.show(fragmentManager, "tagDisnea");


        } else if(!values.values().contains(true)){
            builder.setMessage("Estas sano como una rosa. Disfruta del día.\n\nPor cierto, hoy es un magnífico día para salir a caminar, ¿no crees?")
                    .setTitle("Resultado")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    }).create().show();

        }
        else  if (!(values.get(1) && values.get(2) && values.get(3) && values.get(4) && values.get(5) && values.get(6))) {
            builder.setMessage("Parece que no estás sufriendo una exacerbación. Hidratate, come sano, haz ejercicio y no fumes. Esto te ayudará a encontrarte mejor.")
                    .setTitle("Resultado")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    }).create().show();

        }else
        if(values.get(1) || values.get(2) || values.get(3) ){
            builder.setMessage("Tienes algúnos sintomas the relativa importancia. Te recomendamos que consultes con tu médico.")
                    .setTitle("Resultado")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    }).create().show();
        }
            else  {

            builder.setMessage("No tienes todos los síntomas propios de una exacerbación. Pero si no mejoras no dudes en consultar con tu médico.")
                    .setTitle("Resultado")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    }).create().show();
        }


    }

    public static class DialogoLlamar extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builder =
                    new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();


            builder.setView(inflater.inflate(R.layout.dialogo_llamada, null)).setMessage("Deberías ir a emergencias, tienes lo síntomas propios de una exacerbación.")
                    .setTitle("Resultado")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            getActivity().finish();
                        }
                    });


            return builder.create();
        }
    }

    public void llamarEmergencia(View view) {
        String phone = "902505061";
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        startActivity(intent);
        finish();
    }

    public void exacerbacionesRadioButtonOnClick(View view) {

        addRadioButtonValue(getResources().getResourceName(view.getId()));
    }

    public void addRadioButtonValue(String id) {
        String str = id.substring(id.length()-2).trim();
        Log.d("exacerbacion",str);
        String[] array = str.split("");
        Integer key = new Integer(str.charAt(0)+"");
        Boolean value = str.charAt(1)=='S';
        Log.d("exacerbacionkey",key.toString());
        Log.d("exacerbacionValue",value.toString());
        this.values.put(key,value);

    }

}
