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
import com.example.radoslaw.shushiapp_cheef.adapter.SushiAdapter;
import com.example.radoslaw.shushiapp_cheef.model.IngredientResponse;
import com.example.radoslaw.shushiapp_cheef.rest.SushiAppApiService;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.TooManyListenersException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://sushi.mimosa-soft.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        SushiAppApiService client = retrofit.create(SushiAppApiService.class);
        Call<List<IngredientResponse>> call = client.getIngredients();

        call.enqueue(new Callback<List<IngredientResponse>>() {
            @Override
            public void onResponse(Call<List<IngredientResponse>> call, Response<List<IngredientResponse>> response) {
                List<IngredientResponse> ingre = response.body();
                Toast.makeText(getContext(),ingre.toString(), Toast.LENGTH_SHORT).show();
                Log.d("KULA",new GsonBuilder().setPrettyPrinting().create().toJson(response));

                listView = (ListView) getView().findViewById(R.id.item_pagination);
                SushiAdapter adapter = new SushiAdapter(getActivity(),
                        ingre);
                listView.setAdapter(adapter);

               // listView.setAdapter(new SushiAdapter(getActivity().getApplicationContext(),ingre));
            }

            @Override
            public void onFailure(Call<List<IngredientResponse>> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(),"aaa", Toast.LENGTH_SHORT).show();
            }
        });
    }

}