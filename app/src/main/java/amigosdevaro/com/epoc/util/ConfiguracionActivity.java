package amigosdevaro.com.epoc.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import amigosdevaro.com.epoc.R;
import amigosdevaro.com.epoc.Services.DosisAlarm;

public class ConfiguracionActivity extends AppCompatActivity {
    Switch sw_alarma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sw_alarma = (Switch) findViewById(R.id.preferencias_alarma_sw);

        SharedPreferences prefs =
                this.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        sw_alarma.setChecked(prefs.getBoolean("alarma",true));



    }
    public void onSaveConfiguracion(View view){

        SharedPreferences prefs =
                this.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("alarma",sw_alarma.isChecked());
        editor.commit();
        DosisAlarm.actualiza(this, sw_alarma.isChecked());
        Log.d("boot", "" + sw_alarma.isChecked());
        Log.d("bootConfPref", "" + prefs.getBoolean("alarma",true));
        finish();
    }

}
