package com.tulikab.placesearcher;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;

public class PlaceSearchResultReceiver extends ResultReceiver {

    private PlaceSearchAPICallback placeSearchAPICallback;
    /**
     * Create a new ResultReceive to receive results.  Your
     * {@link #onReceiveResult} method will be called from the thread running
     * <var>handler</var> if given, or from an arbitrary thread if null.
     *
     * @param handler
     */
    public PlaceSearchResultReceiver(Handler handler, PlaceSearchAPICallback callback) {
        super(handler);
        placeSearchAPICallback = callback;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);

        if(resultData != null){
            String response = resultData.getString("RESPONSE");
            Log.d("PA", response);
            if(response != null)
                placeSearchAPICallback.onSuccess(response);
            else
                placeSearchAPICallback.onFailure("", "");
        }
    }
}
