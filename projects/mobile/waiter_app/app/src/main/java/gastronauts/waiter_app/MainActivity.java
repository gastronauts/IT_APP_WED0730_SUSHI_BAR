package gastronauts.waiter_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static WebServiceHandler requestHandler;
    OrderAdapter ordersAdapter;
    Button fetchButton;
    ListView ordersListView;

    private void showPopup(String in_text) {
        try {
            Intent intent = new Intent(this, OrderDetailsActivity.class);
            intent.putExtra("RESPONSE_BODY", in_text);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseServerResponse(String response) throws JSONException {
        ordersAdapter.clear();

        JSONArray array = new JSONArray(response);

        for (int i = 0; i < array.length(); i++) {
            JSONObject o = array.getJSONObject(i);
            ordersAdapter.add(new Order(o.toString()));
        }

        ordersAdapter.notifyDataSetChanged();
    }

    private void fetch_server() {
        requestHandler = new WebServiceHandler() {
            @Override
            protected void onPostExecute(String response) {
                super.onPostExecute(response);
                try {
                    parseServerResponse(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        requestHandler.execute("http://sushi.mimosa-soft.com/order/status/2");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list_activity);

        fetchButton = findViewById(R.id.fetchButton);
        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetch_server();
            }
        });

        fetch_server();

        ordersAdapter = new OrderAdapter(new ArrayList<Order>(), getApplicationContext());

        ordersListView = findViewById(R.id.order_list);
        ordersListView.setAdapter(ordersAdapter);
        ordersListView.setClickable(true);

        ordersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Order o = (Order) ordersListView.getItemAtPosition(position);
                showPopup(o.response_body);
            }
        });
    }
}
