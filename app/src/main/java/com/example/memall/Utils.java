package com.example.memall;

import android.content.Context;
import android.content.SharedPreferences;
import android.print.PrinterId;
import android.util.Log;

import com.example.memall.DatabaseFile.ShopDatabase;
import com.example.memall.Models.GroceryItem;
import com.example.memall.Models.Review;
import com.google.gson.Gson;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {
    private static final String TAG = "Utils";
    private static int ORDER_ID = 0;
//    public static final String DATABASE_NAME = "fake_database";

    private Context context;

    private static ShopDatabase database;

    public Utils(Context context){
        this.context = context;
        database = ShopDatabase.getInstance(context);
    }


    public boolean addReview(Review review){
        Log.d(TAG, "addReview: created");
        database.reviewDao().insert(review);
        return  true;

//        SharedPreferences sharedPreferences = context.getSharedPreferences(DATABASE_NAME,Context.MODE_PRIVATE);
//        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<GroceryItem>>(){}.getType();
//        ArrayList<GroceryItem> items = gson.fromJson(sharedPreferences.getString("allItems",null),type);
//        if(null!=items){
//            ArrayList<GroceryItem> newItems = new ArrayList<>();
//            for (GroceryItem item: items ) {
//                if(item.getId() == review.getGroceryItemId()){
//                    ArrayList<Review> reviews = item.getReviews();
//                    reviews.add(review);
//                    item.setReviews(reviews);
//                }
//                newItems.add(item);
//
//            }
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("allItems",gson.toJson(newItems));
//            editor.commit();
//            return true;
//        }
//        return false;
    }

    public static int getOrderId(){
        ORDER_ID++;
        return ORDER_ID;
    }

    public void updateTheRate(GroceryItem item, int newRate){
        Log.d(TAG, "updateTheRate: started for item " + item.getName());

        item.setRate(newRate);
        database.groceryItemDao().update(item);


//        SharedPreferences sharedPreferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
//        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<GroceryItem>>(){}.getType();
//        ArrayList<GroceryItem> items = gson.fromJson(sharedPreferences.getString("allItems", null),type);
//        if (null != items) {
//            ArrayList<GroceryItem> newItems = new ArrayList<>();
//            for (GroceryItem i : items  ) {
//                if (i.getId() == item.getId()) {
//                    i.setRate(newRate);
//                }
//                newItems.add(i);
//
//            }
//            SharedPreferences.Editor  editor= sharedPreferences.edit();
//            editor.putString("allItems",gson.toJson(newItems));
//            editor.commit();
//        }


    }

    public ArrayList<Review> getReviewForItem (int id) {
        Log.d(TAG, "getReviewForItem: created");

        ArrayList<Review> reviews = (ArrayList<Review>) database.reviewDao().getReviewFroItem(id);

        return reviews;

//        SharedPreferences sharedPreferences = context.getSharedPreferences(DATABASE_NAME,Context.MODE_PRIVATE);
//        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<GroceryItem>>(){}.getType();
//        ArrayList<GroceryItem> items = gson.fromJson(sharedPreferences.getString("allItems",null),type);
//        if (null != items) {
//            for(GroceryItem item:items){
//                if(item.getId()== id){
//                    return item.getReviews();
//                }
//            }
//        }
//        return  null;
    }

    public ArrayList<GroceryItem> getAllItems(){
        Log.d(TAG, "getAllItems: created");

        ArrayList<GroceryItem> allItems = (ArrayList<GroceryItem>) database.groceryItemDao().getAllGroceryItems();
        return  allItems;

//        Gson gson = new Gson();
//        SharedPreferences sharedPreferences = context.getSharedPreferences(DATABASE_NAME,Context.MODE_PRIVATE);
//        Type type = new TypeToken<ArrayList<GroceryItem>>(){}.getType();
//        ArrayList<GroceryItem> allItems = gson.fromJson(sharedPreferences.getString("allItems",null),type);
//
//        return allItems;


    }

    public void addItemToCart(int id){
        Log.d(TAG, "addItemToCart: started");
        database.cartItemDao().insert(id);
//        SharedPreferences sharedPreferences = context.getSharedPreferences(DATABASE_NAME,Context.MODE_PRIVATE);
//        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<Integer>>(){}.getType();
//        ArrayList<Integer> cartItems = gson.fromJson(sharedPreferences.getString("cartItems", null),type);
//        if (null == cartItems){
//            cartItems = new ArrayList<>();
//        }
//        cartItems.add(id);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("cartItems",gson.toJson(cartItems));
//        editor.commit();


    }

    public ArrayList<GroceryItem> searchForItem(String text){
        Log.d(TAG, "searchForItem: started");

        ArrayList<GroceryItem> searchItems = (ArrayList<GroceryItem>) database.groceryItemDao().searchForItems(text);

        return searchItems;

//        SharedPreferences sharedPreferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
//        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<GroceryItem>>(){}.getType();
//        ArrayList<GroceryItem> allItems = gson.fromJson(sharedPreferences.getString("allItems",null),type);
//
//        ArrayList<GroceryItem> searchItems = new ArrayList<>();
//        if(null != allItems){
//            for (GroceryItem item: allItems){
//                if(item.getName().equalsIgnoreCase(text)){
//                    searchItems.add(item);
//                }
//                String[] splittedString = item.getName().split(" ");
//                for (int i = 0; i<splittedString.length; i++){
//                    if(splittedString[i].equalsIgnoreCase(text)){
//
//                        boolean  doesExits = false;
//                        for (GroceryItem searchItem: searchItems){
//                            if (searchItem.equals(item)) {
//                                doesExits = true;
//
//                                break;
//                            }
//                        }
//                        if (!doesExits){
//                            searchItems.add(item);
//                        }
//                    }
//                }
//            }
//        }
//        return searchItems;
    }

    public ArrayList<String> getThreeCategories(){
        Log.d(TAG, "getThreeCategories: started");


//        SharedPreferences sharedPreferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
//        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<GroceryItem>>(){}.getType();
//        ArrayList<GroceryItem> allItems = gson.fromJson(sharedPreferences.getString("allItems",null),type);

        ArrayList<GroceryItem> allItems = (ArrayList<GroceryItem>) database.groceryItemDao().getAllGroceryItems();

        ArrayList<String> categories = new ArrayList<>();
        try{
            for (int i = 0; i < allItems.size(); i++) {
                if(categories.size()<3) {
                    boolean doesExist = false;
                    for (String s : categories) {

                        if (allItems.get(i).getCategory().equals(s)) {
                            doesExist = true;
                            break;
                        }
                    }
                    if (!doesExist) {
                        categories.add(allItems.get(i).getCategory());
                    }

                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return categories;

    }

    public ArrayList<String> getAllCategories(){
        Log.d(TAG, "getAllCategories: started");

//        SharedPreferences sharedPreferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
//        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<GroceryItem>>(){}.getType();
//        ArrayList<GroceryItem> allItems = gson.fromJson(sharedPreferences.getString("allItems",null),type);


        ArrayList<String> categories = new ArrayList<>();

        ArrayList<GroceryItem> allItems = (ArrayList<GroceryItem>) database.groceryItemDao().getAllGroceryItems();
        try{
            for (int i = 0; i < allItems.size(); i++) {

                    boolean doesExist = false;
                    for (String s : categories) {

                        if (allItems.get(i).getCategory().equals(s)) {
                            doesExist = true;
                            break;
                        }
                    }
                    if (!doesExist) {
                        categories.add(allItems.get(i).getCategory());
                    }

                }

        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return categories;

    }

    public ArrayList<GroceryItem> getItemByCategory(String category){
        Log.d(TAG, "getItemByCategory: started");

        ArrayList<GroceryItem> items = (ArrayList<GroceryItem>) database.groceryItemDao().getItemsByCategory(category);
        return items;
//
//        SharedPreferences sharedPreferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
//        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<GroceryItem>>(){}.getType();
//        ArrayList<GroceryItem> allItems = gson.fromJson(sharedPreferences.getString("allItems",null),type);
//        ArrayList<GroceryItem> newItems = new ArrayList<>();
//        if (null!=allItems) {
//            for (GroceryItem item :
//                    allItems) {
//                if (item.getCategory().equals(category)) {
//                    newItems.add(item);
//                }
//
//            }
//        }
//        return newItems;

    }

    public ArrayList<Integer> getCartItems(){
        Log.d(TAG, "getCartItems: started");
        int[] cartItems =  database.cartItemDao().getCartItems();

        ArrayList<Integer> items = new ArrayList<>();
        for (int i = 0; i < cartItems.length; i++) {
            items.add(cartItems[i]);
        }
        return items;

//        SharedPreferences sharedPreferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
//        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<Integer>>(){}.getType();
//        return gson.fromJson(sharedPreferences.getString("cartItems",null),type);

    }

    public ArrayList<Integer> deleteCartItem (GroceryItem item){
        Log.d(TAG, "deleteCartItem: started");

        database.cartItemDao().deleteById(item.getId());

        int[] cartItems = database.cartItemDao().getCartItems();
        ArrayList<Integer> items = new ArrayList<>();
        for (int i = 0; i < cartItems.length; i++) {
            items.add(cartItems[i]);
        }
        return items;


//
//        SharedPreferences sharedPreferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
//        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<Integer>>(){}.getType();
//        ArrayList<Integer> cartItems = gson.fromJson(sharedPreferences.getString("cartItems",null), type);
//        ArrayList<Integer> newItems = new ArrayList<>();
//
//        if (null != cartItems){
//            for(Integer i : cartItems){
//                if(item.getId() != i){
//                    newItems.add(i);
//                }
//            }
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("cartItems", gson.toJson(newItems));
//            editor.commit();
//        }
//        return newItems;

    }

    public void removeCartItems(){
        Log.d(TAG, "removeCartItems: started");
        database.cartItemDao().deleteAllItems();

//        SharedPreferences sharedPreferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.remove("cartItems");
//        editor.apply();

    }

    public ArrayList<GroceryItem> getItemsByIds(ArrayList<Integer> ids){
        Log.d(TAG, "getItemsByIds: started");

        Log.d(TAG, "getItemsByIds: Possible Bug");
        int[] itemIds = new int[ids.size()] ;
        for (int i=0 ; i< ids.size(); i++) {
            itemIds[i] = ids.get(i);

        }

        ArrayList<GroceryItem> items = (ArrayList<GroceryItem>) database.groceryItemDao().getItemsById(itemIds);
        return items;



//        SharedPreferences sharedPreferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
//        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<GroceryItem>>(){}.getType();
//        ArrayList<GroceryItem> allItems =  gson.fromJson(sharedPreferences.getString("allItems",null),type);
//
//        ArrayList<GroceryItem> resultItem = new ArrayList<>();
//        for (int id: ids){
//            if (null != allItems){
//                for (GroceryItem item : allItems){
//                    if(item.getId() == id){
//                        resultItem.add(item);
//                    }
//                }
//            }
//        }
//        return resultItem;


    }

    public void addPopularityPoints(ArrayList<Integer> items){
        Log.d(TAG, "addPopularityPoints: started");

        ArrayList<GroceryItem> allItems = (ArrayList<GroceryItem>) database.groceryItemDao().getAllGroceryItems();

//        SharedPreferences sharedPreferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
//        Gson gson = new Gson();
//        Type type = new TypeToken<ArrayList<GroceryItem>>(){}.getType();
//        ArrayList<GroceryItem> allItems = gson.fromJson(sharedPreferences.getString("allItems",null), type);

        ArrayList<GroceryItem> newItems = new ArrayList<>();

        for (GroceryItem i: allItems){
            boolean doesExits = false;
            for(Integer j : items){
                if(j == i.getId()){
                    doesExits = true;

                }

            }
            if(doesExits){
                i.setPopularityPoint(i.getUserPoint()+ 1);
            }
            newItems.add(i);

        }
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("allItems", gson.toJson(allItems, type));
//        editor.apply();
        for (GroceryItem i: newItems){
            database.groceryItemDao().update(i);
        }
    }

    public void increaseUserPoints(GroceryItem item, int points){
        Log.d(TAG, "increaseUserPoints: started to increase user point for :" + item.getName()+ "by" + points);

        item.setUserPoint(item.getUserPoint()+ points);
        database.groceryItemDao().update(item);

//        SharedPreferences sharedPreferences = context.getSharedPreferences(DATABASE_NAME, Context.MODE_PRIVATE);
//        Gson gson  = new Gson();
//        Type type = new TypeToken<ArrayList<GroceryItem>>(){}.getType();
//        ArrayList<GroceryItem> allItems = gson.fromJson(sharedPreferences.getString("allItems", null), type);
//        if (null != allItems){
//            ArrayList<GroceryItem> newItems = new ArrayList<>();
//            for (GroceryItem i: allItems){
//                if(i.getId() == item.getId()){
//                    i.setUserPoint(i.getUserPoint() + points);
//                }
//                newItems.add(i);
//            }
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("allItems", gson.toJson(newItems));
//            editor.commit();


        }


}
