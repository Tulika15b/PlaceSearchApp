package com.tulikab.placesearcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.tulikab.placesearcher.data.Venue;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements AdapterView.OnItemClickListener {

    android.support.v7.widget.SearchView searchView;
    List<Venue> searchList;
    String data[]={"Coffee", "Tea", "Pizza", "Doctor", "Hospital", "School", "Cafe", "Movie", "Hotels"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_search, container, false);

        searchView = parentView.findViewById(R.id.searchPlaces_etv);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("Enter Search Query");
        searchView.setIconifiedByDefault(false);
        searchView.setFocusable(false);
        final SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, data);
        searchAutoComplete.setAdapter(dataAdapter);

        // Listen to search view item on click event.
        searchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long id) {
                String queryString=(String)adapterView.getItemAtPosition(itemIndex);
                searchAutoComplete.setText("" + queryString);
             }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                searchView.setSubmitButtonEnabled(false);

                SearchedResultsFragment nextFrag= new SearchedResultsFragment();

                Bundle args = new Bundle();
                args.putString("queryParam", query);
                nextFrag.setArguments(args);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextFrag,"search")
                        .addToBackStack(null)
                        .commit();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        searchList = new ArrayList<>();
        return parentView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch(view.getId()){
            case R.id.search_src_text :
                Log.d("PS", "Selected Item");
        }
    }
}
