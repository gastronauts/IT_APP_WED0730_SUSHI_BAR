package com.example.radoslaw.shushiapp_cheef.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import com.example.radoslaw.shushiapp_cheef.R;
import com.example.radoslaw.shushiapp_cheef.model.Ingredient;
import com.example.radoslaw.shushiapp_cheef.rest.RetrofitClient;
import com.example.radoslaw.shushiapp_cheef.rest.SushiAppApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngredientAdapter extends ArrayAdapter<Ingredient> {

        private Context context;
        private List<Ingredient> values;

        public IngredientAdapter(Context context, List<Ingredient> values) {
            super(context, R.layout.list_item_pagination, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View row = convertView;

            if (row == null) {
                LayoutInflater inflater =
                        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.list_item_pagination, parent, false);
            }

            TextView textView = row.findViewById(R.id.list_item_pagination_text);
            TextView textView2 = row.findViewById(R.id.list_item_pagination_textv2);

            final Ingredient item = values.get(position);
            String message = item.getName();
            textView.setText(message);
            String message2 = " "+item.getQuantity();
            textView2.setText(message2);

            Button list_item_pagination_button = row.findViewById(R.id.list_item_pagination_button);
            list_item_pagination_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(item.getQuantity().equals("EMPTY")) item.setQuantity("TEMINTIV");
                    else if (item.getQuantity().equals("TEMINTIV")) item.setQuantity("ENOUGH");
                    else if (item.getQuantity().equals("ENOUGH")) item.setQuantity("EMPTY");
                    else {
                     Toast.makeText(getContext(),"Co tu się stało? ŹLE!!!",Toast.LENGTH_SHORT).show();
                    }

                    SushiAppApiService client = RetrofitClient.getClient().create(SushiAppApiService.class);
                    Call<Ingredient> call=client.changeIngredientStatus(item);
                    call.enqueue(new Callback<Ingredient>() {
                        @Override
                        public void onResponse(Call<Ingredient> call, Response<Ingredient> response) {
                            //Toast.makeText(getContext(), "Git gut: "+response.body().getName()+" "+ response.body().getQuantity(), Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<Ingredient> call, Throwable t) {
                            Toast.makeText(getContext(), "COś poszło nie tak", Toast.LENGTH_SHORT).show();
                        }
                    });


                   // Toast.makeText(getContext(),item.getName(), Toast.LENGTH_SHORT).show();
                }
            });
            return row;
        }
    }

