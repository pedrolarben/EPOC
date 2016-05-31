package amigosdevaro.com.epoc.Services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by betipedro on 30/05/2016.
 */
public class BootReceiver extends BroadcastReceiver {
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("bootReceiver","YEAH1");

            // Set the alarm here.
           /* if(PendingIntent.getBroadcast(context, 0,
                    new Intent(context,DosisReceiver.class),
                    PendingIntent.FLAG_NO_CREATE)==null){
              }*/
        SharedPreferences prefs =
                context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        DosisAlarm.actualiza(context,prefs.getBoolean("alarm",true));


    }
}
