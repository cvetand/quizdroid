package com.example.cvetand.quizdroid;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class Preference extends PreferenceActivity {
    private SharedPreferences preferences;
    private SharedPreferences.OnSharedPreferenceChangeListener listener;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.activity_preference);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        updateSummaries();
        registerPrefListener();


    }

    private void registerPrefListener(){
        listener = new SharedPreferences.OnSharedPreferenceChangeListener(){
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key){
                updateSummaries();
                startAlarm();
            }
        };
        preferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public void updateSummaries(){
        EditTextPreference serverURL = (EditTextPreference) findPreference("url");
        EditTextPreference intervalText = (EditTextPreference) findPreference("interval");
        serverURL.setSummary(serverURL.getText());
        intervalText.setSummary("Every "+intervalText.getText() + " minute(s)");
    }

    public void startAlarm(){

        String url = preferences.getString("url", "www.fakeURL.com");
        int interval = Integer.parseInt(preferences.getString("interval", "15")) * 60000;

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent intentToDownload = new Intent(Preference.this, AlarmReceiver.class);
        intentToDownload.putExtra("url", url);
        intentToDownload.putExtra("interval", interval);
        Log.i("startAlarm", "URL: "+url);
        Log.i("startAlarm", "Interval "+interval);
        pendingIntent = PendingIntent.getBroadcast(Preference.this, 0, intentToDownload, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), interval, pendingIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preference, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
