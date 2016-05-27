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
import amigosdevaro.com.epoc.UI_Documentacion.PdfDocumentacionActivity;

/**
 * Created by betipedro on 17/05/2016.
 */
public class AdaptadorEjercicioFragment extends RecyclerView.Adapter<AdaptadorEjercicioFragment.TitularesViewHolder> {

    ArrayList<String> datos;
    public AdaptadorEjercicioFragment(ArrayList<String> datos){
        this.datos = datos;
    }

    @Override
    public TitularesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listitem_ejercicio_fragment, viewGroup, false);

        TitularesViewHolder tvh = new TitularesViewHolder(itemView);

        return tvh;
    }

    @Override
    public void onBindViewHolder(TitularesViewHolder holder, int position) {
        String item = datos.get(position);


        holder.bindTitular(item,position);
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
        View itemView;

        public TitularesViewHolder(final View itemView) {
            super(itemView);
            this.itemView=itemView;
            txtTitulo = (TextView) itemView.findViewById(R.id.LblTituloEjercicio);
            icon = (ImageView) itemView.findViewById(R.id.ejercicio_icon);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    /*
                    String[] array = itemView.getResources().getStringArray(R.array.indice_doc);

                    int i = 0;

                    for (String s : array) {

                        if (s.equals((String)txtTitulo.getText())) {
                            break;
                        }
                        i++;
                    }


                    Intent intent = new Intent(itemView.getContext(), PdfDocumentacionActivity.class);
                    intent.putExtra("PDF_URL", i);
                    itemView.getContext().startActivity(intent);*/
                }
            });


        }

        public void bindTitular(String t, final int position) {
            txtTitulo.setText(t);
            switch (position){
                case 0:
                    Drawable ic0 = this.itemView.getResources().getDrawable(R.drawable.dumbbell);
                    icon.setImageDrawable(ic0);
                    break;
                case 1:
                    Drawable ic1 = this.itemView.getResources().getDrawable(R.drawable.tablacaminatas);
                    icon.setImageDrawable(ic1);
                    break;


            }
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    switch (position){
                        case 0:
                            Intent intent0 = new Intent(itemView.getContext(), ProgramaEjerciciosActivity.class);
                            itemView.getContext().startActivity(intent0);
                            break;
                        case 1:
                            //TODO
                            break;
                        case 2:
                            Intent intent2 = new Intent(itemView.getContext(), PdfDocumentacionActivity.class);
                            intent2.putExtra("PDF_URL_ejercicio",2 );
                            itemView.getContext().startActivity(intent2);
                            Log.d("EJERCICIO","2");
                            break;
                        case 3:
                            Intent intent3 = new Intent(itemView.getContext(), PdfDocumentacionActivity.class);
                            intent3.putExtra("PDF_URL_ejercicio",3 );
                            itemView.getContext().startActivity(intent3);
                            break;
                    }
                }
            });


        }
    }

}
