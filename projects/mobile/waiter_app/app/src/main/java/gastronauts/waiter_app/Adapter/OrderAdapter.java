package gastronauts.waiter_app.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import gastronauts.waiter_app.Model.Order;
import gastronauts.waiter_app.R;

import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<Order> {

    private static class ViewHolder {
        TextView txtID;
        TextView txtTable;
        TextView txtPrice;
    }

    public OrderAdapter(ArrayList<Order> data, Context context) {
        super(context, R.layout.order_list_item_adapter, data);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Order entity = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.order_list_item_adapter, parent, false);
            viewHolder.txtID = convertView.findViewById(R.id.txtId);
            viewHolder.txtTable = convertView.findViewById(R.id.txtTable);
            viewHolder.txtPrice = convertView.findViewById(R.id.txtPrice);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        assert entity != null;
        viewHolder.txtID.setText(String.format("Order Number: %s", entity.getId()));
        viewHolder.txtTable.setText(String.format("Table %s", entity.getTable()));
        viewHolder.txtPrice.setText(String.format("Total Price: %s", entity.getSummaryPrice()));
        return convertView;
    }
}