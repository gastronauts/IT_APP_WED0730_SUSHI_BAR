package com.example.radoslaw.shushiapp_cheef.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.radoslaw.shushiapp_cheef.R;
import com.example.radoslaw.shushiapp_cheef.model.Ingredient;
import com.example.radoslaw.shushiapp_cheef.model.Order;
import com.example.radoslaw.shushiapp_cheef.rest.RetrofitClient;
import com.example.radoslaw.shushiapp_cheef.rest.SushiAppApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderAdapter extends ArrayAdapter<Order> {
    private Context context;
    private List<Order> values;

    public OrderAdapter(Context context, List<Order> values) {
        super(context, R.layout.order_item_pagination,values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.order_item_pagination, parent, false);
        }

        TextView textView = row.findViewById(R.id.order_item_pagination_text);
        TextView textView2 = row.findViewById(R.id.order_item_pagination_textv2);

        final Order item = values.get(position);
        Integer tableNumber = item.getTable().getId();
        textView.setText("Zamówienie stolik: "+ tableNumber);
        String message2 = " "+item.getStatus();
        textView2.setText(message2);

        return row;
    }

}
