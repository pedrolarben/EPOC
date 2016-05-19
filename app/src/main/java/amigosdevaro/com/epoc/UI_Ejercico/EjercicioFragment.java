package amigosdevaro.com.epoc.UI_Ejercico;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import amigosdevaro.com.epoc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EjercicioFragment extends Fragment {

    RecyclerView recyclerView;
    private ArrayList<String> datos;

    public EjercicioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_ejercicio, container, false);



        datos = new ArrayList<String>();
        String[] array = view.getResources().getStringArray(R.array.indice_ejercicio);
        for(String s : array    ){
            datos.add(s);
        }

        recyclerView=(RecyclerView) view.findViewById(R.id.lstEjercicio);
        recyclerView.setHasFixedSize(true);

        final AdaptadorEjercicioFragment adaptador = new AdaptadorEjercicioFragment(datos);

        recyclerView.setAdapter(adaptador);

        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false));

        return view;
    }

}
