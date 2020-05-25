package com.example.memall.DatabaseFile;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.memall.Models.GroceryItem;

import java.util.List;
@Dao
public interface CartItemDao {
    @Query("INSERT INTO cart_item (grocery_item_id) VALUES (:id)")
    void insert(int id);

    @Query("SELECT grocery_item_id FROM cart_item")
    int[] getCartItems();

    @Query("DELETE FROM cart_item WHERE grocery_item_id=:id")
    void deleteById(int id);

    @Query("DELETE FROM cart_item")
    void deleteAllItems();
}
