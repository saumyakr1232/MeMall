package com.example.memall;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.memall.Models.GroceryItem;
import com.example.memall.Models.Order;

import java.util.ArrayList;

public class CartFirstFragment extends Fragment implements CartRecViewAdapter.GetTotalPrice,
CartRecViewAdapter.DeleteCartItem{
    private static final String TAG = "CartFirstFragment";

    private TextView txtPrice, txtNoItem;
    private RecyclerView recyclerView;
    private Button btnNext;

    private double totalPrice =0;
    private ArrayList<Integer> items;

    private Utils utils;

    private CartRecViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_cart, container, false);

        initViews(view);

        items = new ArrayList<>();

        utils = new Utils(getActivity());


        initRecView();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order order = new Order();
                order.setTotalPrice(totalPrice);
                order.setItems(items);
                Bundle bundle = new Bundle();
                bundle.putParcelable("order", order);
                CartSecondFragment cartSecondFragment = new CartSecondFragment();
                cartSecondFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.in, R.anim.out)
                        .replace(R.id.fragment_container, cartSecondFragment).commit();
            }
        });

        return view;
    }

    private void initViews(View view) {
        Log.d(TAG, "initViews: started");

        txtPrice = (TextView) view.findViewById(R.id.txtSum);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        btnNext = (Button) view.findViewById(R.id.btnNext);
        txtNoItem = (TextView) view.findViewById(R.id.txtNoItem);

    }

    private void initRecView(){
        Log.d(TAG, "initRecView: started");
        adapter = new CartRecViewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Utils utils = new Utils(getActivity());
        ArrayList<Integer> itemIds = utils.getCartItems();
        if (null!=itemIds) {
            ArrayList<GroceryItem> items = utils.getItemsByIds(itemIds);

            if(items.size() == 0){
                btnNext.setVisibility(View.GONE);
                btnNext.setEnabled(false);
                recyclerView.setVisibility(View.GONE);
                txtNoItem.setVisibility(View.VISIBLE);

            }else{
                btnNext.setVisibility(View.VISIBLE);
                btnNext.setEnabled(true);
                recyclerView.setVisibility(View.VISIBLE);
                txtNoItem.setVisibility(View.GONE);

            }
            this.items = itemIds;
            adapter.setItems(items);
        }

    }

    @Override
    public void onGettingTotalPriceResult(double price) {
        Log.d(TAG, "onGettingTotalPriceResult: totalPrice "+price);
        txtPrice.setText(String.valueOf(price));
        this.totalPrice = price;
    }

    @Override
    public void onDeletingResult(GroceryItem item) {
        Log.d(TAG, "onDeletingResult: attempt to delete :"+ item.toString());
        ArrayList<Integer> itemIds = new ArrayList<>();
        itemIds.add(item.getId());
        ArrayList<GroceryItem> items = utils.getItemsByIds(itemIds);
        if(items.size()>0){
            ArrayList<Integer> newItemsIds = utils.deleteCartItem(items.get(0));
            if(newItemsIds.size() == 0){
                btnNext.setVisibility(View.GONE);
                btnNext.setEnabled(false);
                recyclerView.setVisibility(View.GONE);
                txtNoItem.setVisibility(View.VISIBLE);

            }else{
                btnNext.setVisibility(View.VISIBLE);
                btnNext.setEnabled(true);
                recyclerView.setVisibility(View.VISIBLE);
                txtNoItem.setVisibility(View.GONE);

            }
            ArrayList<GroceryItem> newItems = utils.getItemsByIds(newItemsIds);
            this.items = newItemsIds;
            adapter.setItems(newItems);
        }



        }
}
