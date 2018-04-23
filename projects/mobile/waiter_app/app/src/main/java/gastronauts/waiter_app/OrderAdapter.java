package gastronauts.waiter_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<Order> implements View.OnClickListener {

    private ArrayList<Order> mData;
    private Context mContext;

    public OrderAdapter(ArrayList<Order> data, Context context) {
        super(context, R.layout.order_list_item_adapter, data);
        this.mContext = context;
        this.mData = data;

    }

    @Override
    public void onClick(View view) {
//        dummy
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Order entity = (Order) getItem(position);
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
        viewHolder.txtID.setText(String.valueOf(entity.id));
        viewHolder.txtTable.setText(String.format("Table %s", String.valueOf(entity.table)));
        viewHolder.txtPrice.setText(String.format("Price: %s", String.valueOf(entity.summaryPrice)));
        // Return the completed view to render on screen
        return convertView;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView txtID;
        TextView txtTable;
        TextView txtPrice;
    }
}