package amigosdevaro.com.epoc.UI_Ejercico;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import amigosdevaro.com.epoc.R;

/**
 * Created by betipedro on 24/05/2016.
 */
public class AdaptadorProgramaEjercicios extends RecyclerView.Adapter<AdaptadorProgramaEjercicios.TitularesViewHolder> {


    ArrayList<String> datos;

    public AdaptadorProgramaEjercicios(ArrayList<String> datos) {
        this.datos = datos;
    }

    @Override
    public TitularesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.programa_ejercicios_cardview_element, viewGroup, false);

        TitularesViewHolder tvh = new TitularesViewHolder(itemView);

        return tvh;
    }

    @Override
    public void onBindViewHolder(TitularesViewHolder holder, int position) {
        String item = datos.get(position);


        holder.bindTitular(item, position);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }


    /***********
     * viewHolder
     ************/
    public static class TitularesViewHolder
            extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView txtTitulo;
        private TextView txtDescripcion;
        View itemView;

        public TitularesViewHolder(final View itemView) {
            super(itemView);
            this.itemView = itemView;
            txtTitulo = (TextView) itemView.findViewById(R.id.programaEjerciciosTitulo);
            txtDescripcion = (TextView) itemView.findViewById(R.id.programaEjerciciosDescripcion);
            icon = (ImageView) itemView.findViewById(R.id.programaEjerciciosImagen);


        }

        public void bindTitular(String t, final int position) {
            String array[] = t.split("<->");

                txtTitulo.setText(array[0]);
                txtDescripcion.setText(array[1]);

            switch (position) {
                case 0:
                    Drawable ic0 = this.itemView.getResources().getDrawable(R.drawable.vasos);
                    icon.setImageDrawable(ic0);
                    break;
                case 1:
                    Drawable ic1 = this.itemView.getResources().getDrawable(R.drawable.diagonales);
                    icon.setImageDrawable(ic1);
                    break;
                case 2:
                    Drawable ic2 = this.itemView.getResources().getDrawable(R.drawable.escaleras);
                    icon.setImageDrawable(ic2);
                    break;
                case 3:
                    Drawable ic3 = this.itemView.getResources().getDrawable(R.drawable.andar);
                    icon.setImageDrawable(ic3);
                    break;



            }


        }
    }
}
