package amigosdevaro.com.epoc.DB_SQLite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import amigosdevaro.com.epoc.tipos.AdministracionFarmaco;
import amigosdevaro.com.epoc.tipos.Descompensacion;
import amigosdevaro.com.epoc.tipos.DescompesacionImpl;
import amigosdevaro.com.epoc.tipos.DiasSemana;
import amigosdevaro.com.epoc.tipos.Farmaco;
import amigosdevaro.com.epoc.tipos.FarmacoImpl;
import amigosdevaro.com.epoc.tipos.Paciente;
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
    public static boolean updateFarmaco(Farmaco fantigo, Farmaco fnuevo){
        SQLiteDatabase writableDB = helper.getWritableDatabase();
        SQLiteDatabase readableDB = helper.getReadableDatabase();
        Boolean res = false;
        int l=0,m=0,x=0,j=0,v=0,s=0,d=0;
        int idpos = 0;
        int idf = 0;
        if(readableDB!=null){
            Cursor c = readableDB.rawQuery(" SELECT oid_pos, oid_f FROM farmacos WHERE nombre='"+fantigo.getNombre()+"' ", null);
            if(c.moveToFirst()){
                idpos = c.getInt(0);
                idf = c.getInt(1);
            }
        }
        if(writableDB!=null){
            if(fnuevo.getPosologia().getDiassemanas().contains(DiasSemana.L)){
                l=1;
            }
            if(fnuevo.getPosologia().getDiassemanas().contains(DiasSemana.M)){
                m=1;
            }
            if(fnuevo.getPosologia().getDiassemanas().contains(DiasSemana.X)){
                x=1;
            }
            if(fnuevo.getPosologia().getDiassemanas().contains(DiasSemana.J)){
                j=1;
            }
            if(fnuevo.getPosologia().getDiassemanas().contains(DiasSemana.V)){
                v=1;
            }
            if(fnuevo.getPosologia().getDiassemanas().contains(DiasSemana.S)){
                s=1;
            }
            if(fnuevo.getPosologia().getDiassemanas().contains(DiasSemana.D)){
                d=1;
            }
            writableDB.execSQL("UPDATE farmacos SET nombre='"+fnuevo.getNombre()+"', tipo='"+fnuevo.getTipo().toString()+"' WHERE oid_f="+idf+" ");
            writableDB.execSQL("UPDATE posologias SET administracionFarmaco='"+fnuevo.getPosologia().getAdministracion().toString()+"', cada="+fnuevo.getPosologia().getCadaCuantosDias()+", primera="+fnuevo.getPosologia().getPrimeraDosisHora().getTimeInMillis()+", L="+l+", M="+m+", X="+x+", J="+j+", V="+v+", S="+s+", D="+d+" WHERE oid_pos="+idpos+" ");
            res = true;
        }
        readableDB.close();
        writableDB.close();
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
                    oid = c.getInt(0);
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
    public static boolean eliminaFarmaco(Farmaco f){
        boolean res = false;
        SQLiteDatabase writableDB = helper.getWritableDatabase();
        if(writableDB!=null){
            writableDB.execSQL("DELETE FROM farmacos WHERE nombre='"+f.getNombre()+"'");
            res = true;
        }
        writableDB.close();
        return res;
    }

    public static List<Farmaco> getFarmacosToday(){
        //init variables
        List<Farmaco> res = new ArrayList<Farmaco>();
        Boolean l= false,m=false,x=false,j=false,v=false,s=false,d = false;
        Integer cada = 6;
        AdministracionFarmaco ad = null;
        GregorianCalendar gc = new GregorianCalendar();
        DiasSemana aux = null;
        Calendar fecha = new GregorianCalendar();
        fecha.setTime(Calendar.getInstance().getTime());
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
                    if(fecha.get(Calendar.DAY_OF_WEEK)==1){
                        aux = DiasSemana.D;
                    }
                    if(fecha.get(Calendar.DAY_OF_WEEK)==2){
                        aux = DiasSemana.L;
                    }
                    if(fecha.get(Calendar.DAY_OF_WEEK)==3){
                        aux = DiasSemana.M;
                    }
                    if(fecha.get(Calendar.DAY_OF_WEEK)==4){
                        aux = DiasSemana.X;
                    }
                    if(fecha.get(Calendar.DAY_OF_WEEK)==5){
                        aux = DiasSemana.J;
                    }
                    if(fecha.get(Calendar.DAY_OF_WEEK)==6){
                        aux = DiasSemana.V;
                    }
                    if(fecha.get(Calendar.DAY_OF_WEEK)==7){
                        aux = DiasSemana.S;
                    }

                    if(p.getDiassemanas().contains(aux)){
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
    public static List<Farmaco> getFarmacosToDisplayAll(){
        List<Farmaco> aux = getFarmacosToday();
        List<Farmaco> res = new ArrayList<Farmaco>();

        for(Farmaco f: aux){
            Farmaco fa = f;
            res.add(f);
            int cont = 1;
            if(f.getPosologia().getCadaCuantosDias()>0){
            for(int i = fa.getPosologia().getPrimeraDosisHora().get(Calendar.HOUR_OF_DAY); i<=24;i+=f.getPosologia().getCadaCuantosDias()){
                GregorianCalendar gc = fa.getPosologia().getPrimeraDosisHora();
                gc.setTimeInMillis(gc.getTimeInMillis()+(cont*3600000*f.getPosologia().getCadaCuantosDias()));
                cont++;
                Posologia p =  f.getPosologia();
                p.setPrimeraDosisHora(gc);
                f.setPosologia(p);
                res.add(f);
            }}
        }
        Collections.sort(res, new Comparator<Farmaco>() {
            public int compare(Farmaco p1, Farmaco p2) {
                return p2.getPosologia().getPrimeraDosisHora().compareTo(p1.getPosologia().getPrimeraDosisHora());
            }
        });


        /*
        for(Farmaco f:aux){
            if(f.getPosologia().getCadaCuantosDias()>0){
                Posologia p = f.getPosologia();
                GregorianCalendar gc = f.getPosologia().getPrimeraDosisHora();

                gc.setTimeInMillis(p.getPrimeraDosisHora().getTimeInMillis()+f.getPosologia().getCadaCuantosDias()*3600000);
                f.getPosologia().setPrimeraDosisHora(gc);
               /* act2 = f.getPosologia().getPrimeraDosisHora();*/
                /*if(act.get(Calendar.DAY_OF_WEEK)==act2.get(Calendar.DAY_OF_WEEK)){
                    res.add(f);
                }*

            }
        }
        Collections.sort(aux, new Comparator<Farmaco>() {
            public int compare(Farmaco p1, Farmaco p2) {
                return p2.getPosologia().getPrimeraDosisHora().compareTo(p1.getPosologia().getPrimeraDosisHora());
            }
        });

        for(Farmaco f:aux){
            if(f.getPosologia().getCadaCuantosDias()>0){
                act2 = f.getPosologia().getPrimeraDosisHora();
                if(act.get(Calendar.DAY_OF_WEEK)==act2.get(Calendar.DAY_OF_WEEK)){
                    res.add(f);
                }
                Posologia p = f.getPosologia();
                GregorianCalendar gc = f.getPosologia().getPrimeraDosisHora();
                gc.setTimeInMillis(p.getPrimeraDosisHora().getTimeInMillis()+f.getPosologia().getCadaCuantosDias()*3600000);
                f.getPosologia().setPrimeraDosisHora(gc);


            }
        }
        Collections.sort(aux, new Comparator<Farmaco>() {
            public int compare(Farmaco p1, Farmaco p2) {
                return p2.getPosologia().getPrimeraDosisHora().compareTo(p1.getPosologia().getPrimeraDosisHora());
            }
        });
        for(Farmaco f:aux){
            if(f.getPosologia().getCadaCuantosDias()>0){
                act2 = f.getPosologia().getPrimeraDosisHora();
                if(act.get(Calendar.DAY_OF_WEEK)==act2.get(Calendar.DAY_OF_WEEK)){
                    res.add(f);
                }
                Posologia p = f.getPosologia();
                GregorianCalendar gc = f.getPosologia().getPrimeraDosisHora();
                gc.setTimeInMillis(p.getPrimeraDosisHora().getTimeInMillis()+f.getPosologia().getCadaCuantosDias()*3600000);
                f.getPosologia().setPrimeraDosisHora(gc);


            }
        }
        Collections.sort(aux, new Comparator<Farmaco>() {
            public int compare(Farmaco p1, Farmaco p2) {
                return p2.getPosologia().getPrimeraDosisHora().compareTo(p1.getPosologia().getPrimeraDosisHora());
            }
        });
        for(Farmaco f:aux){
            if(f.getPosologia().getCadaCuantosDias()>0){
                act2 = f.getPosologia().getPrimeraDosisHora();
                if(act.get(Calendar.DAY_OF_WEEK)==act2.get(Calendar.DAY_OF_WEEK)){
                    res.add(f);
                }
                Posologia p = f.getPosologia();
                GregorianCalendar gc = f.getPosologia().getPrimeraDosisHora();
                gc.setTimeInMillis(p.getPrimeraDosisHora().getTimeInMillis()+f.getPosologia().getCadaCuantosDias()*3600000);
                f.getPosologia().setPrimeraDosisHora(gc);


            }
        }
        Collections.sort(aux, new Comparator<Farmaco>() {
            public int compare(Farmaco p1, Farmaco p2) {
                return p2.getPosologia().getPrimeraDosisHora().compareTo(p1.getPosologia().getPrimeraDosisHora());
            }
        });
        for(Farmaco f:aux){
            if(f.getPosologia().getCadaCuantosDias()>0){
                act2 = f.getPosologia().getPrimeraDosisHora();
                if(act.get(Calendar.DAY_OF_WEEK)==act2.get(Calendar.DAY_OF_WEEK)){
                    res.add(f);
                }
                Posologia p = f.getPosologia();
                GregorianCalendar gc = f.getPosologia().getPrimeraDosisHora();
                gc.setTimeInMillis(p.getPrimeraDosisHora().getTimeInMillis()+f.getPosologia().getCadaCuantosDias()*3600000);
                f.getPosologia().setPrimeraDosisHora(gc);


            }
        }
        Collections.sort(aux, new Comparator<Farmaco>() {
            public int compare(Farmaco p1, Farmaco p2) {
                return p2.getPosologia().getPrimeraDosisHora().compareTo(p1.getPosologia().getPrimeraDosisHora());
            }
        });
        for(Farmaco f:aux){
            if(f.getPosologia().getCadaCuantosDias()>0){
                act2 = f.getPosologia().getPrimeraDosisHora();
                if(act.get(Calendar.DAY_OF_WEEK)==act2.get(Calendar.DAY_OF_WEEK)){
                    res.add(f);
                }
                Posologia p = f.getPosologia();
                GregorianCalendar gc = f.getPosologia().getPrimeraDosisHora();
                gc.setTimeInMillis(p.getPrimeraDosisHora().getTimeInMillis()+f.getPosologia().getCadaCuantosDias()*3600000);
                f.getPosologia().setPrimeraDosisHora(gc);


            }
        }*/
        return res;
    }
    public static List<Farmaco> getFarmacosToDisplay(){
        List<Farmaco> aux = getFarmacosToDisplayAll();
        Calendar gc = new GregorianCalendar();
        gc.setTime(Calendar.getInstance().getTime());
        for(int i = 0; i < aux.size(); i++){
            if(gc.compareTo(aux.get(i).getPosologia().getPrimeraDosisHora())>=0){
                aux.remove(i);
            }
        }
        return aux;
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
            writableDB.execSQL("INSERT INTO farmacosTomados (oid_f, hora) VALUES ("+oidp+", "+time.getTimeInMillis()+")");
            res = true;
        }
        readableDB.close();
        writableDB.close();
        return res;
    }

    public static void restartFarmacosTomado(){
        SQLiteDatabase writableDB = helper.getWritableDatabase();
        if(writableDB!=null){
            writableDB.execSQL("DROP TABLE IF EXISTS farmacosTomados");
        }
        writableDB.close();
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
        readableDB.close();
        return st;
    }





    public static List<SatOxigeno> getSatOxigeno(){
        GregorianCalendar gc = null;
        Integer v = null;
        List<SatOxigeno> res = new ArrayList<SatOxigeno>();
        SQLiteDatabase readableDB = helper.getReadableDatabase();
        if(readableDB!=null){

            Cursor c1 = readableDB.rawQuery("SELECT fecha, valor FROM satoxigeno", null);
            if(c1.moveToFirst()){
                do{
                    gc = new GregorianCalendar();
                    gc.setTimeInMillis(c1.getLong(0));
                    SatOxigeno aux = new SatOxigenoImpl(c1.getInt(1), gc);
                    res.add(aux);
                }while(c1.moveToNext());
            }
        }
        readableDB.close();
        return res;
    }
    public static boolean addSatOxigeno(Descompensacion descompensacion  ,  SatOxigeno satOxigeno){

         return false;
    }

    /******************************
     DESCOMPENSACIONES
     *****************************/
    public static List<Descompensacion> getDescompensaciones(){
        //init variables
        List<Descompensacion> res = new ArrayList<Descompensacion>();
        GregorianCalendar fecha = null;
        GregorianCalendar fecha1 = null;
        Integer valor = 0;
        Integer disnea = null;
        Integer oidso = 0;
        Boolean tos = false;
        Double fev = 0.0;
        Boolean expec = false;
        Boolean ruidosRespiratorios = false;
        Boolean hinchaxonTobillos = false;
        Boolean dolorTobillos = false;
        Boolean dolorPecho = false;
        Boolean estarIrritable = false;
        Boolean desorientacion = false;
        Boolean dolorCabeza = false;
        Boolean somnolencia = false;
        Double fiebre = 0.0;
        int oidpos=0;
        //abrimos bd:
        SQLiteDatabase readableDB = helper.getReadableDatabase();
        //si hemos abierto correctamente la db
        if(readableDB!=null){
            Cursor c = readableDB.rawQuery("SELECT oid_so, fecha, disrea, tos, fev, expectoracion, ruidosRespiratorios, hinchazonTobillos, dolorPecho, estarIrritable, desorientacion, dolorCabeza, somnolencia, fiebre FROM descompensaciones WHERE oid_p="+idPaciente+"", null);
            if(c.moveToFirst()){
                do {
                    oidso = c.getInt(0);
                    fecha.setTimeInMillis(c.getLong(1));
                    disnea = c.getInt(2);
                    tos = c.getInt(3)==1;
                    fev = c.getDouble(4);
                    expec = c.getInt(5)==1;
                    ruidosRespiratorios = c.getInt(6)==1;
                    hinchaxonTobillos = c.getInt(7)==1;
                    dolorPecho = c.getInt(8)==1;
                    estarIrritable = c.getInt(9)==1;
                    desorientacion = c.getInt(10)==1;
                    dolorCabeza = c.getInt(11)==1;
                    somnolencia = c.getInt(12)==1;
                    fiebre = c.getDouble(13);
                    Cursor c1 = readableDB.rawQuery("SELECT fecha, valor FROM satoxigeno where oid_so="+oidso+"",null);
                    if(c1.moveToFirst()){
                        fecha1.setTimeInMillis(c1.getLong(0));
                        valor = c1.getInt(1);

                    }

                    SatOxigeno so = new SatOxigenoImpl(valor, fecha1);
                    Descompensacion desc = new DescompesacionImpl(fecha);
                    desc.setFiebre(fiebre);
                    desc.setDisnea(disnea);
                    desc.setTos(tos);
                    desc.setFev(fev);
                    desc.setExpectoracion(expec);
                    desc.setRuidosRespiratorios(ruidosRespiratorios);
                    desc.setHinchazonTobillos(hinchaxonTobillos);
                   // desc.setDolorPecho(dolorPecho);
                    desc.setEstarIrritable(estarIrritable);
                    desc.setDesorientacion(desorientacion);
                    desc.setDolorCabeza(dolorCabeza);
                    desc.setSomnolencia(somnolencia);
                    desc.setOxigeno(so);
                    res.add(desc);
                }while(c.moveToNext());
            }
        }
        //cerramos db
        readableDB.close();
        //devolvemos (en caso de que haya que devolver algo)
        return res;
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



