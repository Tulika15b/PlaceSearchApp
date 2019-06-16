package com.tulikab.placesearcher.utils;

import android.app.Activity;
import android.location.Location;
import android.text.TextUtils;
import android.widget.Toast;

public class PlaceSearcherCommonUtils {

    public static String constructUrlForVenueSearch(String near, String query){

        String url = "";

        if(!TextUtils.isEmpty(query)){
            url = PlaceSearcherConstants.fourSquareBaseUrl +
                    PlaceSearcherConstants.venues +
                    PlaceSearcherConstants.search + "?"
                    + "client_id=" + PlaceSearcherConstants.client_Id
                    + "&client_secret=" + PlaceSearcherConstants.client_secret
                    + "&v=" + PlaceSearcherConstants.venueId
                    + "&near=" + PlaceSearcherConstants.nearArea
                    + "&query=" + query;
        }

        return url;

    }

    public static String constructUrlForStaticMap(String longitude, String latitude){
        String url = PlaceSearcherConstants.seattleStaticMapAPI
                +"&markers=color:blue|label:A|"
                +latitude+","
                +longitude;

        return url;

    }

    public static void showToast(final Activity activity, final String message) {
        if (activity != null && !activity.isFinishing()) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public static float distanceFromCoordinates(double lat, double lng){
        double seattleCenterLat = 47.6062;
        double seattleCenterLng = -122.3321;

        Location seattleCenterLocation = new Location("center");
        seattleCenterLocation.setLatitude(seattleCenterLat);
        seattleCenterLocation.setLongitude(seattleCenterLng);

        Location venueLocation = new Location("venue loc");
        venueLocation.setLongitude(lng);
        venueLocation.setLatitude(lat);

        float distance = seattleCenterLocation.distanceTo(venueLocation);
        return distance;
    }


}
