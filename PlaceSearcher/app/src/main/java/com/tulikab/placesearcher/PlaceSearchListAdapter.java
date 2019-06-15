package com.tulikab.placesearcher;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tulikab.placesearcher.data.Venue;

import java.util.List;

public class PlaceSearchListAdapter extends RecyclerView.Adapter<PlaceSearchItemViewHolder> {

    List<Venue> mVenueList;

    public PlaceSearchListAdapter(List<Venue> venues){
        mVenueList = venues;
    }

    @NonNull
    @Override
    public PlaceSearchItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_place, null);
        PlaceSearchItemViewHolder placeSearchItemViewHolder = new PlaceSearchItemViewHolder(view);
        return placeSearchItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceSearchItemViewHolder placeSearchItemViewHolder, int i) {
        Venue placeData = mVenueList.get(i);
        if(placeData != null){
            placeSearchItemViewHolder.searchedPlaceName.setText(placeData.getVenueName());
            //placeSearchItemViewHolder.searchedPlaceCategory.setText(placeData.getVenueCategoryList().get(0).getCategoryName());
            //searchedResultItemViewHolder.searchedPlaceDistanceFromCenter.setText((tring)placeData.getDistanceFromCenter());
           /* if(placeData.isFavorite())
            searchedResultItemViewHolder.isFavorite.setVisibility(View.VISIBLE);
            else
                searchedResultItemViewHolder.isFavorite.setVisibility(View.INVISIBLE);
*/
        }
    }

    @Override
    public int getItemCount() {
        return mVenueList.size();
    }
}
