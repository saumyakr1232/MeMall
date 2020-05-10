package com.example.memall;

import com.example.memall.Models.Order;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitClient {

    @POST("posts")
    Call<Order> goToFakePayment(@Body Order order);

}
