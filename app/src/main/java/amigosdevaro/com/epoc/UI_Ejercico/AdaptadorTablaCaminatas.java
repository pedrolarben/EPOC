package amigosdevaro.com.epoc.UI_Ejercico;

import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import amigosdevaro.com.epoc.DB_SQLite.EpocDB;
import amigosdevaro.com.epoc.R;
import amigosdevaro.com.epoc.tipos.Caminata;
import amigosdevaro.com.epoc.tipos.Farmaco;

/**
 * Created by betipedro on 27/05/2016.
 */
public class AdaptadorTablaCaminatas extends RecyclerView.Adapter<AdaptadorTablaCaminatas.ViewHolderCaminatas>{
    List<Caminata> datos;

    public AdaptadorTablaCaminatas(List<Caminata> datos) {
        this.datos = datos;

    }

    @Override
    public void onBindViewHolder(ViewHolderCaminatas holder, int position) {
        final Caminata caminata = datos.get(position);
        final int i = new Integer(position);


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datos.remove(i);
                EpocDB.eliminarCaminata(caminata);
                update(i);
            }
        });
        holder.bindTitular(caminata);
    }
    public void update(int i){
        notifyItemRemoved(i);

    }



    @Override
    public ViewHolderCaminatas onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.caminata_cardview_elem, parent, false);

        ViewHolderCaminatas vh = new ViewHolderCaminatas(itemView);

        return vh;
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }



    static class ViewHolderCaminatas extends RecyclerView.ViewHolder   {
        TextView fechayhora;
         TextView duracion;
        TextView disnea;
        TextView brazos;
       TextView observaciones;
        ImageButton delete;


        public ViewHolderCaminatas(View itemView) {
            super(itemView);
            fechayhora=(TextView)itemView.findViewById(R.id.caminata_fecha_hora);
            duracion=(TextView) itemView.findViewById(R.id.caminata_duracion);
            disnea = (TextView) itemView.findViewById(R.id.caminata_disnea);
            brazos = (TextView) itemView.findViewById(R.id.caminata_brazos);
            observaciones = (TextView) itemView.findViewById(R.id.caminata_observaciones);
            delete = (ImageButton) itemView.findViewById(R.id.caminata_delete);

        }
        public void bindTitular(Caminata c){
            /*fechayhora=(TextView)itemView.findViewById(R.id.caminata_fecha_hora);
            duracion=(TextView) itemView.findViewById(R.id.caminata_duracion);
            disnea = (TextView) itemView.findViewById(R.id.caminata_disnea);
            brazos = (TextView) itemView.findViewById(R.id.caminata_brazos);
            observaciones = (TextView) itemView.findViewById(R.id.caminata_observaciones);
*/
            Map<Double,String> disneaValues = new HashMap<Double,String>();
            disneaValues.put(0.,"sin disnea");
            disneaValues.put(0.5,"Muy ligera, practicamente no se nota");
            disneaValues.put(1., "Muy ligera");
            disneaValues.put(2.,"Ligera");
            disneaValues.put(3.,"Moderada");
            disneaValues.put(4.,"En ocaciones severa");
            disneaValues.put(5.,"Severa");
            disneaValues.put(7., "Muy severa");
            disneaValues.put(9.,"Muy Severa, en ocaciones máxima");
            disneaValues.put(10., "Máxima");

            fechayhora.setText(c.getFecha() + " - " + c.getHora());
            duracion.setText(c.getDuracion()+" minutos");
            disnea.setText("Disnea: "+disneaValues.get(c.getDisnea()));
            brazos.setText("Ejercicios de brazos: "+c.getEjercicios());
            observaciones.setText(c.getObservaciones());




        }


    }
}
