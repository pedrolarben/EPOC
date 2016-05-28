package amigosdevaro.com.epoc.UI_Ejercico;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import amigosdevaro.com.epoc.DB_SQLite.EpocDB;
import amigosdevaro.com.epoc.R;
import amigosdevaro.com.epoc.tipos.Caminata;

public class TablaCaminatasActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Caminata> datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_caminatas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_tabla_caminatas);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mis caminatas");

        //Añadiendo flechita atras:
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        datos =  new ArrayList<Caminata>(EpocDB.getCaminatas());
        Log.d("datosTablaCaminata", "" + datos.size());
        recyclerView = (RecyclerView) findViewById(R.id.tablaCaminatas_lista);
        recyclerView.setHasFixedSize(true);

       final  AdaptadorTablaCaminatas adaptador = new AdaptadorTablaCaminatas(datos);

        recyclerView.setAdapter(adaptador );

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.acction_add_caminata);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TablaCaminatasActivity.this,FormCaminataActivity.class );
                startActivity(intent );
                finish();
                Log.d("tablaCaminatas","fab");
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
