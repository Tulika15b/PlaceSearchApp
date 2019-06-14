package com.tulikab.placesearcher.data;

import java.util.List;

public class Venue {

    private int venueId;
    private String venueName;
    private VenueLocation location;
    private List<VenueCategory> venueCategoryList;
    private boolean isFavorite;
    private String iconUrl;


    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public VenueLocation getLocation() {
        return location;
    }

    public void setLocation(VenueLocation location) {
        this.location = location;
    }

    public List<VenueCategory> getVenueCategoryList() {
        return venueCategoryList;
    }

    public void setVenueCategoryList(List<VenueCategory> venueCategoryList) {
        this.venueCategoryList = venueCategoryList;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
