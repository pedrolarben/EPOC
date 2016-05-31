package amigosdevaro.com.epoc.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import amigosdevaro.com.epoc.DB_SQLite.EpocDB;

/**
 * Created by betipedro on 30/05/2016.
 */
public class AlarmReceiver24 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm worked.", Toast.LENGTH_LONG).show();
        Log.d("bootAlarm","WorksFine");
        EpocDB.restartFarmacosTomado();
    }
}
