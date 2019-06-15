package com.tulikab.placesearcher.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Venue implements Parcelable {

    private String venueId;
    private String venueName;
    private VenueLocation location;
    private String venueLatitude;
    private String venueLongitude;
    private List<VenueCategory> venueCategoryList;
    private boolean isFavorite;
    private String iconUrl;

    public Venue(){

    }

    public Venue(Parcel in) {
        venueId = in.readString();
        venueName = in.readString();
        venueLatitude = in.readString();
        venueLongitude = in.readString();
        isFavorite = in.readByte() != 0;
        iconUrl = in.readString();
    }

    public static final Creator<Venue> CREATOR = new Creator<Venue>() {
        @Override
        public Venue createFromParcel(Parcel in) {
            return new Venue(in);
        }

        @Override
        public Venue[] newArray(int size) {
            return new Venue[size];
        }
    };

    public String getVenueId() {
        return venueId;
    }

    public void setVenueId(String venueId) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.venueId);
        dest.writeString(this.iconUrl);
        dest.writeString(this.venueName);
        dest.writeString(this.venueLatitude);
        dest.writeString(this.venueLongitude);

    }

    public String getVenueLatitude() {
        return venueLatitude;
    }

    public void setVenueLatitude(String venueLatitude) {
        this.venueLatitude = venueLatitude;
    }

    public String getVenueLongitude() {
        return venueLongitude;
    }

    public void setVenueLongitude(String venueLongitude) {
        this.venueLongitude = venueLongitude;
    }
}
