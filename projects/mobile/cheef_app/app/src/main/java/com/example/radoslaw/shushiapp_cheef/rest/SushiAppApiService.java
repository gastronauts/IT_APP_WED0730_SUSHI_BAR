package com.example.radoslaw.shushiapp_cheef.rest;

import com.example.radoslaw.shushiapp_cheef.model.IngredientResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.Call;

public interface SushiAppApiService {
    @GET("ingredient")
    Call<List<IngredientResponse>> getIngredients();
}
