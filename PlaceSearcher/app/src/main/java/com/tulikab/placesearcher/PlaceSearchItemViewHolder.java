package com.tulikab.placesearcher;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PlaceSearchItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected TextView searchedPlaceName;
    protected TextView searchedPlaceCategory;
    protected TextView searchedPlaceDistanceFromCenter;
    protected ImageView isFavorite;
    protected ImageView searchedPlaceImage;

    public PlaceSearchItemViewHolder(@NonNull View itemView) {
        super(itemView);

        searchedPlaceName = itemView.findViewById(R.id.placeName_tv);
        searchedPlaceCategory = itemView.findViewById(R.id.placeCategory_tv);
        searchedPlaceDistanceFromCenter = itemView.findViewById(R.id.placeDist_tv);
        isFavorite = itemView.findViewById(R.id.placeIsFav_iv);
        searchedPlaceImage = itemView.findViewById(R.id.placeImg_iv);
    }

    @Override
    public void onClick(View v) {

    }
}
