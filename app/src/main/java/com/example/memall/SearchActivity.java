package com.example.memall;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.memall.Models.GroceryItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.internal.$Gson$Preconditions;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements ShowAllCategoriesDialog.SelectCategory {
    private static final String TAG = "SearchActivity";
    private EditText searchBar;
    private ImageView btnSearch;
    private RecyclerView recyclerView;
    private BottomNavigationView bottomNavigationView;
    private TextView txtFirstCat, txtSecondCat, txtThirdCat, txtSeeAllCategories;

    private Utils utils;

    private GroceryItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();


        utils = new Utils(this);

        adapter = new GroceryItemAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        initBottomNavigation();
        initThreeTextViews();


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initiateSearch();
            }
        });
        txtSeeAllCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAllCategoriesDialog showAllCategoriesDialog = new ShowAllCategoriesDialog();
                showAllCategoriesDialog.show(getSupportFragmentManager(), "all dialog");
            }
        });

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<GroceryItem> items = utils.searchForItem(String.valueOf(s));
                adapter.setItems(items);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private  void initiateSearch(){
        Log.d(TAG, "initiateSearch: created");

        String text = searchBar.getText().toString();

        ArrayList<GroceryItem> items = utils.searchForItem(text);
        for (GroceryItem item: items){
            utils.increaseUserPoints(item, 3);
        }
        adapter.setItems(items);


    }
    private void initViews () {
        Log.d(TAG, "initViews: started");
        searchBar = (EditText) findViewById(R.id.edtTxtSearchBar);
        btnSearch = (ImageView) findViewById(R.id.btnSearch);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        txtFirstCat = (TextView) findViewById(R.id.firstCategory);
        txtSecondCat = (TextView) findViewById(R.id.secondCategory);
        txtThirdCat = (TextView) findViewById(R.id.thirdCategory);
        txtSeeAllCategories = (TextView) findViewById(R.id.btnAllCategories);
        txtThirdCat.setTextColor(txtSeeAllCategories.getTextColors());
        txtSecondCat.setTextColor(txtSeeAllCategories.getTextColors());
        txtFirstCat.setTextColor(txtSeeAllCategories.getTextColors());
    }

    private void initBottomNavigation(){
        Log.d(TAG, "initBottomNavigation: created");
        bottomNavigationView.setSelectedItemId(R.id.search);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.search:

                        break;
                    case R.id.Cart:
                        Intent intent1 = new Intent(SearchActivity.this,CartActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent1);
                        break;
                    case R.id.homeActivity:
                        Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }

    private void initThreeTextViews(){
        Log.d(TAG, "initThreeTextViews: started");
        ArrayList<String> categories = utils.getThreeCategories();
        switch (categories.size()){
            case 1:
                txtFirstCat.setText(categories.get(0));
                txtSecondCat.setVisibility(View.GONE);
                txtThirdCat.setVisibility(View.GONE);
                break;
            case 2:
                txtFirstCat.setText(categories.get(0));
                txtSecondCat.setText(categories.get(1));
                txtThirdCat.setVisibility(View.GONE);
                break;
            case 3:
                txtFirstCat.setText(categories.get(0));
                txtSecondCat.setText(categories.get(1));
                txtThirdCat.setText(categories.get(2));
                break;
            default:
                break;


        }

        txtFirstCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new  Intent(SearchActivity.this, ShowItemsByCategoryActivity.class);
                intent.putExtra("category",txtFirstCat.getText().toString());
                startActivity(intent);

            }
        });
        txtSecondCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new  Intent(SearchActivity.this, ShowItemsByCategoryActivity.class);
                intent.putExtra("category",txtSecondCat.getText().toString());
                startActivity(intent);
            }
        });
        txtThirdCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new  Intent(SearchActivity.this, ShowItemsByCategoryActivity.class);
                intent.putExtra("category",txtThirdCat.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSelectCategoryResult(String category) {
        Log.d(TAG, "onSelectCategoryResult: category "+ category );
        Intent intent = new Intent(this, ShowItemsByCategoryActivity.class);
        intent.putExtra("category",category);
        startActivity(intent);
    }
}
