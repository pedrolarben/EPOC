package amigosdevaro.com.epoc.DB_SQLite;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import amigosdevaro.com.epoc.tipos.Descompensacion;
import amigosdevaro.com.epoc.tipos.Farmaco;
import amigosdevaro.com.epoc.tipos.Paciente;
import amigosdevaro.com.epoc.tipos.Posologia;
import amigosdevaro.com.epoc.tipos.SatOxigeno;

/**
 * Created by betipedro on 23/04/2016.
 */
public  class EpocDB {

    private static DbHelper helper ;
    private static int idPaciente;

    public static void initEpocDB(DbHelper dbhelper){
        helper = dbhelper;
        //TODO: crear un paciente por defecto y añadir a  idPaciente (variable, mirar arriba)
    }
    /******************************
     PACIENTES
     *****************************/
    public static Paciente getPaciente(){
        //usar idPaciente
        //init variables
        Paciente res = null;//TODO
        //abrimos bd:
        SQLiteDatabase readableDB = helper.getReadableDatabase();
        //si hemos abierto correctamente la db
        if(readableDB!=null){
            //TODO
            //TODO: res = new PacienteImpl(....)
        }
        //cerramos db
        readableDB.close();
        //devolvemos (en caso de que haya que devolver algo)
        return res;//TODO

    }
    public static boolean updatePaciente(){
        //usar idPaciente
        //init variables
        boolean res = false;//TODO
        //abrimos bd:
        SQLiteDatabase writableDB = helper.getWritableDatabase();
        //si hemos abierto correctamente la db
        if(writableDB!=null){
            //TODO
        }
        //cerramos db
        writableDB.close();
        //devolvemos (en caso de que haya que devolver algo)
        return res;//TODO
    }


    /******************************
     FARMACOS
     *****************************/
    public static List<Farmaco> getFarmacos(){
        //init variables
         List<Farmaco> res = new ArrayList<Farmaco>();//TODO
        //abrimos bd:
        SQLiteDatabase readableDB = helper.getReadableDatabase();
        //si hemos abierto correctamente la db
        if(readableDB!=null){
            //TODO
        }
        //cerramos db
        readableDB.close();
        //devolvemos (en caso de que haya que devolver algo)
        return res;//TODO

    }
    public static boolean addFarmaco(Farmaco f){
        //init variables
        boolean res = false;//TODO
        //abrimos bd:
        SQLiteDatabase writableDB = helper.getWritableDatabase();
        //si hemos abierto correctamente la db
        if(writableDB!=null){
            //TODO
        }
        //cerramos db
        writableDB.close();
        //devolvemos (en caso de que haya que devolver algo)
        return res;//TODO
    }

    public static List<Farmaco> getFarmacosToday(){
        //TODO seguir esquemas anteriores
        return null;
    }
    public static List<Farmaco> getFarmacosToDisplay(){
        //TODO seguir esquemas anteriores
        return null;
    }
    public static List<Farmaco> getFarmacosTomados(){
        //TODO seguir esquemas anteriores
        return null;
    }
    public static boolean addFarmacoTomado(Farmaco farmaco, GregorianCalendar time){
        //TODO seguir esquemas anteriores
        return false;
    }

    public static void restartFarmacosTomado(){
        //NO Hacer aun
    }

    /******************************
     POSOLOGIAS
     *****************************/
    public static Posologia getPosologia(Farmaco farmaco){
        //TODO seguir esquemas anteriores
        return null;
    }
    public static boolean addPosologia(Farmaco farmaco , Posologia posologia){
        //TODO seguir esquemas anteriores
        return false;
    }
    /******************************
     SatOxigeno
     *****************************/
    public static SatOxigeno getSatOxigeno(Descompensacion descompensacion){
        //TODO seguir esquemas anteriores
        return null;
    }
    public static List<SatOxigeno> getSatOxigeno(){
        //TODO seguir esquemas anteriores
        return null;
    }
    public static boolean addSatOxigeno(Descompensacion descompensacion  ,  SatOxigeno satOxigeno){
        //TODO seguir esquemas anteriores
        return false;
    }

    /******************************
     DESCOMPENSACIONES
     *****************************/
    public static List<Descompensacion> getDescompensaciones(){
        //TODO seguir esquemas anteriores
        return null;
    }
    public static boolean addDescompensacion(Descompensacion descompensacion){
        //TODO seguir esquemas anteriores //recordar que para guardar una descompensacion hay que guardar una sat oxigeno
        return false;
    }
    public static boolean updateDescompensacion(Descompensacion anterior, Descompensacion nueva){
        //TODO seguir esquemas anteriores
        return false;
    }

    /******************************
     //TODO añadir nuevos métodos que creais interesante (o que necesiteis)
     *****************************/





}
