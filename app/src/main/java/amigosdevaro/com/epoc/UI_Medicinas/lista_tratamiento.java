package amigosdevaro.com.epoc.UI_Medicinas;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import amigosdevaro.com.epoc.R;

public class lista_tratamiento extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tratamiento);

        //Metodo que indica la lista de elementos que visualizar:

        ArrayList testlist = new ArrayList<String>();
        testlist.add("Farmaco 1");
        testlist.add("Farmaco 2");
        testlist.add("Farmaco 3");



        setListAdapter(new ArrayAdapter<String>(this, R.layout.elemento_lista, R.id.medicine,testlist));



       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

}
