package com.sodacrab.begiles;

import android.Manifest;
import android.content.Context;
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

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private LocationManager myLocationManager;
    private LocationListener locationListener;
    private TextView textView1;

    private String android_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = (TextView) findViewById(R.id.textView1);

        android_id = Secure.getString(this.getContentResolver(), Secure.ANDROID_ID);

        myLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        myLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);

    }

    private class MyLocationListener implements LocationListener {

        private ServerRestClient src = new ServerRestClient();
        private RequestParams params = new RequestParams();
        private AsyncHttpClient client = new AsyncHttpClient();
        private AsyncHttpResponseHandler respClient;

        @Override
        public void onLocationChanged(Location loc) {
            double newLon = loc.getLongitude();
            double newLat = loc.getLatitude();

            double speed = loc.getSpeed();
            double time = loc.getTime();

            double step = 0;
            if (GlobalVars.prevLon != 0 && GlobalVars.prevLat != 0) {
                step = StepCalc.measureInMeters(newLon, newLat, GlobalVars.prevLon, GlobalVars.prevLat);
                GlobalVars.walkedDistance = GlobalVars.walkedDistance + step;

            }
            textView1.setText(
                    "Current statistics:\n" +
                            "Current Location: " + newLon + ", " + newLat + "\n" +
                            "Previous location: " + GlobalVars.prevLon + ", " + GlobalVars.prevLat + "\n" +
                            "Speed: " + speed + "\n" +
                            "Time: " + time + "\n" +
                            "Step: " + step + " (meters)\n" +
                            "Walked distance: " + GlobalVars.walkedDistance + " (meters)"
            );

            /*
            client.get(GlobalVars.API_URL, new AsyncHttpResponseHandler() {
                @Override
                public void onStart() {
                }
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                }
                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                }
                @Override
                public void onRetry(int retryNo) {
                }
                @Override
                public void onProgress(long bytesWritten, long totalSize) {
                }
                @Override
                public void onFinish() {
                }
            });
            params.put("android_id", android_id);
            params.put("distance_walked", GlobalVars.walkedDistance);
            params.put("time", time);

            src.post(GlobalVars.API_URL, params, respClient);
            */

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

}

