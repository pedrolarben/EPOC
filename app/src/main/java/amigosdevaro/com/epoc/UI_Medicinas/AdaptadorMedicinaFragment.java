package amigosdevaro.com.epoc.UI_Medicinas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import amigosdevaro.com.epoc.DB_SQLite.EpocDB;
import amigosdevaro.com.epoc.R;
import amigosdevaro.com.epoc.tipos.Farmaco;

/**
 * Created by betipedro on 05/05/2016.
 */
public class AdaptadorMedicinaFragment extends RecyclerView.Adapter<AdaptadorMedicinaFragment.ViewHolderMedicinasFragment>{
    List<Farmaco> datos ;

    public AdaptadorMedicinaFragment(List<Farmaco> datos) {
        this.datos = datos;
    }

    @Override
    public ViewHolderMedicinasFragment onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicinafragment_cardview_elem, parent, false);

        ViewHolderMedicinasFragment vh = new ViewHolderMedicinasFragment(itemView);


        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolderMedicinasFragment holder, int position) {

        Farmaco farmaco = datos.get(position);
        holder.bindTitular(farmaco);

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    /*************************************
     *  VIEWHOLDER
     *************************************/
    public  class ViewHolderMedicinasFragment extends RecyclerView.ViewHolder {


        private TextView txtNombre ;
        private TextView txtTipo ;
        private TextView txtHora;
        private ImageButton buttonTomado;

        private Farmaco farm;

        private List<Farmaco> farmacosTomados;

        public ViewHolderMedicinasFragment(final View itemView){
            super(itemView);
            this.farmacosTomados = EpocDB.getFarmacosTomados();
            txtNombre = (TextView) itemView.findViewById(R.id.medicinafragment_nombre);
            txtTipo = (TextView) itemView.findViewById(R.id.medicinafragment_tipo);
            txtHora = (TextView) itemView.findViewById(R.id.medicinafragment_hora);
            buttonTomado= (ImageButton) itemView.findViewById(R.id.medicinafragment_tomado);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //TODO: pasar a la actividad MedForms pasandole el farmaco como parametro para que pueda editarlo.

                    return true;
                }
            });

        }
        public void bindTitular(Farmaco f){
            final Farmaco farm = f;
            txtNombre.setText(f.getNombre());
            txtTipo.setText(f.getTipo().toString());
            String hora = ""+f.getPosologia().getPrimeraDosisHora().get(Calendar.HOUR_OF_DAY);
            String minutos = ""+f.getPosologia().getPrimeraDosisHora().get(Calendar.MINUTE);
            if(hora.length()<2){
                hora="0"+hora;
            }
            if(minutos.length()<2){
                minutos="0"+minutos;
            }
            txtHora.setText(hora+":"+minutos);
            //TODO if ( farmaco ya esta tomado) cambiar el boton por un tic ./

            if(!farmacosTomados.contains(f)){
                Drawable d = itemView.getResources().getDrawable(R.drawable.checkbox_blank_circle_outline);
                d.setColorFilter(itemView.getResources().getColor(R.color.gray), PorterDuff.Mode.MULTIPLY);

                buttonTomado.setImageDrawable(d);
            }
            else{
                Drawable d = itemView.getResources().getDrawable(R.drawable.checkbox_marked_circle_outline);
                d.setColorFilter(itemView.getResources().getColor(R.color.colorAccent), PorterDuff.Mode.MULTIPLY);

                buttonTomado.setImageDrawable(d);
            }

            buttonTomado.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //TODO añadir a frmacos tomados (EpocDB) y actualizar la vista
                    if (!farmacosTomados.contains(farm)) {

                        EpocDB.addFarmacoTomado(farm, farm.getPosologia().getPrimeraDosisHora());
                        //TODO: actualizar el adaptador
                        actualiza(getAdapterPosition());

                    } else {


                        //EpocDB.eliminaFarmacoTomado(farm, farm.getPosologia().getPrimeraDosisHora());
                        //actualiza(getAdapterPosition());



                    }

                }
            });

        }



    }
    public  void actualiza(int pos){
        this.notifyItemChanged(pos);
    }
}
