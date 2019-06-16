package com.tulikab.placesearcher;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tulikab.placesearcher.data.Venue;
import com.tulikab.placesearcher.utils.PlaceSearcherCommonUtils;

import java.util.List;

public class PlaceSearchListAdapter extends RecyclerView.Adapter<PlaceSearchItemViewHolder> {

    List<Venue> mVenueList;
    private Activity mActivity;
    private AdapterView.OnItemClickListener mOnItemClickListener;

    public PlaceSearchListAdapter(List<Venue> venues, AdapterView.OnItemClickListener onItemClickListener, Activity activity){
        mVenueList = venues;
        mOnItemClickListener = onItemClickListener;
        mActivity = activity;
    }

    @NonNull
    @Override
    public PlaceSearchItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_place, null);
        PlaceSearchItemViewHolder placeSearchItemViewHolder = new PlaceSearchItemViewHolder(view, mOnItemClickListener);
        return placeSearchItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceSearchItemViewHolder placeSearchItemViewHolder, int i) {
        Venue placeData = mVenueList.get(i);
        if(placeData != null){
            placeSearchItemViewHolder.searchedPlaceName.setText(placeData.getVenueName());
            placeSearchItemViewHolder.searchedPlaceCategory.setText(placeData.getPrimaryCategory());
            Float distance = PlaceSearcherCommonUtils.distanceFromCoordinates(placeData.getVenueLatitude(), placeData.getVenueLongitude());
            placeSearchItemViewHolder.searchedPlaceDistanceFromCenter.setText(distance.toString() + "meters from Seattle center");
            if(placeData.getIconUrl() != null)
            Picasso.with(mActivity).load(placeData.getIconUrl()).into(placeSearchItemViewHolder.searchedPlaceImage);
        }
    }

    @Override
    public int getItemCount() {
        return mVenueList.size();
    }
}
