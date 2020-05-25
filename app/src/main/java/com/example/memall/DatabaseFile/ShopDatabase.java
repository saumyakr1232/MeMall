package com.example.memall.DatabaseFile;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.memall.Models.GroceryItem;
import com.example.memall.Models.Review;

import org.xml.sax.HandlerBase;

import static androidx.constraintlayout.widget.Constraints.TAG;

@Database(entities = {GroceryItem.class, Review.class, CartItem.class},version = 1)
@TypeConverters(DataConverter.class)
public abstract class ShopDatabase extends RoomDatabase {

    public abstract GroceryItemDao groceryItemDao();
    public abstract ReviewDao reviewDao();
    public abstract  CartItemDao cartItemDao();

    public static ShopDatabase instance;

    public static synchronized ShopDatabase getInstance(Context context){
        if(null == instance){
            instance = Room.databaseBuilder(context, ShopDatabase.class, "shop_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(initialCallBack)
                    .allowMainThreadQueries()
                    .build();
            Log.d(TAG, "getInstance: database build");
        }

        return  instance;
    }

    private static RoomDatabase.Callback initialCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new InitialAsyncTask(instance).execute();


        }
    };

    private static class InitialAsyncTask extends AsyncTask<Void, Void, Void>{
        private static final String TAG = "InitialAsyncTask";

        private GroceryItemDao groceryItemDao;

        public InitialAsyncTask(ShopDatabase db){
            groceryItemDao = db.groceryItemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: database Items added");
            groceryItemDao.insert(new GroceryItem("ice cream", "produced of fresh milk",
                    "https://bloximages.newyork1.vip.townnews.com/virginislandsdailynews.com/content/tncms/assets/v3/editorial/c/f9/cf961d35-5e81-58c5-ae8b-63c27b2020ef/5b57bcd072ed3.image.jpg",
                    "food", 15, 2.5));
            groceryItemDao.insert(new GroceryItem("cheese", "Best cheese possible",
                    "https://amp.businessinsider.com/images/5b8592ba89c8a1d6218b4a36-750-563.jpg", "food", 3, 4.45));
            groceryItemDao.insert(new GroceryItem("Cucumber", "it is fresh",
                    "https://cdn1.medicalnewstoday.com/content/images/articles/283/283006/cucumber-slices.jpg", "vegetables", 10, 0.8));
            groceryItemDao.insert(new GroceryItem("Coca cola", "it is a tasty drink",
                    "https://www.myamericanmarket.com/873-large_default/coca-cola-classic.jpg", "drinks", 100, 1));
            groceryItemDao.insert(new GroceryItem("Spaghetti", "it is an easy to cook meal",
                    "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/barilla-rotini-pasta-1527694739.jpg", "food", 16, 2.5));
            groceryItemDao.insert(new GroceryItem("Chicken nugget", "usually enough for 4 person",
                    "https://www.seriouseats.com/images/2014/01/20140122-taste-test-nuggets-banquet.jpg", "food", 15, 10.8));
            groceryItemDao.insert(new GroceryItem("Clear Shampoo", "you won't experience hair fall with this",
                    "https://100comments.com/wp-content/uploads/2018/02/Untitled-10-3.jpg", "hygiene", 42, 12.3));
            groceryItemDao.insert(new GroceryItem("Axe body spray", "is hot and sweaty? not any more",
                    "https://pics.drugstore.com/prodimg/519930/900.jpg", "hygiene", 9, 8.5));
            groceryItemDao.insert(new GroceryItem("Kleenex", "soft and famous!",
                    "https://images-na.ssl-images-amazon.com/images/I/91ZyGoGBMAL._SY355_.jpg", "hygiene", 12, 3.2));

            return null;
        }
    }
}
