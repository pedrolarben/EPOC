package amigosdevaro.com.epoc.TabsBarUI;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import amigosdevaro.com.epoc.R;


public class Fragment_a extends Fragment {

    RecyclerView recyclerView;
    private ArrayList<String> datos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_a, container, false);


        datos = new ArrayList<String>();
        for(int i=1 ; i<=20;i++){
            datos.add("i = "+i);
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.lstLista);
        recyclerView.setHasFixedSize(true);

        final AdaptadorFragA adaptador = new AdaptadorFragA(datos);

        recyclerView.setAdapter(adaptador);

        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext(),LinearLayoutManager.VERTICAL,false)
        );


        return view;
    }
}
