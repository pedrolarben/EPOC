package amigosdevaro.com.epoc.UI_Medicinas;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    Context context;
    TextView emptyListText;

    public MedicinasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        Log.d("MedicinaFragment", "onResume");
        update();
        super.onResume();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medicinas, container, false);

        context = container.getContext();

        datos = new ArrayList<>();
        datos = EpocDB.getFarmacosToDisplay(); //TODO: cambiar por EpocDB.getFarmacosToDisplay  que te pasa los farmacos ordenados y además te pone los que ya te has tomado al final

        recyclerView = (RecyclerView) view.findViewById(R.id.medicinafragment_recyclerview);
        recyclerView.setHasFixedSize(true);



        DefaultItemAnimator animator = new DefaultItemAnimator() {
            @Override
            public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder viewHolder) {
                return true;
            }
        };
        recyclerView.setItemAnimator(null);

        final AdaptadorMedicinaFragment adaptador = new AdaptadorMedicinaFragment(datos);


        recyclerView.setAdapter(adaptador);

        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false));

        emptyListText = (TextView) view.findViewById(R.id.no_medicine_text);
        if(datos.size()==0){
            recyclerView.setVisibility(View.GONE);
            emptyListText.setVisibility(View.VISIBLE);
        }else{
            recyclerView.setVisibility(View.VISIBLE);
            emptyListText.setVisibility(View.GONE);
        }

        return view;
    }

    public  void update(){


        datos = new ArrayList<>();
        datos = EpocDB.getFarmacosToDisplay(); //TODO: cambiar por EpocDB.getFarmacosToDisplay  que te pasa los farmacos ordenados y además te pone los que ya te has tomado al final


        recyclerView.setHasFixedSize(true);

        final AdaptadorMedicinaFragment adaptador = new AdaptadorMedicinaFragment(datos);

        recyclerView.setAdapter(adaptador);

        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));





    }

}
