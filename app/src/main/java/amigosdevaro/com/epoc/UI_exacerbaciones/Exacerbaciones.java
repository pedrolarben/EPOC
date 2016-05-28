package amigosdevaro.com.epoc.UI_exacerbaciones;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import amigosdevaro.com.epoc.MainActivity;
import amigosdevaro.com.epoc.R;

public class Exacerbaciones extends AppCompatActivity {

    //Switches

    Switch sw1;
    Switch sw2;
    Switch sw3;
    Switch sw4;
    Switch sw5;
    Switch sw6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exacerbaciones);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_exacerbacion);
        setSupportActionBar(toolbar);


        sw1 = (Switch) findViewById(R.id.switch_exacerbacion_1);
        sw2 = (Switch) findViewById(R.id.switch_exacerbacion_2);
        sw3 = (Switch) findViewById(R.id.switch_exacerbacion_3);
        sw4 = (Switch) findViewById(R.id.switch_exacerbacion_4);
        sw5 = (Switch) findViewById(R.id.switch_exacerbacion_5);
        sw6 = (Switch) findViewById(R.id.switch_exacerbacion_6);



    }









    //OnClick del boton
    public void enviarFormulario(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (sw1.isChecked() && sw2.isChecked() && sw3.isChecked() && sw4.isChecked() && sw5.isChecked() && sw6.isChecked()) {


            builder.setMessage("Deberías consultar al médico, tienes lo síntomas propios de una exacerbación")
                    .setTitle("Resultado")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    }).create().show();

        } else {

            builder.setMessage("No tienes todos los síntomas propios de una exacerbación. Si no mejoras no dudes en consultar al médico.")
                    .setTitle("Resultado")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    }).create().show();
    }



    }




}
