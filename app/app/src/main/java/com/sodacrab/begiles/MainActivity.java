package com.sodacrab.begiles;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

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
            String longitude = "Longitude: " + loc.getLongitude();
            String latitude = "Latitude: " + loc.getLatitude();
            Toast.makeText(getBaseContext(), "Location updated", Toast.LENGTH_SHORT).show();

            textView1.setText(
                    "Location: " + longitude + ":" + latitude + "\n" +
                            "Speed: " + "\n" +
                            "Time: " + "\n" +
                            "Range: " + "\n");
        }

        @Override
        public void onProviderDisabled(String provider) {}

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    }

}
