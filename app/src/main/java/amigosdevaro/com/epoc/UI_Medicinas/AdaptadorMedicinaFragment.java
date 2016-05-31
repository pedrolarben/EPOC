package amigosdevaro.com.epoc.UI_Medicinas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import amigosdevaro.com.epoc.DB_SQLite.EpocDB;
import amigosdevaro.com.epoc.R;
import amigosdevaro.com.epoc.tipos.Farmaco;
import amigosdevaro.com.epoc.tipos.FarmacoTomado;
import amigosdevaro.com.epoc.tipos.FarmacoTomadoImpl;

/**
 * Created by betipedro on 05/05/2016.
 */
public class AdaptadorMedicinaFragment extends RecyclerView.Adapter<AdaptadorMedicinaFragment.ViewHolderMedicinasFragment>{
    List<Farmaco> datos ;
    List<Farmaco> todosLosFarmacos;
    View itemView;
    public List<FarmacoTomado>  farmacosTomados;

    public AdaptadorMedicinaFragment(List<Farmaco> datos) {
        this.datos = datos;
        farmacosTomados = EpocDB.getFarmacosTomados();
        todosLosFarmacos = EpocDB.getFarmacos();
    }
    @Override
    public ViewHolderMedicinasFragment onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicinafragment_cardview_elem, parent, false);

        ViewHolderMedicinasFragment vh = new ViewHolderMedicinasFragment(itemView);


        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolderMedicinasFragment holder, final int position) {

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean contieneFarmaco = false;
               // FarmacoTomado farmTom = new FarmacoTomadoImpl(datos.get(position),-1,-1);
                for (FarmacoTomado ft : farmacosTomados) {
                    if (ft.getFarmaco().getNombre().equals(datos.get(position).getNombre()) &&
                            ft.getHora().equals(datos.get(position).getPosologia().getPrimeraDosisHora().get(Calendar.HOUR_OF_DAY))) {
                        contieneFarmaco = true;
                      //  farmTom.setHora(ft.getHora());
                        //farmTom.setMinutos(ft.getMinutos());
                        break;
                    }
                }
                //TODO añadir a frmacos tomados (EpocDB) y actualizar la vista
                if (!contieneFarmaco) {

                    EpocDB.addFarmacoTomado(datos.get(position), datos.get(position).getPosologia().getPrimeraDosisHora().get(Calendar.HOUR_OF_DAY), datos.get(position).getPosologia().getPrimeraDosisHora().get(Calendar.MINUTE));
                   // farmTom.setHora(datos.get(position).getPosologia().getPrimeraDosisHora().get(Calendar.HOUR_OF_DAY));
                    //farmTom.setMinutos(datos.get(position ).getPosologia().getPrimeraDosisHora().get(Calendar.MINUTE));
                  //  farmacosTomados.add(farmTom);
                    farmacosTomados = EpocDB.getFarmacosTomados();
                    Drawable d = itemView.getResources().getDrawable(R.drawable.checkbox_marked_circle_outline);
                    d.setColorFilter(itemView.getResources().getColor(R.color.colorAccent), PorterDuff.Mode.MULTIPLY);

                    holder.buttonTomado.setImageDrawable(d);


                } else {
                   Toast toast =  Toast.makeText(itemView.getContext(),"Mantén pulsado la toma para desmarcarla. ", Toast.LENGTH_SHORT);
                    TextView toastTextView = (TextView) toast.getView().findViewById(android.R.id.message);
                    toastTextView.setTextColor(Color.WHITE);
                    toast.show();
                    /*
                    Drawable d = itemView.getResources().getDrawable(R.drawable.checkbox_blank_circle_outline);
                    d.setColorFilter(itemView.getResources().getColor(R.color.gray), PorterDuff.Mode.MULTIPLY);

                    holder.buttonTomado.setImageDrawable(d);
                    EpocDB.eliminarFarmacoTomado(datos.get(position), datos.get(position).getPosologia().getPrimeraDosisHora().get(Calendar.HOUR_OF_DAY), datos.get(position).getPosologia().getPrimeraDosisHora().get(Calendar.MINUTE));

                    farmacosTomados = EpocDB.getFarmacosTomados();
                    */


                }


            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                boolean contieneFarmaco = false;
                // FarmacoTomado farmTom = new FarmacoTomadoImpl(datos.get(position),-1,-1);
                for (FarmacoTomado ft : farmacosTomados) {
                    if (ft.getFarmaco().getNombre().equals(datos.get(position).getNombre()) &&
                            ft.getHora().equals(datos.get(position).getPosologia().getPrimeraDosisHora().get(Calendar.HOUR_OF_DAY))) {
                        contieneFarmaco = true;
                        //  farmTom.setHora(ft.getHora());
                        //farmTom.setMinutos(ft.getMinutos());
                        break;
                    }
                }
                if(contieneFarmaco){
                    Drawable d = itemView.getResources().getDrawable(R.drawable.checkbox_blank_circle_outline);
                    d.setColorFilter(itemView.getResources().getColor(R.color.gray), PorterDuff.Mode.MULTIPLY);

                    holder.buttonTomado.setImageDrawable(d);
                    EpocDB.eliminarFarmacoTomado(datos.get(position), datos.get(position).getPosologia().getPrimeraDosisHora().get(Calendar.HOUR_OF_DAY), datos.get(position).getPosologia().getPrimeraDosisHora().get(Calendar.MINUTE));

                    farmacosTomados = EpocDB.getFarmacosTomados();

                }
                return true;
            }
        });

        holder.txtNombre.setText(datos.get(position).getNombre());
        holder.txtTipo.setText(datos.get(position).getTipo().toString());
        String hora = ""+datos.get(position).getPosologia().getPrimeraDosisHora().get(Calendar.HOUR_OF_DAY);
        String minutos = ""+datos.get(position).getPosologia().getPrimeraDosisHora().get(Calendar.MINUTE);
        if(hora.length()<2){
            hora="0"+hora;
        }
        if(minutos.length()<2){
            minutos="0"+minutos;
        }
        holder.txtHora.setText(hora+":"+minutos);
        //TODO if ( farmaco ya esta tomado) cambiar el boton por un tic ./
        boolean contieneFarmaco = false;

        for(FarmacoTomado ft:farmacosTomados){
            if( ft.getFarmaco().getNombre().equals(datos.get(position).getNombre())&&
                    ft.getHora().equals(datos.get(position).getPosologia().getPrimeraDosisHora().get(Calendar.HOUR_OF_DAY))){
                contieneFarmaco=true;
            }
        }
        if(!contieneFarmaco){
            Drawable d = itemView.getResources().getDrawable(R.drawable.checkbox_blank_circle_outline);
            d.setColorFilter(itemView.getResources().getColor(R.color.gray), PorterDuff.Mode.MULTIPLY);

            holder.buttonTomado.setImageDrawable(d);
        }
        else{
            Drawable d = itemView.getResources().getDrawable(R.drawable.checkbox_marked_circle_outline);
            d.setColorFilter(itemView.getResources().getColor(R.color.colorAccent), PorterDuff.Mode.MULTIPLY);

            holder.buttonTomado.setImageDrawable(d);
        }
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    /*************************************
     *  VIEWHOLDER
     *************************************/
    public static class ViewHolderMedicinasFragment extends RecyclerView.ViewHolder {

        public  CardView cardView;
         TextView txtNombre ;
        private TextView txtTipo ;
        private TextView txtHora;
        ImageView buttonTomado;
        Farmaco farmaco ;

        private Farmaco farm;

        private List<FarmacoTomado> farmacosTomados;

        public ViewHolderMedicinasFragment(final View itemView){
            super(itemView);
            this.farmacosTomados = EpocDB.getFarmacosTomados();
            txtNombre = (TextView) itemView.findViewById(R.id.medicinafragment_nombre);
            txtTipo = (TextView) itemView.findViewById(R.id.medicinafragment_tipo);
            txtHora = (TextView) itemView.findViewById(R.id.medicinafragment_hora);
            buttonTomado= (ImageView) itemView.findViewById(R.id.medicinafragment_tomado);
            cardView = (CardView) itemView.findViewById(R.id.cardview_element_med_frag);


        }
        public void bindTitular(Farmaco f){


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
            boolean contieneFarmaco = false;
            for(FarmacoTomado ft:farmacosTomados){
               if( ft.getFarmaco().getNombre().equals(f.getNombre())&&
                ft.getHora().equals(f.getPosologia().getPrimeraDosisHora().get(Calendar.HOUR_OF_DAY))){
                   contieneFarmaco=true;
               }
            }
            if(!contieneFarmaco){
                Drawable d = itemView.getResources().getDrawable(R.drawable.checkbox_blank_circle_outline);
                d.setColorFilter(itemView.getResources().getColor(R.color.gray), PorterDuff.Mode.MULTIPLY);

                buttonTomado.setImageDrawable(d);
            }
            else{
                Drawable d = itemView.getResources().getDrawable(R.drawable.checkbox_marked_circle_outline);
                d.setColorFilter(itemView.getResources().getColor(R.color.colorAccent), PorterDuff.Mode.MULTIPLY);

                buttonTomado.setImageDrawable(d);
            }
            buttonTomado.setClickable(false);


        }



    }
    public  void actualiza(int pos, Farmaco f){
       /*datos.clear();
        datos.addAll(datas);
        notifyDataSetChanged();
    */
        notifyItemChanged(pos, f);
       // notifyDataSetChanged();

    }
}
