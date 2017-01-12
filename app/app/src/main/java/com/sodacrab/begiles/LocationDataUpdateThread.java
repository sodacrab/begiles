package com.sodacrab.begiles;

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
