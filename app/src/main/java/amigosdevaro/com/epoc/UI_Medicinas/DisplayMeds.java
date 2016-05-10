package amigosdevaro.com.epoc.UI_Medicinas;


import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import amigosdevaro.com.epoc.DB_SQLite.EpocDB;
import amigosdevaro.com.epoc.R;
import amigosdevaro.com.epoc.tipos.Farmaco;

public class DisplayMeds extends AppCompatActivity {

    /*Esta actividad es la encargada de mostrar todas las medicinas y de proporcionar una forma de editar y eliminar medicinas*/

 /*Android developers frequently create a TAG constant with the name of the class for logging.*/
    //Constants:
    public static String TAG = "DisplayMeds";
    RecyclerView recyclerView;
    private List<Farmaco> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_meds);
        Toolbar toolbar = (Toolbar) findViewById(R.id.display_med_toolbar);
        setSupportActionBar(toolbar);
        //Barra Arriba
        getSupportActionBar().setTitle("Mis medicamentos");

        //Añadiendo flechita atras:
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




        datos = EpocDB.getFarmacos();
        recyclerView = (RecyclerView) findViewById(R.id.displayMed_lista);
        recyclerView.setHasFixedSize(true);

        final AdaptadorDisplayMed adaptador = new AdaptadorDisplayMed(datos);

        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        final FloatingActionButton addMedicina = (FloatingActionButton) findViewById(R.id.action_add_medicine);
        addMedicina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(DisplayMeds.this, MedForm.class));
            }
        });

    }

}
