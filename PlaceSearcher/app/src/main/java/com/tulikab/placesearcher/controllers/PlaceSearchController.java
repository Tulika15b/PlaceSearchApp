package com.tulikab.placesearcher.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Handler;
import android.support.v4.app.JobIntentService;
import android.text.TextUtils;
import android.util.Log;

import com.tulikab.placesearcher.APIResponseCallback;
import com.tulikab.placesearcher.APIRequestIntentService;
import com.tulikab.placesearcher.PlaceSearchResultReceiver;
import com.tulikab.placesearcher.data.Venue;
import com.tulikab.placesearcher.utils.PlaceSearcherCommonUtils;
import com.tulikab.placesearcher.utils.PlaceSearcherConstants;
import com.tulikab.placesearcher.utils.PlaceSearcherJsonParser;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static android.os.Looper.getMainLooper;

public class PlaceSearchController implements APIResponseCallback {

    APIResponseCallback returnCallback;
    PlaceSearchResultReceiver resultReceiver = new PlaceSearchResultReceiver(new Handler(getMainLooper()), this);
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mSharedPrefEditor;


    public void persistFavoritePlace(Activity activity, Venue venue){
        mSharedPreferences = activity.getSharedPreferences("PlaceSearcher_Fav", 0);
        mSharedPrefEditor = mSharedPreferences.edit();

        final String venueId = "VENUE_ID"+venue.getVenueId();

        Set<String> venueLocationDetails = new HashSet<>();
        venueLocationDetails.add("LATITUDE" + venue.getVenueLatitude());
        venueLocationDetails.add("LONGITUDE" + venue.getVenueLongitude());
        mSharedPrefEditor.putStringSet(venueId, venueLocationDetails);
        mSharedPrefEditor.commit();
    }

    /*public void fetchPersistPlace(){
        Venue venue = new Venue();

        Map<String, ?> allEntries =  mSharedPreferences.getAll();
        for (String lKey : allEntries.keySet()) {
            if (lKey.contains("VENUE_ID")) {
               Set<String> locationData = mSharedPreferences.getStringSet(lKey, null);
               if(locationData != null && locationData.size() > 0){
                   for(String coords : locationData){
                       if(coords.startsWith("LONGITUDE")){

                       }
                   }
               }

            }
        }
    }*/



    public void fetchSearchResult(Context context, String queryParam, APIResponseCallback callback){
        if(!TextUtils.isEmpty(queryParam)){
            sendRequest(context, queryParam);
        }

        returnCallback = callback;

    }

    private void sendRequest(Context context, String queryParam){
        //Constructs url or request for FourSquare API
        //Calls the IntentService to initiate HttpConnection
        String requestUrl = PlaceSearcherCommonUtils.constructUrlForVenueSearch("Seattle",queryParam);

        Intent requestIntent = new Intent(context, APIRequestIntentService.class);
        requestIntent.putExtra(PlaceSearcherConstants.URL_KEY, requestUrl);
        requestIntent.putExtra(PlaceSearcherConstants.RESULT_RECEIVER_KEY, resultReceiver);
        JobIntentService.enqueueWork(context, APIRequestIntentService.class, 1000, requestIntent);

    }


    @Override
    public void onSuccess(String result) {
        Log.d("PS", "SUCCESS");
        PlaceSearcherJsonParser parser = new PlaceSearcherJsonParser();
        List<Venue> venue = parser.jsonParseResponse(result);

        returnCallback.onSuccess(venue);
    }

    @Override
    public void onSuccess(List<Venue> venueList) {

    }

    @Override
    public void onFailure(String errorCode, String errorReason) {
        returnCallback.onFailure("", "");
    }
}
