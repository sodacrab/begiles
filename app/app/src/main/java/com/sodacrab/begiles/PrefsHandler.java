package com.sodacrab.begiles;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Iwan on 13.01.2017.
 */

public class PrefsHandler {

    public static SharedPreferences sharedPref;
    public static SharedPreferences.Editor prefEditor;

    public static void savePrefs(Context con, float data1, float data2, float data3) {
        prefEditor.putFloat(con.getString(R.string.distance), data1);
        prefEditor.putFloat(con.getString(R.string.time), data2);
        prefEditor.putFloat(con.getString(R.string.speed), data3);
        prefEditor.commit();
    }
    public static float[] loadPrefs(Context con) {
        float[] temp = new float[3];
        int defaultValue = 0;
        temp[0] = sharedPref.getFloat(con.getString(R.string.distance), defaultValue);
        temp[1] = sharedPref.getFloat(con.getString(R.string.time), defaultValue);
        temp[2] = sharedPref.getFloat(con.getString(R.string.speed), defaultValue);
        return temp;
    }
}
