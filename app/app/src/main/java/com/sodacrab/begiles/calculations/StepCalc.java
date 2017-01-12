package com.sodacrab.begiles.calculations;

/**
 * Created by Iwan on 12.01.2017.
 */

public class StepCalc {

    public static double measureInMeters(double lat1, double lon1, double lat2, double lon2) {
        double earthRad = 6378.137;
        double dLat = lat2 * Math.PI / 180 - lat1 * Math.PI / 180;
        double dLon = lon2 * Math.PI / 180 - lon1 * Math.PI / 180;

        double a = Math.sin(dLat/2) * Math.sin(dLat / 2) +
                Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = earthRad * c;

        return d * 1000; // (meters)
    }

    public static double measureInKilometers(double lat1, double lon1, double lat2, double lon2) {
        double earthRad = 6378.137;
        double dLat = lat2 * Math.PI / 180 - lat1 * Math.PI / 180;
        double dLon = lon2 * Math.PI / 180 - lon1 * Math.PI / 180;

        double a = Math.sin(dLat/2) * Math.sin(dLat / 2) +
                Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
                        Math.sin(dLon/2) * Math.sin(dLon/2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = earthRad * c;

        return d * 1000 * 1000;
    }
}
