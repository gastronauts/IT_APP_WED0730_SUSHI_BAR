package com.example.radoslaw.shushiapp_cheef.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.radoslaw.shushiapp_cheef.R;
import com.example.radoslaw.shushiapp_cheef.adapter.IngredientAdapter;
import com.example.radoslaw.shushiapp_cheef.adapter.OrderAdapter;
import com.example.radoslaw.shushiapp_cheef.model.Ingredient;
import com.example.radoslaw.shushiapp_cheef.model.Order;
import com.example.radoslaw.shushiapp_cheef.rest.RetrofitClient;
import com.example.radoslaw.shushiapp_cheef.rest.SushiAppApiService;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tab1Orders extends Fragment{
    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_1, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        SushiAppApiService client = RetrofitClient.getClient().create(SushiAppApiService.class);
        Call<List<Order>> call = client.getOrdersByStatus(3);
            call.enqueue(new Callback<List<Order>>() {
                @Override
                public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                    List<Order> orde = response.body();
                    listView = getView().findViewById(R.id.order_pagination);
                    OrderAdapter adapter = new OrderAdapter(getActivity(), orde);
                    listView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<List<Order>> call, Throwable t) {
                }
            });
    }
}
