package com.example.memall;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.memall.Models.GroceryItem;
import com.example.memall.Models.Review;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddReviewDialog extends DialogFragment {
    private static final String TAG = "AddReviewDialog";

    private EditText edtTxtName, edtTxtReview;
    private Button btnAddReview;
    private TextView txtItemName, txtWarning;



    private int itemId = 0;

    public interface AddReview{
        void onAddReviewResult(Review review);
    }

    private AddReview addReview;
    private boolean reviewAdded = false;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_reviews,null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle("Add Review")
                .setView(view);

        initViews(view);
        Bundle bundle = getArguments();
        try{
            GroceryItem item = bundle.getParcelable("item");
            txtItemName.setText(item.getName());
            this.itemId = item.getId();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        btnAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    addReview();
                    reviewAdded = true;

            }
        });
        reviewAdded = false;
        return builder.create();
    }

    private void addReview(){
        Log.d(TAG, "addReview: created");

        if(validateData()) {
            String name = edtTxtName.getText().toString();
            String reviewText = edtTxtReview.getText().toString();
            String date = getCurrentDate();

            Review review = new Review(itemId, name, date, reviewText);
            Bundle bundle = getArguments();
            try {
                GroceryItem incomingItem = bundle.getParcelable("item");
                if(incomingItem != null){
                    ArrayList<Review> hasReviews = incomingItem.getReviews();
                    if (!hasReviews.contains(review)) {
                        try {
                            addReview = (AddReview) getActivity();
                            addReview.onAddReviewResult(review);
                            dismiss();
                        } catch (ClassCastException e) {
                            e.printStackTrace();
                            dismiss();
                        }

                    }else{
                        dismiss();
                    }
                }
            } catch (ClassCastException e){
                e.printStackTrace();
            }




        }else{
            txtWarning.setVisibility(View.VISIBLE);
        }
    }

    private boolean validateData() {
        Log.d(TAG, "validateData: started");
        if(edtTxtName.getText().toString().equals("") || edtTxtReview.getText().toString().equals("")){
            return false;
        }

        return true;
    }

    private String getCurrentDate(){
        Log.d(TAG, "getCurrentDate: created");
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yy");
        return sdf.format(date);
    }

    private void initViews(View view){
        Log.d(TAG, "initViews: created");
        edtTxtName = (EditText) view.findViewById(R.id.edtTxtName);
        edtTxtReview = (EditText) view.findViewById(R.id.edtTxtReview);
        txtItemName = (TextView) view.findViewById(R.id.reviewName);
        txtWarning = (TextView) view.findViewById(R.id.txtWarning);
        btnAddReview = (Button) view.findViewById(R.id.btnAdd);

    }
}
