package com.tulikab.placesearcher.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Venue implements Parcelable {

    private String venueId;
    private String venueName;
    private VenueLocation location;
    private double venueLatitude;
    private double venueLongitude;
    private List<VenueCategory> venueCategoryList;
    private String isFavorite;
    private String iconUrl;
    private String primaryCategory;

    public Venue(){

    }

    public Venue(Parcel in) {
        venueId = in.readString();
        venueName = in.readString();
        venueLatitude = in.readDouble();
        venueLongitude = in.readDouble();
        //isFavorite = in.readByte() != 0;
        isFavorite = in.readString();
        iconUrl = in.readString();
        primaryCategory = in.readString();
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

    public String isFavorite() {
        return isFavorite;
    }

    public void setFavorite(String favorite) {
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
        dest.writeString(this.venueName);
        dest.writeDouble(this.venueLatitude);
        dest.writeDouble(this.venueLongitude);
        dest.writeString(this.isFavorite);
        dest.writeString(this.iconUrl);
        dest.writeString(this.primaryCategory);

    }

    public double getVenueLatitude() {
        return venueLatitude;
    }

    public void setVenueLatitude(double venueLatitude) {
        this.venueLatitude = venueLatitude;
    }

    public double getVenueLongitude() {
        return venueLongitude;
    }

    public void setVenueLongitude(double venueLongitude) {
        this.venueLongitude = venueLongitude;
    }

    public String getPrimaryCategory() {
        return primaryCategory;
    }

    public void setPrimaryCategory(String primaryCategory) {
        this.primaryCategory = primaryCategory;
    }
}
