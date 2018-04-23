package com.example.radoslaw.shushiapp_cheef.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import com.example.radoslaw.shushiapp_cheef.R;
import com.example.radoslaw.shushiapp_cheef.model.IngredientResponse;

    public class SushiAdapter extends ArrayAdapter<IngredientResponse> {

        private Context context;
        private List<IngredientResponse> values;

        public SushiAdapter(Context context, List<IngredientResponse> values) {
            super(context, R.layout.list_item_pagination, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;

            if (row == null) {
                LayoutInflater inflater =
                        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.list_item_pagination, parent, false);
            }

            TextView textView = (TextView) row.findViewById(R.id.list_item_pagination_text);

            IngredientResponse item = values.get(position);
            String message = item.getName();
            textView.setText(message);

            return row;
        }
    }

