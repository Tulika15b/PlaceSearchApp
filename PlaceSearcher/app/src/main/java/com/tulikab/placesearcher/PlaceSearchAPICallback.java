package com.tulikab.placesearcher;

import com.tulikab.placesearcher.data.Venue;

import java.util.List;

public interface PlaceSearchAPICallback {

    void onSuccess(String result);

    void onSuccess(List<Venue> venueList);

    void onFailure(String errorCode, String errorReason);
}
