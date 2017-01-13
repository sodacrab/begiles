package com.sodacrab.begiles;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Iwan on 13.01.2017.
 */

public class PrefsHandler {

    public static SharedPreferences sharedPref;
    public static SharedPreferences.Editor prefEditor;

    private static int defaultValue = 0;

    public static void savePrefs(float data1, float data2, float data3) {
        prefEditor.putFloat("distance", sharedPref.getFloat("distance", defaultValue) + data1);
        prefEditor.putFloat("speed", sharedPref.getFloat("speed", defaultValue) + data2);
        prefEditor.putFloat("time", sharedPref.getFloat("time", defaultValue) + data3);
        prefEditor.commit();
    }
    public static float[] loadPrefs() {
        float[] temp = new float[3];
        temp[0] = sharedPref.getFloat("distance" , defaultValue);
        temp[1] = sharedPref.getFloat("time", defaultValue);
        temp[2] = sharedPref.getFloat("speed", defaultValue);
        return temp;
    }
}
