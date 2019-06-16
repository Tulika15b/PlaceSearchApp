package com.tulikab.placesearcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tulikab.placesearcher.controllers.PlaceSearchController;
import com.tulikab.placesearcher.data.Venue;
import com.tulikab.placesearcher.utils.PlaceSearcherCommonUtils;
import com.tulikab.placesearcher.utils.PlaceSearcherConstants;

public class PlaceDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView staticMap;
    ImageView iconImage;
    TextView placeName;
    TextView placeAddress;
    TextView placeCategory;
    TextView placeCoordinates;
    Venue venue;
    FloatingActionButton favFab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_item_details);

        venue = new Venue();
        staticMap = findViewById(R.id.staticMap_iv);
        iconImage = findViewById(R.id.search_item_icon);
        placeName = findViewById(R.id.place_name);
        placeAddress = findViewById(R.id.place_address);
        placeCategory = findViewById(R.id.place_category);
        placeCoordinates = findViewById(R.id.place_coordinates);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bundle extra = extras.getBundle("extra");

            if (extra != null) {
                venue = extra.getParcelable("VENUE");
                fetchStaticMapImage(venue);
                placeName.setText("Name : " + venue.getVenueName());
                if(venue.getLocation() != null)
                    placeAddress.setText("Located at " + venue.getLocation().getAddress() + venue.getLocation().getCrossStreet() + venue.getLocation().getCountryCode());
                placeCategory.setText("Category :" + venue.getPrimaryCategory());
                placeCoordinates.setText(venue.getVenueLatitude() + "," + venue.getVenueLongitude());

            }
        }

        favFab = findViewById(R.id.fav_fab);
        favFab.setOnClickListener(this);

    }

    public void fetchStaticMapImage(Venue venue){

        String staticMapUrl = PlaceSearcherConstants.seattleStaticMapAPI;
        String iconUrl = "";

        staticMapUrl += "&markers=color:red|label:A|" +venue.getVenueLatitude()+"," +venue.getVenueLongitude();

        iconUrl = venue.getIconUrl();
        Log.d("PS","ICON URL"+iconUrl );

        Log.d("PS","MAP URL"+staticMapUrl );
        Picasso.with(this).load(staticMapUrl).into(staticMap);
        Picasso.with(this).load(iconUrl).into(iconImage);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.fav_fab :
                Toast.makeText(getApplicationContext(), "Added to Your Favorites!!", Toast.LENGTH_LONG).show();
                PlaceSearchController controller = new PlaceSearchController();
                controller.persistFavoritePlace(this, venue);
        }
    }
}
