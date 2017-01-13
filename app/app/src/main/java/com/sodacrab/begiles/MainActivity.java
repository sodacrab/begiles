package com.sodacrab.begiles;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings.Secure;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sodacrab.begiles.VarContainer.GlobalVars;
import com.sodacrab.begiles.calculations.StepCalc;

public class MainActivity extends AppCompatActivity {

    private LocationManager myLocationManager;
    private LocationListener locationListener;
    private TextView textView1;

    static Context context;

    private String android_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = (TextView) findViewById(R.id.textView1);

        context = this;

        android_id = Secure.getString(this.getContentResolver(), Secure.ANDROID_ID);

        PrefsHandler.sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        PrefsHandler.prefEditor = PrefsHandler.sharedPref.edit();
        float[] tempPrefsLoader = PrefsHandler.loadPrefs(this);
        GlobalVars.walkedDistance = tempPrefsLoader[0];
        GlobalVars.speed = tempPrefsLoader[1];
        GlobalVars.time = tempPrefsLoader[2];

        Toast.makeText(getBaseContext(), "" + GlobalVars.walkedDistance, Toast.LENGTH_SHORT).show();

        myLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        myLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);

    }

    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {
            double newLon = loc.getLongitude();
            double newLat = loc.getLatitude();

            GlobalVars.speed = loc.getSpeed();
            GlobalVars.time = loc.getTime();

            double step = 0;
            if (GlobalVars.prevLon != 0 && GlobalVars.prevLat != 0) {
                step = StepCalc.measureInMeters(newLon, newLat, GlobalVars.prevLon, GlobalVars.prevLat);
                GlobalVars.walkedDistance = GlobalVars.walkedDistance + step;

            }
            textView1.setText(
                    "Current statistics:\n" +
                            "Current Location: " + newLon + ", " + newLat + "\n" +
                            "Previous location: " + GlobalVars.prevLon + ", " + GlobalVars.prevLat + "\n" +
                            "Speed: " + GlobalVars.speed + "\n" +
                            "Time: " + GlobalVars.time + "\n" +
                            "Step: " + step + " (meters)\n" +
                            "Walked distance: " + GlobalVars.walkedDistance + " (meters)"
            );
            PrefsHandler.savePrefs(getContext(), (float)GlobalVars.walkedDistance, (float)GlobalVars.time, (float)GlobalVars.speed);

            GlobalVars.prevLon = loc.getLongitude();
            GlobalVars.prevLat = loc.getLatitude();

            Toast.makeText(getBaseContext(), "Location updated", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(String provider) {}

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    }
    public static Context getContext() {
        if (context == null) context = new MainActivity();
        return context;
    }
}

