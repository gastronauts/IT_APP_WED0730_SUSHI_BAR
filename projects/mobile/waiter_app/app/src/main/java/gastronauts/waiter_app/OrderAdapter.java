package gastronauts.waiter_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<OrderModel> implements View.OnClickListener {

    private ArrayList<OrderModel> dataSet;
    private Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtID;
        TextView txtTable;
        TextView txtStatus;
    }

    public OrderAdapter(ArrayList<OrderModel> data, Context context) {
        super(context, R.layout.single_order, data);
        this.mContext = context;
        this.dataSet = data;

    }

    @Override
    public void onClick(View v) {
        // TODO: Implement clicking order
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        OrderModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.single_order, parent, false);
            viewHolder.txtID = convertView.findViewById(R.id.txt_id);
            viewHolder.txtTable = convertView.findViewById(R.id.txt_table);
            viewHolder.txtStatus = convertView.findViewById(R.id.txt_status);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.txtID.setText(dataModel.getId());
        viewHolder.txtTable.setText(dataModel.getTable());
        viewHolder.txtStatus.setText(dataModel.getStatus());
        // Return the completed view to render on screen
        return convertView;
    }
}