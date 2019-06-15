package com.tulikab.placesearcher.utils;

import com.tulikab.placesearcher.data.Venue;
import com.tulikab.placesearcher.data.VenueCategory;
import com.tulikab.placesearcher.data.VenueLocation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlaceSearcherJsonParser {

    //TODO::
    public List<Venue> jsonParseResponse(String response){

        List<Venue> venueList = new ArrayList<>();

        try {
            JSONObject searchResponseObj = new JSONObject(response);
            JSONObject meta = searchResponseObj.getJSONObject("meta");
            int responseCode = meta.getInt("code");
            if(responseCode == PlaceSearcherConstants.HTTP_OK){
                JSONObject searchResponse = searchResponseObj.getJSONObject("response");
                JSONArray searchVenues = searchResponse.getJSONArray("venues");
                for(int i = 0; i < searchVenues.length(); i++){
                    Venue venue = new Venue();
                    VenueLocation venueLocation = new VenueLocation();

                    JSONObject venueObj = searchVenues.optJSONObject(i);

                    JSONObject venueLoc = venueObj.optJSONObject("location");
                    venueLocation.setAddress(venueLoc.optString("address"));
                    venueLocation.setCrossStreet(venueLoc.optString("crossStreet"));
                    venueLocation.setLat(venueLoc.optLong("lat"));
                    venueLocation.setLng(venueLoc.optLong("lng"));
                    venueLocation.setPostalCode(venueLoc.optInt("postalCode"));

                    venue.setVenueId(venueObj.optString("id"));
                    venue.setVenueName(venueObj.optString("name"));
                    venue.setLocation(venueLocation);

                    venueList.add(venue);

                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return venueList;
    }
}
