package com.example.cvetand.quizdroid;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by cvetand on 3/1/15.
 */
public class AlarmReceiver extends BroadcastReceiver{
    private AlarmManager alarmManager;

    public AlarmReceiver(){
    }

    public void onReceive(Context context, Intent intent){
        String url = intent.getExtras().getString("url");
        Log.i("onRecieve", "alarm should be set");
        Toast.makeText(context, url, Toast.LENGTH_LONG).show();
    }
}
