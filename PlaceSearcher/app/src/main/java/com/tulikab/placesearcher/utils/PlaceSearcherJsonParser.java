package com.tulikab.placesearcher.utils;

import com.tulikab.placesearcher.data.Venue;
import com.tulikab.placesearcher.data.VenueLocation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class PlaceSearcherJsonParser {

    public List<Venue> jsonParseResponse(String response){

        List<Venue> venueList = new ArrayList<>();

        try {

            JSONObject searchResponseObj = new JSONObject(response);
            JSONObject meta = searchResponseObj.getJSONObject(PlaceSearcherConstants.JSON_META_KEY);
            int responseCode = meta.getInt(PlaceSearcherConstants.JSON_CODE_KEY);
            if(responseCode == PlaceSearcherConstants.HTTP_OK){
                JSONObject searchResponse = searchResponseObj.getJSONObject(PlaceSearcherConstants.JSON_RESPONSE_KEY);
                JSONArray searchVenues = searchResponse.getJSONArray(PlaceSearcherConstants.JSON_VENUES_KEY);
                for(int i = 0; i < searchVenues.length(); i++){
                    Venue venue = new Venue();
                    VenueLocation venueLocation = new VenueLocation();

                    JSONObject venueObj = searchVenues.optJSONObject(i);

                    JSONObject venueLoc = venueObj.optJSONObject(PlaceSearcherConstants.JSON_LOCATION_KEY);
                    venueLocation.setAddress(venueLoc.optString(PlaceSearcherConstants.JSON_ADDRESS_KEY));
                    venueLocation.setCrossStreet(venueLoc.optString(PlaceSearcherConstants.JSON_CROSS_STREET_KEY));
                    venueLocation.setLat(venueLoc.optDouble(PlaceSearcherConstants.JSON_LAT_KEY));
                    venueLocation.setLng(venueLoc.optDouble(PlaceSearcherConstants.JSON_LNG_KEY));
                    venueLocation.setPostalCode(venueLoc.optInt(PlaceSearcherConstants.JSON_POSTAL_CODE_KEY));

                    venue.setVenueId(venueObj.optString(PlaceSearcherConstants.JSON_ID_KEY));
                    venue.setVenueName(venueObj.optString(PlaceSearcherConstants.JSON_NAME_KEY));
                    venue.setLocation(venueLocation);
                    venue.setVenueLatitude(venueLocation.getLat());
                    venue.setVenueLongitude(venueLocation.getLng());

                    String iconUrl = "";
                    JSONArray categoriesArr = venueObj.optJSONArray(PlaceSearcherConstants.JSON_CATEGORIES_KEY);
                    for(int j = 0; j < categoriesArr.length(); j++){
                        JSONObject categoryObj = categoriesArr.optJSONObject(j);
                        if(categoryObj.optBoolean(PlaceSearcherConstants.JSON_PRIMARY_KEY)){
                            iconUrl = categoryObj.optJSONObject(PlaceSearcherConstants.JSON_ICON_KEY)
                                    .optString(PlaceSearcherConstants.JSON_PREFIX_KEY)
                                    + PlaceSearcherConstants.ICON_SIZE
                                    + categoryObj.optJSONObject(PlaceSearcherConstants.JSON_ICON_KEY)
                                    .optString(PlaceSearcherConstants.JSON_SUFFIX_KEY);
                            venue.setIconUrl(iconUrl);
                            venue.setPrimaryCategory(categoryObj.optString(PlaceSearcherConstants.JSON_NAME_KEY));
                        }

                    }
                    venueList.add(venue);

                }

            }

        } catch (JSONException | NullPointerException e) {
            e.printStackTrace();
        }

        return venueList;
    }
}
