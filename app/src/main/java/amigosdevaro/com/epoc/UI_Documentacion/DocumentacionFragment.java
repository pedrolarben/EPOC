package amigosdevaro.com.epoc.UI_Documentacion;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import amigosdevaro.com.epoc.R;
import amigosdevaro.com.epoc.UI_Documentacion.AdaptadorDocumentacionFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class DocumentacionFragment extends Fragment {

    RecyclerView recyclerView;
    private ArrayList<String> datos;

    public DocumentacionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_documentacion, container, false);


        datos = new ArrayList<String>();
        String[] array = view.getResources().getStringArray(R.array.indice_doc);

        view.getResources().getStringArray(R.array.indice_doc);
        for(String s : array){
            datos.add(s);
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.lstLista);
        recyclerView.setHasFixedSize(true);

        final AdaptadorDocumentacionFragment adaptador = new AdaptadorDocumentacionFragment(datos);

        recyclerView.setAdapter(adaptador);

        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext(),LinearLayoutManager.VERTICAL,false)
        );


        return view;
    }

}
