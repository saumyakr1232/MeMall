package com.example.memall;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.memall.Models.GroceryItem;
import com.example.memall.Models.Order;

import java.util.ArrayList;

public class SuccessPaymentFragment extends Fragment {
    private static final String TAG = "SuccessPaymentFragment";

    private Button btnGoBack;
    private Utils utils;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_succes_payment, container, false);

        utils = new Utils(getActivity());
        utils.removeCartItems();

        Bundle bundle = getArguments();
        try {
            assert bundle != null;
            Order order = bundle.getParcelable("order");
            assert order != null;
            ArrayList<Integer> itemIds = order.getItems();
            ArrayList<GroceryItem> items = utils.getItemsByIds(itemIds);
            for(GroceryItem item: items){

                ArrayList<GroceryItem> sameCategoryItems = utils.getItemByCategory(item.getCategory());
                for (GroceryItem i: sameCategoryItems){
                    utils.increaseUserPoints(i, 4);
                }

            }
            utils.addPopularityPoints(itemIds);
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        btnGoBack = (Button) view.findViewById(R.id.btnGoBack);

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        return view;

    }
}
