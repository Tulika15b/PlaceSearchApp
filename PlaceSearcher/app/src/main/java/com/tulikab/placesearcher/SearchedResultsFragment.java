package com.tulikab.placesearcher;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;
import com.tulikab.placesearcher.controllers.PlaceSearchController;
import com.tulikab.placesearcher.data.Venue;

import java.util.ArrayList;
import java.util.List;

public class SearchedResultsFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {


    private RecyclerView recyclerView;
    TextView emptyTV;
    private RecyclerView.LayoutManager layoutManager;
    private PlaceSearchListAdapter searchListAdapter;
    private List<Venue> searchList;
    private TextView textView;
    private String venueSearchedFor;
    private FloatingActionButton favoritePlaces;
    boolean isRequestInProgress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View parentView = inflater.inflate(R.layout.fragment_search_results, container, false);

        searchList = new ArrayList<>();
        recyclerView = parentView.findViewById(R.id.searchedResult_recycler_view);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        searchListAdapter = new PlaceSearchListAdapter(searchList, this, getActivity());
        recyclerView.setAdapter(searchListAdapter);

        textView = parentView.findViewById(R.id.searchedPlace_tv);


        favoritePlaces = parentView.findViewById(R.id.floating_action_fav_places);
        favoritePlaces.setOnClickListener(this);

        isRequestInProgress = false;

        emptyTV = parentView.findViewById(R.id.empty_view);


        return parentView;

    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null && (args.get("queryParam") != null)) {
           venueSearchedFor = args.get("queryParam").toString();
        }

        textView.setText(venueSearchedFor + textView.getText());

        if(!isRequestInProgress)
            fetchSearchResults(venueSearchedFor);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.floating_action_fav_places:

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("VENUELIST", (ArrayList<? extends Parcelable>) searchList);

                Intent searchedPlacesIntent = new Intent(getContext(), FullScreenMapsActivity.class);
                searchedPlacesIntent.putExtra("extra", bundle);
                startActivity(searchedPlacesIntent);

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position >= 0 && searchList.size() > 0){

            Bundle extra = new Bundle();
            extra.putParcelable("VENUE", searchList.get(position));

            Intent searchedPlacesIntent = new Intent(getContext(), PlaceDetailsActivity.class);
            searchedPlacesIntent.putExtra("extra", extra);
            startActivity(searchedPlacesIntent);
        }
    }

    private void fetchSearchResults(String queryParam){
        isRequestInProgress = true;
        PlaceSearchController searchController = new PlaceSearchController();
        searchController.fetchSearchResult(getActivity(), queryParam, new APIResponseCallback() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onSuccess(List<Venue> venueList) {

                if(searchList != null){
                    searchList.clear();
                }

                if(venueList != null){
                    searchList.addAll(venueList);
                    searchListAdapter.notifyDataSetChanged();
                }

                toggleViewItemsBasedOnResponse();
            }

            @Override
            public void onFailure(String errorCode, String errorReason) {
                isRequestInProgress = false;
                toggleViewItemsBasedOnResponse();
            }
        });
    }

    private void toggleViewItemsBasedOnResponse(){
        if(isAdded()){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (!searchList.isEmpty()) {
                        emptyTV.setVisibility(View.INVISIBLE);
                        recyclerView.setVisibility(View.VISIBLE);
                    } else {
                        emptyTV.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }

    }
}
