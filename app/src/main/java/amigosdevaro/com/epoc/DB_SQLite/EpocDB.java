package amigosdevaro.com.epoc.DB_SQLite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import amigosdevaro.com.epoc.tipos.AdministracionFarmaco;
import amigosdevaro.com.epoc.tipos.Descompensacion;
import amigosdevaro.com.epoc.tipos.DiasSemana;
import amigosdevaro.com.epoc.tipos.Farmaco;
import amigosdevaro.com.epoc.tipos.FarmacoImpl;
import amigosdevaro.com.epoc.tipos.Paciente;
import amigosdevaro.com.epoc.tipos.PacienteImpl;
import amigosdevaro.com.epoc.tipos.Posologia;
import amigosdevaro.com.epoc.tipos.PosologiaImpl;
import amigosdevaro.com.epoc.tipos.SatOxigeno;
import amigosdevaro.com.epoc.tipos.SatOxigenoImpl;
import amigosdevaro.com.epoc.tipos.TipoFarmaco;

/**
 * Created by betipedro on 23/04/2016.
 */
public  class EpocDB {

    private static DbHelper helper ;
    private static int idPaciente;

    public static void initEpocDB(DbHelper dbhelper){
        helper = dbhelper;
        idPaciente = 1;
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
         List<Farmaco> res = new ArrayList<Farmaco>();
        Boolean l= false,m=false,x=false,j=false,v=false,s=false,d = false;
        Integer cada = 6;
        AdministracionFarmaco ad = null;
        GregorianCalendar gc = null;
        String nombre = null;
        TipoFarmaco tf = null;
        int oidpos=0;
        //abrimos bd:
        SQLiteDatabase readableDB = helper.getReadableDatabase();
        //si hemos abierto correctamente la db
        if(readableDB!=null){
            Cursor c = readableDB.rawQuery("SELECT nombre, tipo, oid_pos FROM farmacos WHERE oid_p="+idPaciente+"", null);
            if(c.moveToFirst()){
                do {
                    nombre = c.getString(0);
                    tf = TipoFarmaco.valueOf(c.getString(1));
                    oidpos = c.getInt(2);
                    Cursor c1 = readableDB.rawQuery("SELECT administracionFarmaco, cada, primera, L, M, X, J, V, S, D FROM posologias where oid_pos="+oidpos+"",null);
                    if(c1.moveToFirst()){
                        ad = AdministracionFarmaco.valueOf(c1.getString(0));
                        cada = c1.getInt(1);
                        gc = new GregorianCalendar();
                        gc.setTimeInMillis(c1.getLong(2));
                        l = c1.getInt(3)==1;
                        m = c1.getInt(4)==1;
                        x = c1.getInt(5)==1;
                        j = c1.getInt(6)==1;
                        v = c1.getInt(7)==1;
                        s = c1.getInt(8)==1;
                        d = c1.getInt(9)==1;

                    }
                    Set<DiasSemana> sds = new HashSet<DiasSemana>();
                    if(l){
                        sds.add(DiasSemana.L);
                    }
                    if(m){
                        sds.add(DiasSemana.M);
                    }
                    if(x){
                        sds.add(DiasSemana.X);
                    }
                    if(j){
                        sds.add(DiasSemana.J);
                    }
                    if(v){
                        sds.add(DiasSemana.V);
                    }
                    if(s){
                        sds.add(DiasSemana.S);
                    }
                    if(d){
                        sds.add(DiasSemana.D);
                    }
                    Posologia p = new PosologiaImpl(sds, cada, gc, ad);
                    Farmaco f = new FarmacoImpl(nombre,tf, p);
                    res.add(f);
                }while(c.moveToNext());
            }
        }
        //cerramos db
        readableDB.close();
        //devolvemos (en caso de que haya que devolver algo)
        return res;

    }
    public static boolean addFarmaco(Farmaco f){
        //init variables
        boolean res = false;//TODO
        int l=0,m=0,x=0,j=0,v=0,s=0,d=0;
        int oid = 0;
        //abrimos bd:
        SQLiteDatabase writableDB = helper.getWritableDatabase();
        SQLiteDatabase readableDB = helper.getReadableDatabase();
        //si hemos abierto correctamente la db
        if(writableDB!=null){
            if(f.getPosologia().getDiassemanas().contains(DiasSemana.L)){
                l=1;
            }
            if(f.getPosologia().getDiassemanas().contains(DiasSemana.M)){
                m=1;
            }
            if(f.getPosologia().getDiassemanas().contains(DiasSemana.X)){
                x=1;
            }
            if(f.getPosologia().getDiassemanas().contains(DiasSemana.J)){
                j=1;
            }
            if(f.getPosologia().getDiassemanas().contains(DiasSemana.V)){
                v=1;
            }
            if(f.getPosologia().getDiassemanas().contains(DiasSemana.S)){
                s=1;
            }
            if(f.getPosologia().getDiassemanas().contains(DiasSemana.D)){
                d=1;
            }
            writableDB.execSQL("INSERT INTO posologias (administracionFarmaco, cada, primera, L, M, X, J, V, S, D) VALUES ('"+f.getPosologia().getAdministracion().toString()+"', "+f.getPosologia().getCadaCuantosDias()+", "+f.getPosologia().getPrimeraDosisHora().getTimeInMillis()+ ", "+l+ ", "+m+ ", "+x+", "+j+", "+v+", "+s+", "+d+") ");

        }
        if(readableDB!=null){
            Cursor c = readableDB.rawQuery("SELECT MAX(oid_pos) FROM posologias", null);
            if(c.moveToFirst()) {
                    oid = c.getColumnIndex("oid_pos");
            }
        }
        if(writableDB!=null){
            writableDB.execSQL("INSERT INTO farmacos (oid_p, oid_pos, nombre, tipo) VALUES ("+idPaciente+", "+oid+", '"+f.getNombre()+"', '"+f.getTipo().toString()+"')");
            res = true;
        }
        //cerramos db
        writableDB.close();
        readableDB.close();

        //devolvemos (en caso de que haya que devolver algo)
        return res;
    }

    public static List<Farmaco> getFarmacosToday(){
        //init variables
        List<Farmaco> res = new ArrayList<Farmaco>();
        Boolean l= false,m=false,x=false,j=false,v=false,s=false,d = false;
        Integer cada = 6;
        AdministracionFarmaco ad = null;
        GregorianCalendar gc = null;

        Calendar fecha = new GregorianCalendar();
        String nombre = null;
        TipoFarmaco tf = null;
        int oidpos=0;
        //abrimos bd:
        SQLiteDatabase readableDB = helper.getReadableDatabase();
        //si hemos abierto correctamente la db
        if(readableDB!=null){
            Cursor c = readableDB.rawQuery("SELECT nombre, tipo, oid_pos FROM farmacos WHERE oid_p="+idPaciente+"", null);
            if(c.moveToFirst()){
                do {
                    nombre = c.getString(0);
                    tf = TipoFarmaco.valueOf(c.getString(1));
                    oidpos = c.getInt(2);
                    Cursor c1 = readableDB.rawQuery("SELECT administracionFarmaco, cada, primera, L, M, X, J, V, S, D FROM posologias where oid_pos="+oidpos+"",null);
                    if(c1.moveToFirst()){
                        ad = AdministracionFarmaco.valueOf(c1.getString(0));
                        cada = c1.getInt(1);
                        gc = new GregorianCalendar();
                        gc.setTimeInMillis(c1.getLong(2));
                        l = c1.getInt(3)==1;
                        m = c1.getInt(4)==1;
                        x = c1.getInt(5)==1;
                        j = c1.getInt(6)==1;
                        v = c1.getInt(7)==1;
                        s = c1.getInt(8)==1;
                        d = c1.getInt(9)==1;

                    }
                    Set<DiasSemana> sds = new HashSet<DiasSemana>();
                    if(l){
                        sds.add(DiasSemana.L);
                    }
                    if(m){
                        sds.add(DiasSemana.M);
                    }
                    if(x){
                        sds.add(DiasSemana.X);
                    }
                    if(j){
                        sds.add(DiasSemana.J);
                    }
                    if(v){
                        sds.add(DiasSemana.V);
                    }
                    if(s){
                        sds.add(DiasSemana.S);
                    }
                    if(d){
                        sds.add(DiasSemana.D);
                    }
                    Posologia p = new PosologiaImpl(sds, cada, gc, ad);
                    Farmaco f = new FarmacoImpl(nombre,tf, p);
                    Calendar act = new GregorianCalendar();
                    act = p.getPrimeraDosisHora();
                    if(fecha.get(Calendar.DAY_OF_WEEK)==act.get(Calendar.DAY_OF_WEEK)){
                        res.add(f);
                    }

                }while(c.moveToNext());
            }
        }
        //cerramos db
        readableDB.close();
        //devolvemos (en caso de que haya que devolver algo)
        return res;

    }
    public static List<Farmaco> getFarmacosToDisplay(){
        //TODO seguir esquemas anteriores
        return null;
    }
    public static List<Farmaco> getFarmacosTomados(){
        List<Farmaco> res = new ArrayList<Farmaco>();
        Boolean l= false,m=false,x=false,j=false,v=false,s=false,d = false;
        Integer cada = 6;
        AdministracionFarmaco ad = null;
        GregorianCalendar gc = null;
        String nombre = null;
        TipoFarmaco tf = null;
        int oidpos=0;
        int oidf=0;
        //abrimos bd:
        SQLiteDatabase readableDB = helper.getReadableDatabase();
        //si hemos abierto correctamente la db
        if(readableDB!=null) {

            Cursor c = readableDB.rawQuery("SELECT oid_f FROM farmacosTomados", null);
            if (c.moveToFirst()) {
                do {
                    oidf = c.getInt(0);
                    Cursor c2 = readableDB.rawQuery("SELECT nombre, tipo, oid_pos FROM farmacos WHERE oid_f=" + oidf + "", null);
                    if (c2.moveToFirst()) {
                        nombre = c2.getString(0);
                        tf = TipoFarmaco.valueOf(c2.getString(1));
                        oidpos = c2.getInt(2);
                        Cursor c1 = readableDB.rawQuery("SELECT administracionFarmaco, cada, primera, L, M, X, J, V, S, D FROM posologias where oid_pos=" + oidpos + "", null);
                        if (c1.moveToFirst()) {
                            ad = AdministracionFarmaco.valueOf(c1.getString(0));
                            cada = c1.getInt(1);
                            gc = new GregorianCalendar();
                            gc.setTimeInMillis(c1.getLong(2));
                            l = c1.getInt(3) == 1;
                            m = c1.getInt(4) == 1;
                            x = c1.getInt(5) == 1;
                            j = c1.getInt(6) == 1;
                            v = c1.getInt(7) == 1;
                            s = c1.getInt(8) == 1;
                            d = c1.getInt(9) == 1;

                        }
                        Set<DiasSemana> sds = new HashSet<DiasSemana>();
                        if (l) {
                            sds.add(DiasSemana.L);
                        }
                        if (m) {
                            sds.add(DiasSemana.M);
                        }
                        if (x) {
                            sds.add(DiasSemana.X);
                        }
                        if (j) {
                            sds.add(DiasSemana.J);
                        }
                        if (v) {
                            sds.add(DiasSemana.V);
                        }
                        if (s) {
                            sds.add(DiasSemana.S);
                        }
                        if (d) {
                            sds.add(DiasSemana.D);
                        }
                        Posologia p = new PosologiaImpl(sds, cada, gc, ad);
                        Farmaco f = new FarmacoImpl(nombre, tf, p);
                        res.add(f);
                    }
                } while (c.moveToNext());
            }
        }
        //cerramos db
        readableDB.close();
        //devolvemos (en caso de que haya que devolver algo)
        return res;
    }
    public static boolean addFarmacoTomado(Farmaco farmaco, GregorianCalendar time){
        Calendar fecha = new GregorianCalendar();
        boolean res = false;
        int oidp=0;
        SQLiteDatabase readableDB = helper.getReadableDatabase();
        SQLiteDatabase writableDB = helper.getWritableDatabase();
        //si hemos abierto correctamente la db
        if(readableDB!=null) {
            Cursor c = readableDB.rawQuery("SELECT oid_f FROM farmacos WHERE nombre='" + farmaco.getNombre() + "'", null);
            if (c.moveToFirst()) {
                oidp = c.getInt(0);
            }
        }
        if(writableDB!=null){
            writableDB.execSQL("INSERT INTO farmacosTomados (oid_f, horas) VALUES ("+oidp+", "+time.getTimeInMillis()+")");
            res = true;
        }
        return res;
    }

    public static void restartFarmacosTomado(){
        SQLiteDatabase writableDB = helper.getWritableDatabase();
        if(writableDB!=null){
            writableDB.execSQL("DROP TABLE IF EXISTS farmacosTomados");
        }
    }

    /******************************
     POSOLOGIAS
     *****************************/
    public static Posologia getPosologia(Farmaco farmaco){
        List<Farmaco> res = new ArrayList<Farmaco>();
        Boolean l= false,m=false,x=false,j=false,v=false,s=false,d = false;
        Integer cada = 6;
        AdministracionFarmaco ad = null;
        GregorianCalendar gc = null;
        Posologia p = null;

        Calendar fecha = new GregorianCalendar();
        int oidpos=0;
        SQLiteDatabase readableDB = helper.getReadableDatabase();
        //si hemos abierto correctamente la db
        if(readableDB!=null){
            Cursor c = readableDB.rawQuery("SELECT oid_pos FROM farmacos WHERE nombre='"+farmaco.getNombre()+"'", null);
            if(c.moveToFirst()){

                    oidpos = c.getInt(0);
                    Cursor c1 = readableDB.rawQuery("SELECT administracionFarmaco, cada, primera, L, M, X, J, V, S, D FROM posologias where oid_pos="+oidpos+"",null);
                    if(c1.moveToFirst()){
                        ad = AdministracionFarmaco.valueOf(c1.getString(0));
                        cada = c1.getInt(1);
                        gc = new GregorianCalendar();
                        gc.setTimeInMillis(c1.getLong(2));
                        l = c1.getInt(3)==1;
                        m = c1.getInt(4)==1;
                        x = c1.getInt(5)==1;
                        j = c1.getInt(6)==1;
                        v = c1.getInt(7)==1;
                        s = c1.getInt(8)==1;
                        d = c1.getInt(9)==1;

                    }
                    Set<DiasSemana> sds = new HashSet<DiasSemana>();
                    if(l){
                        sds.add(DiasSemana.L);
                    }
                    if(m){
                        sds.add(DiasSemana.M);
                    }
                    if(x){
                        sds.add(DiasSemana.X);
                    }
                    if(j){
                        sds.add(DiasSemana.J);
                    }
                    if(v){
                        sds.add(DiasSemana.V);
                    }
                    if(s){
                        sds.add(DiasSemana.S);
                    }
                    if(d){
                        sds.add(DiasSemana.D);
                    }
                    p = new PosologiaImpl(sds, cada, gc, ad);



            }
        }
        //cerramos db
        readableDB.close();
        //devolvemos (en caso de que haya que devolver algo)
        return p;
    }
    /*public static boolean addPosologia(Farmaco farmaco , Posologia posologia){
        No tiene sentido ya que un farmaco no puede ser creado sin tener posologia
    }*/
    /******************************
     SatOxigeno
     *****************************/
    public static SatOxigeno getSatOxigeno(Descompensacion descompensacion) {
        int oidpos = 0;
        GregorianCalendar gc = null;
        Integer v = null;
        SQLiteDatabase readableDB = helper.getReadableDatabase();
        //si hemos abierto correctamente la db
        if (readableDB != null) {
            Cursor c = readableDB.rawQuery("SELECT oid_so FROM descompensaciones WHERE fecha=" + descompensacion.getFecha().getTimeInMillis() + "", null);
            if (c.moveToFirst()) {

                oidpos = c.getInt(0);
                Cursor c1 = readableDB.rawQuery("SELECT fecha, valor FROM satoxigeno where oid_so=" + oidpos + "", null);
                if (c1.moveToFirst()) {
                    gc = new GregorianCalendar();
                    gc.setTimeInMillis(c1.getLong(0));
                    v = c1.getInt(1);
                }
            }
        }
        SatOxigeno st = new SatOxigenoImpl(v,gc);
        return st;
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
