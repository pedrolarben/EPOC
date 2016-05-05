package amigosdevaro.com.epoc.UI_Medicinas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import amigosdevaro.com.epoc.R;

/**
 * Created by belladona on 26/4/16.
 */
public class MultipleChoiceClass extends DialogFragment {


    //Override methods to create the dialogs:

    final String[] dias = getResources().getStringArray(R.array.dias);

    //Selected items, trying out

    List<String> diasseleccionados = new ArrayList<String>();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("¿Cuándo debe repetirse la alarma?");
        builder.setMultiChoiceItems(R.array.dias, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                //isChecked true = something was selected.

                if(isChecked){
                    diasseleccionados.add(dias[which]);
                }else if(diasseleccionados.contains(dias[which])){
                    diasseleccionados.remove(which);
                }

            }
        }).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Lo que hace el boton de aceptar

                String selecionados = "";

                for (String x : diasseleccionados) {
                    selecionados += "\n" + x;
                }

                Toast.makeText(getActivity(), "Dias selecionados: " + selecionados, Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Lo que hace el boton de cancelar
            }
        });

        return builder.create();
    }







}
