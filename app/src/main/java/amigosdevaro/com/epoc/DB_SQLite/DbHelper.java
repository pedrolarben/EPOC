package amigosdevaro.com.epoc.DB_SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import amigosdevaro.com.epoc.tipos.Paciente;
import amigosdevaro.com.epoc.tipos.PacienteImpl;

/**
 * Created by Alberto on 20/04/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "basededatos.sqlite";
    private static final int DB_SCHEME_VERSION = 1; // ESQUEMA DE LA BASE DE DATOS, VERSIÓN DE LA BASE DE DATOS



    public DbHelper(Context contexto){
        super(contexto, DB_NAME, null, DB_SCHEME_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db ){
        //crear la base de datos
        db.execSQL(DataBaseManager.CREATE_TABLE_PACIENTES);
        db.execSQL(DataBaseManager.CREATE_TABLE_POSOLOGIAS);
        db.execSQL(DataBaseManager.CREATE_TABLE_FARMACOS);
        db.execSQL(DataBaseManager.CREATE_TABLE_FARMACOSTOMADOS);
        db.execSQL(DataBaseManager.CREATE_TABLE_SATOXIGENO);
        db.execSQL(DataBaseManager.CREATE_TABLE_DESCOMPENSACIONES);
        db.execSQL(DataBaseManager.CREATE_TABLE_CAMINATAS);
        Paciente user = new PacienteImpl();

      ;
        //si hemos abierto correctamente la db
        if(db!=null){
            db.execSQL("INSERT INTO pacientes (fev, disrea, actfisica, hospitalizaciones) VALUES ("+user.getFev()+","+user.getDisnea()+","+user.getActFisica()+","+user.getHospitalizaciones()+")");
        }


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva){
        db.execSQL("DROP TABLE IF EXISTS pacientes");
        db.execSQL("DROP TABLE IF EXISTS posologias");
        db.execSQL("DROP TABLE IF EXISTS farmacos");
        db.execSQL("DROP TABLE IF EXISTS farmacosTomados");
        db.execSQL("DROP TABLE IF EXISTS satoxigeno");
        db.execSQL("DROP TABLE IF EXISTS descompensaciones");
        db.execSQL("DROP TABLE IF EXISTS caminatas");


        //Se crea la nueva versión de la tabla
        db.execSQL(DataBaseManager.CREATE_TABLE_PACIENTES);
        db.execSQL(DataBaseManager.CREATE_TABLE_POSOLOGIAS);
        db.execSQL(DataBaseManager.CREATE_TABLE_FARMACOS);
        db.execSQL(DataBaseManager.CREATE_TABLE_FARMACOSTOMADOS);
        db.execSQL(DataBaseManager.CREATE_TABLE_SATOXIGENO);
        db.execSQL(DataBaseManager.CREATE_TABLE_DESCOMPENSACIONES);
        db.execSQL(DataBaseManager.CREATE_TABLE_CAMINATAS);
    }

}
