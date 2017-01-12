package com.myowndev.runforestrun;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by Iwan on 12.01.2017.
 */

public class LocationDataUpdateThread extends Thread {

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1 * 1000);



            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
