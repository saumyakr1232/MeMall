package com.example.memall.DatabaseFile;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.memall.Models.Review;

import java.util.List;

@Dao
public interface ReviewDao {

    @Insert
    void insert(Review review);

    @Query("SELECT * FROM reviews WHERE grocery_item_id=:id")
    List<Review> getReviewFroItem(int id);
}
