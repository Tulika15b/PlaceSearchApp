package com.tulikab.placesearcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.tulikab.placesearcher.data.Venue;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    SearchView searchView;
    List<Venue> searchList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_search, container, false);

        searchView = parentView.findViewById(R.id.searchPlaces_etv);
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.setSubmitButtonEnabled(false);

                SearchedResultsFragment nextFrag= new SearchedResultsFragment();

                Bundle args = new Bundle();
                args.putString("queryParam", query);
                nextFrag.setArguments(args);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();


               /* Intent intent = new Intent(getApplicationContext(), SearchedResultsActivity.class);
                intent.putExtra("QUERYSTR", query);
                startActivity(intent);*/
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchList = new ArrayList<>();

        return parentView;
    }
}
