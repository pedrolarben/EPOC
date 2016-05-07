package amigosdevaro.com.epoc.UI_Medicinas;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

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
    public static class ViewHolderMedicinasFragment extends RecyclerView.ViewHolder{


        private TextView txtNombre ;
        private TextView txtTipo ;
        private ImageButton buttonTomado;


        public ViewHolderMedicinasFragment(final View itemView){
            super(itemView);
            txtNombre = (TextView) itemView.findViewById(R.id.medicinafragment_nombre);
            txtTipo = (TextView) itemView.findViewById(R.id.medicinafragment_tipo);
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

            txtNombre.setText(f.getNombre());
            txtTipo.setText(f.getTipo().toString());

            //TODO if ( farmaco ya esta tomado) cambiar el boton por un tic ./



            buttonTomado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO añadir a frmacos tomados (EpocDB) y actualizar la vista

                }
            });

        }
    }
}
