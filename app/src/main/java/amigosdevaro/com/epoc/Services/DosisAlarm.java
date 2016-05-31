package amigosdevaro.com.epoc.Services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import java.util.Calendar;

import amigosdevaro.com.epoc.DB_SQLite.DbHelper;
import amigosdevaro.com.epoc.DB_SQLite.EpocDB;

/**
 * Created by betipedro on 30/05/2016.
 */
public  class DosisAlarm {
    public static PendingIntent pendingIntent;
    public static  AlarmManager alarmManager;
    private static void initAlarm(Context context){
        //ALARM
        Log.d("boot","######min######");
       alarmManager=(AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        Intent intent = new Intent(context, DosisReceiver.class);
       pendingIntent = PendingIntent.getBroadcast(context, 1000, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(),
                1 * 10 * 1000,
                pendingIntent);
        Log.d("boot", "######min*#####");
    }
    public static void cancelAlarm(Context context){
        Log.d("boot","######cancel######");
        if(alarmManager!=null){
            alarmManager.cancel(pendingIntent);
            Log.d("boot", "######cancel*#####");
        }
    }
    private static void setTimePeriod(Context context){
        Log.d("boot","######24h######");
        Calendar calendar = Calendar.getInstance();
        if(calendar.get(Calendar.HOUR_OF_DAY)>=4){
            calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+1);
        }
        calendar.set(Calendar.HOUR_OF_DAY,4);
        calendar.set(Calendar.MINUTE,0);
        alarmManager=(AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24 * 60 * 1000, pendingIntent);
        Log.d("boot", "######24h*#####");
    }
    public static void actualiza(Context context,boolean pref){
        DbHelper helper = new DbHelper(context);
        EpocDB.initEpocDB(helper);
        if(pref==false){
            cancelAlarm(context);
            return;
        }
        if(EpocDB.getFarmacosToDisplay().size()>0){
            initAlarm(context);
        }else{
            setTimePeriod(context);
        }
    }

}
