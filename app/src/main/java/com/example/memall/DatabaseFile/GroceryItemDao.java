package com.example.memall.DatabaseFile;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.memall.Models.GroceryItem;

import java.util.List;

@Dao
public interface GroceryItemDao {

    @Insert
    void insert(GroceryItem item);

    @Query("SELECT * FROM grocery_item")
    List<GroceryItem> getAllGroceryItems();

    @Update
    void update(GroceryItem item);

    @Query("SELECT * FROM grocery_item WHERE name LIKE :name")
    List<GroceryItem> searchForItems(String name);

    @Query("SELECT category FROM grocery_item LIMIT 3")
    List<String> getThreeCategories();

    @Query("SELECT category FROM grocery_item")
    List<String> getAllCategories();

    @Query("SELECT * FROM GROCERY_ITEM WHERE category=:category")
    List<GroceryItem> getItemsByCategory(String category);

    @Query("SELECT * FROM grocery_item WHERE _id IN (:ids)")
    List<GroceryItem> getItemsById(int[] ids);

}
