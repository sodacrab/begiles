package com.sodacrab.begiles;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.sodacrab.begiles.VarContainer.GlobalVars;
import com.sodacrab.begiles.calculations.StepCalc;

public class MainActivity extends AppCompatActivity {

    private LocationManager myLocationManager;
    private LocationListener locationListener;
    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) findViewById(R.id.textView1);

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
            GlobalVars.lon2 = loc.getLongitude();
            GlobalVars.lat2 = loc.getLatitude();

            double speed = loc.getSpeed();
            double time = loc.getTime();

            double step = StepCalc.measureInMeters(GlobalVars.lon1, GlobalVars.lat1, GlobalVars.lon2, GlobalVars.lat2);
            GlobalVars.lon1 = loc.getLongitude();
            GlobalVars.lat1 = loc.getLatitude();

            GlobalVars.range = GlobalVars.range + step;

            textView1.setText(
                    "Current stats:\n" +
                            "Location: " + GlobalVars.lon1 + ", " + GlobalVars.lat1 + "\n" +
                            "Speed: " + speed + "\n" +
                            "Time: " + time + "\n" +
                            "Step: " + step + "\n" +
                            "Range: " + GlobalVars.range + " (meters)"
            );

            Toast.makeText(getBaseContext(), "Location updated", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(String provider) {}

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    }

}

