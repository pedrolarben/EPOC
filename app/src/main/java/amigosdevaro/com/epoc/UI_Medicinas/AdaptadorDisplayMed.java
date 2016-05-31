package amigosdevaro.com.epoc.UI_Medicinas;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import amigosdevaro.com.epoc.DB_SQLite.EpocDB;
import amigosdevaro.com.epoc.R;
import amigosdevaro.com.epoc.Services.DosisAlarm;
import amigosdevaro.com.epoc.tipos.DiasSemana;
import amigosdevaro.com.epoc.tipos.Farmaco;
import amigosdevaro.com.epoc.tipos.FarmacoImpl;

/**
 * Created by betipedro on 05/05/2016.
 */
public class AdaptadorDisplayMed extends RecyclerView.Adapter<AdaptadorDisplayMed.ViewHolderDisplayMed>{
    List<Farmaco> datos ;
    Context context;

    public AdaptadorDisplayMed(List<Farmaco> datos,Context context) {
        this.datos = datos;
        this.context = context;
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
    public  class ViewHolderDisplayMed extends RecyclerView.ViewHolder implements View.OnClickListener{


        private TextView txtNombre ;
        private TextView txtDias ;
        private TextView txtAdministracion ;
        private TextView txtTipo ;
        private ImageButton buttonDelete;
        private ImageButton buttonEdit;
        private Integer cada = -1;


        public ViewHolderDisplayMed(final View itemView){
            super(itemView);

            txtNombre = (TextView) itemView.findViewById(R.id.displaymed_nombre);
            txtDias = (TextView) itemView.findViewById(R.id.displaymed_diassemana);
            txtAdministracion = (TextView) itemView.findViewById(R.id.displaymed_administracion);
            txtTipo = (TextView) itemView.findViewById(R.id.displaymed_tipo);
            buttonDelete= (ImageButton) itemView.findViewById(R.id.displaymed_delete);
            buttonEdit = (ImageButton) itemView.findViewById(R.id.displaymed_edit);



        }
        public void bindTitular(Farmaco f){

            txtNombre.setText(f.getNombre());
            txtTipo.setText(f.getTipo().toString());
            cada = f.getPosologia().getCadaCuantosDias();
            String administracion = f.getPosologia().getAdministracion().toString();
            if(administracion=="INHALADORES")
                administracion = administracion.substring(0,8);
            else{
                administracion = administracion.replace("_"," ");
            }
            txtAdministracion.setText(administracion);
            String diasSemanas = "";
            for(DiasSemana dia : DiasSemana.values()){
                if(f.getPosologia().getDiassemanas().contains(dia)){
                    diasSemanas+="  "+dia.toString();
                }
            }
            final Farmaco frmaco = f;
            diasSemanas += " - "+itemView.getResources().getString(R.string.cada);
            diasSemanas += " " +cada+" "+itemView.getResources().getString(R.string.hora);
            if(cada>1){
                diasSemanas+="s";
            }
            txtDias.setText(diasSemanas);

            buttonDelete.setOnClickListener(this);

            buttonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.d("DISPLAYMED", "edit");
                    //1705016..........
                    Farmaco editar = datos.get(getAdapterPosition());
                    Intent intent = new Intent(v.getContext(), MedForm.class);
                    intent.putExtra("farmacoAEditar",(Serializable)editar);
                    ((Activity)context).finish();
                    v.getContext().startActivity(intent);
                    //1705016..........
                }
            });


        }

        @Override
        public void onClick(View v) {
            Farmaco f = new FarmacoImpl((String)this.txtNombre.getText(),null,null);
            EpocDB.eliminaFarmaco(f);
            removeAt(getAdapterPosition());
        }
    }
    public void removeAt(int position){
        datos.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
        Log.d("remove", position + " eliminado");
        notifyItemRangeChanged(position, datos.size() - 1);

        SharedPreferences prefs =
                context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        DosisAlarm.actualiza(context,prefs.getBoolean("alarma",true));
    }
}
