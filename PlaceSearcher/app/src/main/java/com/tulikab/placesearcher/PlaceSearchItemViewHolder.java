package com.tulikab.placesearcher;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

public class PlaceSearchItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected TextView searchedPlaceName;
    protected TextView searchedPlaceCategory;
    protected TextView searchedPlaceDistanceFromCenter;
    protected ImageView isFavorite;
    protected ImageView searchedPlaceImage;
    private AdapterView.OnItemClickListener onItemClickListener;
    private boolean isRequestInProgress;


    public PlaceSearchItemViewHolder(@NonNull View itemView, AdapterView.OnItemClickListener listener) {
        super(itemView);

        searchedPlaceName = itemView.findViewById(R.id.placeName_tv);
        searchedPlaceName.setOnClickListener(this);

        searchedPlaceCategory = itemView.findViewById(R.id.placeCategory_tv);
        searchedPlaceCategory.setOnClickListener(this);

        searchedPlaceDistanceFromCenter = itemView.findViewById(R.id.placeDist_tv);
        searchedPlaceDistanceFromCenter.setOnClickListener(this);

        isFavorite = itemView.findViewById(R.id.placeIsFav_iv);
        isFavorite.setOnClickListener(this);

        searchedPlaceImage = itemView.findViewById(R.id.placeImg_iv);
        searchedPlaceImage.setOnClickListener(this);

        this.onItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.placeIsFav_iv :
                isFavorite.setImageResource(R.drawable.ic_action_fav);
                return;
        }

        onItemClickListener.onItemClick(null, v, getAdapterPosition(), v.getId());
    }
}
