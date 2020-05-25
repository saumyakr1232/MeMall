package com.example.memall;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memall.Models.GroceryItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";
    private BottomNavigationView bottomNavigationView;

    private Utils utils;

    private RecyclerView newItemsRecView, popularItemsRecView, suggestedItemsRecView;

    private GroceryItemAdapter newItemsAdapter, popularItemsAdapter, suggestedItemsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initViews(view);

        initBottomNavigation();

        utils = new Utils(getActivity());

        initRecViews();

        
        return view;
    }

    
    private void initBottomNavigation(){
        Log.d(TAG, "initBottomNavigation: created");
        bottomNavigationView.setSelectedItemId(R.id.homeActivity);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    //TODO: logic for navigation
                    case R.id.search:
                        //Toast.makeText(getContext(), "search clicked", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(),SearchActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.Cart:
                        //Toast.makeText(getContext(), "cart clicked", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(getActivity(),CartActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.homeActivity:
                        //Toast.makeText(getContext(), "home clicked", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent2);
                        break;
                }
                return true;
            }
        });
    }

    private void initRecViews(){
        Log.d(TAG, "initRecViews: created");
        newItemsAdapter = new GroceryItemAdapter(getActivity());
        popularItemsAdapter = new GroceryItemAdapter(getActivity());
        suggestedItemsAdapter = new GroceryItemAdapter(getActivity());

        newItemsRecView.setAdapter(newItemsAdapter);
        popularItemsRecView.setAdapter(popularItemsAdapter);
        suggestedItemsRecView.setAdapter(suggestedItemsAdapter);

        newItemsRecView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularItemsRecView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        suggestedItemsRecView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        updateRecView();


    }
    private void updateRecView(){
        Log.d(TAG, "updateRecView: created");
        ArrayList<GroceryItem> newItems = utils.getAllItems();

        Comparator<GroceryItem> newItemComparator = new Comparator<GroceryItem>() {
            @Override
            public int compare(GroceryItem o1, GroceryItem o2) {
                return o1.getId() - o2.getId();
            }
        };
        Comparator<GroceryItem> reverseNewItemsComparator = Collections.reverseOrder(newItemComparator);
        Collections.sort(newItems,reverseNewItemsComparator);
        newItemsAdapter.setItems(newItems);

        ArrayList<GroceryItem> popularItems = utils.getAllItems();

        Comparator<GroceryItem> popularityComparator = new Comparator<GroceryItem>() {
            @Override
            public int compare(GroceryItem o1, GroceryItem o2) {
                return compareByPopularity(o1, o2);
            }
        };
        Comparator<GroceryItem> reversePopularityComparator = Collections.reverseOrder(popularityComparator);
        Collections.sort(popularItems, reversePopularityComparator);

        popularItemsAdapter.setItems(popularItems);

        ArrayList<GroceryItem> suggestedItems = utils.getAllItems();
        Comparator<GroceryItem> suggestedItemsComparator = new Comparator<GroceryItem>() {
            @Override
            public int compare(GroceryItem o1, GroceryItem o2) {
                return o1.getUserPoint() - o2.getUserPoint();
            }
        };

        Comparator<GroceryItem> reverseSuggestedItemsComparator = Collections.reverseOrder(suggestedItemsComparator);
        Collections.sort(suggestedItems, reverseSuggestedItemsComparator);
        suggestedItemsAdapter.setItems(suggestedItems);
    }

    @Override
    public void onResume() {
        updateRecView();
        super.onResume();
    }

    private int compareByPopularity(GroceryItem item1, GroceryItem item2){
        Log.d(TAG, "compareByPopularity: created");
        if(item1.getPopularityPoint() > item2.getPopularityPoint()){
            return 1;
        }else if (item1.getPopularityPoint()<item2.getPopularityPoint()){
            return -1;

        }else{
            return 0;
        }
    }



    private void initViews(View view){
        Log.d(TAG, "initViews: created");
        bottomNavigationView = (BottomNavigationView) view.findViewById(R.id.bottomNavigationView);
        newItemsRecView = (RecyclerView) view.findViewById(R.id.newItemsRecView);
        popularItemsRecView = (RecyclerView) view.findViewById(R.id.popularItems);
        suggestedItemsRecView= (RecyclerView)view.findViewById(R.id.suggestedItems);
    }
    
}
