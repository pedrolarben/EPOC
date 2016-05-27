package amigosdevaro.com.epoc.UI_Ejercico;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import amigosdevaro.com.epoc.R;
import amigosdevaro.com.epoc.tipos.Farmaco;

public class ProgramaEjerciciosActivity extends AppCompatActivity {



    RecyclerView recyclerView;
    private ArrayList<String> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programa_ejercicios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_programa_ejercicios);
        setSupportActionBar(toolbar);

        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        cargarDatos();

        recyclerView = (RecyclerView) findViewById(R.id.programaEjercicios_recyclerview);
        recyclerView.setHasFixedSize(true);

        AdaptadorProgramaEjercicios adaptador = new AdaptadorProgramaEjercicios(datos);

        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


    }
    private  void cargarDatos(){
        datos = new ArrayList<String>();
        datos.add("Elevar Brazos<->Suba y baje vasos a un soporte que esté por encima de sus hombros. Durante 2 minutos cada brazo.");
        datos.add("Diagonales<->Realice diagonales durante 10 minutos.");
        datos.add("Escaleras<->Suba y baje escaleras durante 10 minutos.");
        datos.add("Caminar<->Camine 20 minutos. En esta aplicación puedes guardar los datos de tus caminatas.");

    }

}
