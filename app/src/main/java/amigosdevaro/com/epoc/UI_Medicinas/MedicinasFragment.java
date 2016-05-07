package amigosdevaro.com.epoc.UI_Medicinas;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import amigosdevaro.com.epoc.DB_SQLite.EpocDB;
import amigosdevaro.com.epoc.R;
import amigosdevaro.com.epoc.tipos.Farmaco;

/**
 * A simple {@link Fragment} subclass.
 */
public class MedicinasFragment extends Fragment {

    RecyclerView recyclerView;
    private List<Farmaco> datos;

    public MedicinasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_medicinas, container, false);

        datos = new ArrayList<>();
        datos = EpocDB.getFarmacos(); //TODO: cambiar por EpocDB.getFarmacosToDisplay  que te pasa los farmacos ordenados y además te pone los que ya te has tomado al final

        recyclerView = (RecyclerView) view.findViewById(R.id.medicinafragment_recyclerview);
        recyclerView.setHasFixedSize(true);

        final AdaptadorMedicinaFragment adaptador = new AdaptadorMedicinaFragment(datos);

        recyclerView.setAdapter(adaptador);

        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false));



        return view;
    }

}