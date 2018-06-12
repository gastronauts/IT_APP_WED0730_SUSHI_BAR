package com.example.radoslaw.shushiapp_cheef.rest;

import com.example.radoslaw.shushiapp_cheef.model.Ingredient;
import com.example.radoslaw.shushiapp_cheef.model.Order;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.Call;
import retrofit2.http.Path;

public interface SushiAppApiService {
    @GET("ingredient")
    Call<List<Ingredient>> getIngredients();

    @GET("order/{id}")
    Call<Order> getOrderById(@Path("id") Integer id);

    @GET("order/status/{status}")
    Call<List<Order>> getOrdersByStatus(@Path("status") Integer status);

    @PUT("ingredient")
    Call<Ingredient> changeIngredientStatus(@Body Ingredient ingredientResponse);

    @PUT("order/preparing/{id}")
    Call<Order> changeOrderToPreapering(@Path("id") String id);

    @PUT("order/ready/{id}")
    Call<Order> changeOrderToReady(@Path("id") String id);

    @PUT("order/served/{id}")
    Call<Order> changeOrderToServed(@Path("id") String id);

}
