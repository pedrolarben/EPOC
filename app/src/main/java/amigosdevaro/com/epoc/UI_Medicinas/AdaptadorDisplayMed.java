package amigosdevaro.com.epoc.UI_Medicinas;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import amigosdevaro.com.epoc.R;
import amigosdevaro.com.epoc.tipos.DiasSemana;
import amigosdevaro.com.epoc.tipos.Farmaco;

/**
 * Created by betipedro on 05/05/2016.
 */
public class AdaptadorDisplayMed extends RecyclerView.Adapter<AdaptadorDisplayMed.ViewHolderDisplayMed>{
    List<Farmaco> datos ;

    public AdaptadorDisplayMed(List<Farmaco> datos) {
        this.datos = datos;
    }

    @Override
    public ViewHolderDisplayMed onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.displaymed_cardview_element, parent, false);

        ViewHolderDisplayMed vh = new ViewHolderDisplayMed(itemView);


        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolderDisplayMed holder, int position) {

        Farmaco farmaco = datos.get(position    );
        holder.bindTitular(farmaco);

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    /*************************************
     *  VIEWHOLDER
     *************************************/
    public static class ViewHolderDisplayMed extends RecyclerView.ViewHolder{


        private TextView txtNombre ;
        private TextView txtDias ;
        private TextView txtTipo ;
        private ImageButton buttonDelete;
        private ImageButton buttonEdit;
        private Integer cada = -1;

        public ViewHolderDisplayMed(final View itemView){
            super(itemView);
            txtNombre = (TextView) itemView.findViewById(R.id.displaymed_nombre);
            txtDias = (TextView) itemView.findViewById(R.id.displaymed_diassemana);
            txtTipo = (TextView) itemView.findViewById(R.id.displaymed_tipo);
            buttonDelete= (ImageButton) itemView.findViewById(R.id.displaymed_delete);
            buttonEdit = (ImageButton) itemView.findViewById(R.id.displaymed_edit);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //TODO://TODO pasar a la actividad MedForms pasandole el farmaco como parametro para que pueda editarlo.
                    Log.d("DISPLAYMED", "long click item");
                    return true;
                }
            });

        }
        public void bindTitular(Farmaco f){

            txtNombre.setText(f.getNombre());
            txtTipo.setText(f.getTipo().toString());
            cada = f.getPosologia().getCadaCuantosDias();
            String diasSemanas = "";
            for(DiasSemana dia : DiasSemana.values()){
                if(f.getPosologia().getDiassemanas().contains(dia)){
                    diasSemanas+="  "+dia.toString();
                }
            }
            diasSemanas += " - "+itemView.getResources().getString(R.string.cada);
            diasSemanas += " " +cada+" "+itemView.getResources().getString(R.string.hora);
            txtDias.setText(diasSemanas);

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO eliminar el farmaco de la base de datos EpodDB.removeFarmaco(f);
                    Log.d("DISPLAYMED", "deleted");
                }
            });
            buttonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO pasar a la actividad MedForms pasandole el farmaco como parametro para que pueda editarlo.
                    Log.d("DISPLAYMED", "edit");
                }
            });

        }
    }
}