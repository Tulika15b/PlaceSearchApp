package com.tulikab.placesearcher;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tulikab.placesearcher.data.Venue;
import com.tulikab.placesearcher.data.VenueLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FullScreenMapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    Map<String, VenueLocation> favoriteVenueList;
    List<Venue> venueList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        favoriteVenueList = new HashMap<>();
        //fetchFavoritePlaces();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bundle extra = extras.getBundle("extra");
            if (extra != null) {
                venueList = extra.getParcelableArrayList("VENUELIST");
            }
        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Seattle and move the camera
        LatLng seattle = new LatLng(47.6062, -122.3321);
        mMap.addMarker(new MarkerOptions().position(seattle).title("Marker in Seattle"));

        mMap.setOnInfoWindowClickListener(this);
        markSearchedPlaces(venueList);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(seattle));
    }

    private void markSearchedPlaces(List<Venue> venues){
        if(venues != null && venues.size() > 0){
            for(Venue venue: venues){
                LatLng latLng = new LatLng(venue.getVenueLatitude(), venue.getVenueLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title(venue.getVenueName()));
            }
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Bundle extra = new Bundle();

        for(Venue venue : venueList){
            if(venue.getVenueName().equals(marker.getTitle())){
                extra.putParcelable("VENUE", venue);
                Intent intent = new Intent(this, PlaceDetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("extra", extra);
                startActivity(intent);
            }
        }
    }
}
