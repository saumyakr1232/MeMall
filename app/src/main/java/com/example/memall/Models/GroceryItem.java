package com.example.memall.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.memall.Utils;

import java.util.ArrayList;

@Entity(tableName = "grocery_item")
public class GroceryItem implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int id;
    private String name;
    private String description;
    @ColumnInfo(name="image_Url")
    private String imageUrl;
    private String category;
    @ColumnInfo(name="available_amount")
    private int availableAmount;
    private double price;
    @ColumnInfo(name = "popularity_point")
    private int popularityPoint;
    @ColumnInfo(name = "user_point")
    private int userPoint;
    private int rate;
    private ArrayList<Review> reviews;


    public GroceryItem(String name, String description,
                       String imageUrl, String category,
                       int availableAmount, double price,
                       int popularityPoint, int userPoint,
                       int rate, ArrayList<Review> reviews)
    {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
        this.availableAmount = availableAmount;
        this.price = price;
        this.popularityPoint = popularityPoint;
        this.userPoint = userPoint;
        this.rate = rate;
        this.reviews = reviews;
    }

    @Ignore
    public GroceryItem(String name, String description, String imageUrl,
                       String category, int availableAmount, double price) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category.replace(category.charAt(0), category.toUpperCase().charAt(0));
        this.availableAmount = availableAmount;
        this.price = price;
        this.popularityPoint = 0;
        this.userPoint = 0;
        this.reviews = new ArrayList<>();
        this.rate = 0;
    }

    @Ignore
    protected GroceryItem(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        category = in.readString();
        availableAmount = in.readInt();
        price = in.readDouble();
        popularityPoint = in.readInt();
        userPoint = in.readInt();
        rate = in.readInt();
        reviews = in.createTypedArrayList(Review.CREATOR);
    }
    @Ignore
    public static final Creator<GroceryItem> CREATOR = new Creator<GroceryItem>() {
        @Override
        public GroceryItem createFromParcel(Parcel in) {
            return new GroceryItem(in);
        }

        @Override
        public GroceryItem[] newArray(int size) {
            return new GroceryItem[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPopularityPoint() {
        return popularityPoint;
    }

    public void setPopularityPoint(int popularityPoint) {
        this.popularityPoint = popularityPoint;
    }

    public int getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(int userPoint) {
        this.userPoint = userPoint;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Ignore
    @Override
    public String toString() {
        return "GroceryItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", category='" + category + '\'' +
                ", availableAmount=" + availableAmount +
                ", price=" + price +
                ", popularityPoint=" + popularityPoint +
                ", userPoint=" + userPoint +
                ", rate=" + rate +
                ", reviews=" + reviews +
                '}';
    }
    @Ignore
    @Override
    public int describeContents() {
        return 0;
    }

    @Ignore
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(imageUrl);
        dest.writeString(category);
        dest.writeInt(availableAmount);
        dest.writeDouble(price);
        dest.writeInt(popularityPoint);
        dest.writeInt(userPoint);
        dest.writeInt(rate);
        dest.writeTypedList(reviews);
        
    }
}
