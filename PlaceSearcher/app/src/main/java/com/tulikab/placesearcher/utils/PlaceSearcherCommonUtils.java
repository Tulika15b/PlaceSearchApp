package com.tulikab.placesearcher.utils;

import android.text.TextUtils;

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
                    + "&near=" + PlaceSearcherConstants.nearArea;
        }

        return url;

    }


}
