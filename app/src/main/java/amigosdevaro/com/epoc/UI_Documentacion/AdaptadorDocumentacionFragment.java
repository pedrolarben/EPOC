package amigosdevaro.com.epoc.UI_Documentacion;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import amigosdevaro.com.epoc.R;

/**
 * Created by betipedro on 28/04/2016.
 */
public class AdaptadorDocumentacionFragment extends RecyclerView.Adapter<AdaptadorDocumentacionFragment.TitularesViewHolder> {
     ArrayList<String> datos ;

    public AdaptadorDocumentacionFragment(ArrayList<String> datos) {
        this.datos = datos;
    }

    @Override
    public TitularesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listitem_documentacion_fragment, viewGroup, false);

        TitularesViewHolder tvh = new TitularesViewHolder(itemView);

        return tvh;
    }

    @Override
    public void onBindViewHolder(TitularesViewHolder holder, int position) {
        String item = datos.get(position);

        holder.bindTitular(item);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }


    /*********** viewHolder ************/
    public static class TitularesViewHolder
            extends RecyclerView.ViewHolder {

        private TextView txtTitulo;


        public TitularesViewHolder(final View itemView) {
            super(itemView);
            txtTitulo = (TextView)itemView.findViewById(R.id.LblTitulo);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String[] array = itemView.getResources().getStringArray(R.array.indice_doc);

                    int i = 0;

                    for (String s : array) {//TODO NO FUNCIONA

                        if (s.equals((String)txtTitulo.getText())) {
                            break;
                        }
                        i++;
                    }


                    Intent intent = new Intent(itemView.getContext(), PdfDocumentacionActivity.class);
                    intent.putExtra("PDF_URL", i);
                    itemView.getContext().startActivity(intent);
                }
            });



        }

        public void bindTitular(String t) {
            txtTitulo.setText(t);

        }
    }
}
