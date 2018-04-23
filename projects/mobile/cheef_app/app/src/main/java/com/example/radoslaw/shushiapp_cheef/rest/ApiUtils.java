package com.example.radoslaw.shushiapp_cheef.rest;

public class ApiUtils {
    public static final String BASE_URL = "http://sushi.mimosa-soft.com/";

    public static SushiAppApiService getSushiAppApiService() {
        return RetrofitClient.getClient(BASE_URL).create(SushiAppApiService.class);
    }
}
