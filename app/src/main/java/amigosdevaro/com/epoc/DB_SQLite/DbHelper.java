package amigosdevaro.com.epoc.DB_SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        db.execSQL(DataBaseManager.CREATE_TABLE_DIASYHORAS);
        db.execSQL(DataBaseManager.CREATE_TABLE_SATOXIGENO);
        db.execSQL(DataBaseManager.CREATE_TABLE_DESCOMPENSACIONES);


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva){
        //TODO migrar los datos de la version anterior a la version nueva

    }

}
