package com.sodacrab.begiles;

import com.loopj.android.http.*;
import com.sodacrab.begiles.VarContainer.GlobalVars;

/**
 * Created by Iwan on 13.01.2017.
 */

public class ServerRestClient {

    // Based on this: http://loopj.com/android-async-http/

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return GlobalVars.API_URL + relativeUrl;
    }
}
