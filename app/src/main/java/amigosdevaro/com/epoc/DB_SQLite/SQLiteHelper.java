package amigosdevaro.com.epoc.DB_SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by betipedro on 15/03/2016.
 */
public class SQLiteHelper  extends SQLiteOpenHelper{

    String sqlUsuariosCreate = "CREATE TABLE Usuarios ()";
    String sqlPacientesCreate = "CREATE TABLE Pacientes ()";
    String sqlFarmacosCreate = "CREATE TABLE Farmacos()";
    String sqlSatOxigenoCreate = "CREATE TABLE SatOxigeno()";
    String sqlDescompensacionesCreate = "CREATE TABLE Descompensaciones()";




    public SQLiteHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory,int version ){
        super(contexto, nombre, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db  ){
        //crear la base de datos
        db.execSQL(sqlUsuariosCreate);
        db.execSQL(sqlPacientesCreate);
        db.execSQL(sqlFarmacosCreate);
        db.execSQL(sqlSatOxigenoCreate);
        db.execSQL(sqlDescompensacionesCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva){
        //TODO migrar los datos de la version anterior a la version nueva

    }




}
