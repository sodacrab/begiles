package com.sodacrab.begiles;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by Iwan on 13.01.2017.
 */

public class PostRequestInstance {

    private ServerRestClient src = new ServerRestClient();
    private RequestParams params = new RequestParams();
    private AsyncHttpClient client = new AsyncHttpClient();
    private AsyncHttpResponseHandler respClient;

    private static void postRequest() {
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
    }
}
