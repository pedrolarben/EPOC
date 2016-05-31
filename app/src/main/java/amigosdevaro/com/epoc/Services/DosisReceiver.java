package amigosdevaro.com.epoc.Services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import amigosdevaro.com.epoc.DB_SQLite.DbHelper;
import amigosdevaro.com.epoc.DB_SQLite.EpocDB;
import amigosdevaro.com.epoc.MainActivity;
import amigosdevaro.com.epoc.R;
import amigosdevaro.com.epoc.tipos.Farmaco;

/**
 * Created by betipedro on 30/05/2016.
 */
public class DosisReceiver extends BroadcastReceiver {
    public static List<Farmaco> farmacos ;
    @Override
    public void onReceive(Context context, Intent intent) {
        DbHelper helper = new DbHelper(context);
        EpocDB.initEpocDB(helper);
        farmacos= EpocDB.getFarmacosToDisplay();
        Log.d("bootAlarm", "farmaco" + farmacos.size());
        if(farmacos.size()==0){
            return;
        }
        Calendar calendar =  Calendar.getInstance();

        for(Farmaco f : farmacos){
            if(f.getPosologia().getPrimeraDosisHora().get(Calendar.HOUR_OF_DAY)==calendar.get(Calendar.HOUR_OF_DAY)){

                if(f.getPosologia().getPrimeraDosisHora().get(Calendar.MINUTE)==calendar.get(Calendar.MINUTE)){

                    Log.d("bootAlarm", "hay farmaco");
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(context)
                                    .setSmallIcon(R.drawable.pill_white)
                                    .setLargeIcon((((BitmapDrawable)context.getResources()
                                            .getDrawable(R.drawable.icon)).getBitmap()))
                                    .setContentTitle("EasyEpoc")
                                    .setContentText("Tomar Dosis: "+f.getNombre())
                                    .setContentInfo("")
                                    .setTicker("Nueva toma!").setAutoCancel(true);
                    NotificationManager mNotificationManager =
                            (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    Intent notIntent =
                            new Intent(context, MainActivity.class);

                    PendingIntent contIntent =
                            PendingIntent.getActivity(
                                    context, 0, notIntent, 0);

                    mBuilder.setContentIntent(contIntent);
                    mNotificationManager.notify(f.hashCode(), mBuilder.build());
                }
            }
        }
        restartFarmacosTomados(calendar,context);
        //EpocDB.restartFarmacosTomado();
    }
    public void  restartFarmacosTomados(Calendar calendar,Context context){
        if(calendar.get(Calendar.HOUR_OF_DAY)==4){
            EpocDB.restartFarmacosTomado();
            SharedPreferences prefs =
                    context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
            DosisAlarm.actualiza(context,prefs.getBoolean("alarma",true));
        }
    }
}
