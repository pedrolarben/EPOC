package amigosdevaro.com.epoc.TabsBarUI;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import amigosdevaro.com.epoc.R;

/**
 * Created by betipedro on 28/04/2016.
 */
public class AdaptadorFragA extends RecyclerView.Adapter<AdaptadorFragA.TitularesViewHolder> {
    private ArrayList<String> datos ;

    public AdaptadorFragA(ArrayList<String> datos) {
        this.datos = datos;
    }

    @Override
    public TitularesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listitem_frag_a, viewGroup, false);

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


        public TitularesViewHolder(View itemView) {
            super(itemView);

            txtTitulo = (TextView)itemView.findViewById(R.id.LblTitulo);

        }

        public void bindTitular(String t) {
            txtTitulo.setText(t);

        }
    }
}
