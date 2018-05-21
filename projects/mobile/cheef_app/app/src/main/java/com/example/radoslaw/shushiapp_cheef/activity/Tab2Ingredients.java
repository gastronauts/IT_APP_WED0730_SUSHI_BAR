package com.example.radoslaw.shushiapp_cheef.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.radoslaw.shushiapp_cheef.R;
import com.example.radoslaw.shushiapp_cheef.adapter.IngredientAdapter;
import com.example.radoslaw.shushiapp_cheef.model.Ingredient;
import com.example.radoslaw.shushiapp_cheef.rest.RetrofitClient;
import com.example.radoslaw.shushiapp_cheef.rest.SushiAppApiService;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tab2Ingredients extends Fragment {
    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_2, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        SushiAppApiService client = RetrofitClient.getClient().create(SushiAppApiService.class);
        Call<List<Ingredient>> call = client.getIngredients();

        call.enqueue(new Callback<List<Ingredient>>() {
            @Override
            public void onResponse(Call<List<Ingredient>> call, Response<List<Ingredient>> response) {
                List<Ingredient> ingre = response.body();
                //Toast.makeText(getContext(),ingre.toString(), Toast.LENGTH_SHORT).show();
                Log.d("KULA",new GsonBuilder().setPrettyPrinting().create().toJson(response));

                listView = getView().findViewById(R.id.item_pagination);
                IngredientAdapter adapter = new IngredientAdapter(getActivity(), ingre);
                listView.setAdapter(adapter);

               // listView.setAdapter(new IngredientAdapter(getActivity().getApplicationContext(),ingre));
            }

            @Override
            public void onFailure(Call<List<Ingredient>> call, Throwable t) {
            }
        });

    }


}