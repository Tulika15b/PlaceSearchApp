package com.tulikab.placesearcher;

import com.tulikab.placesearcher.data.Venue;

import java.util.List;

public interface APIResponseCallback {

    void onSuccess(String result);

    void onSuccess(List<Venue> venueList);

    void onFailure(String errorCode, String errorReason);
}
