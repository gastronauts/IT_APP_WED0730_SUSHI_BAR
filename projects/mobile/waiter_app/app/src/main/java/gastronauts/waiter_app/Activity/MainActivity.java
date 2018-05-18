package gastronauts.waiter_app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import gastronauts.waiter_app.Adapter.OrderAdapter;
import gastronauts.waiter_app.Model.Meal;
import gastronauts.waiter_app.Model.Order;
import gastronauts.waiter_app.R;
import gastronauts.waiter_app.WebAPI.WebServiceHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private OrderAdapter ordersAdapter;

    private void startDetailsActivity(Order order) {
        Intent intent = new Intent(this, OrderDetailsActivity.class);
        intent.putExtra("ORDER", order);
        startActivity(intent);
    }

    private void parseServerResponse(String response) {
        ordersAdapter.clear();

        JSONArray orderArray = null;
        try {
            orderArray = new JSONArray(response);

            for (int i = 0; i < orderArray.length(); i++) {
                JSONObject orderJSON = orderArray.getJSONObject(i);

                ArrayList<Meal> meals = new ArrayList<>();

                JSONArray mealsArray = orderJSON.getJSONArray("meals");
                for (int j = 0; j < mealsArray.length(); j++) {
                    JSONObject mealJSON = orderArray.getJSONObject(i).getJSONObject("meal");
                    meals.add(new Meal(
                            mealJSON.getString("name"),
                            mealJSON.getString("price"),
                            orderArray.getJSONObject(i).getString("amount")
                    ));
                }

                ordersAdapter.add(new Order(
                        orderJSON.getString("id"),
                        orderJSON.getString("table"),
                        orderJSON.getString("summaryPrice"),
                        orderJSON.getString("status"),
                        meals
                ));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ordersAdapter.notifyDataSetChanged();
    }

    private void fetch_server(int status) {
        WebServiceHandler requestHandler = new WebServiceHandler() {
            @Override
            public void handleResponse(String response) {
                    parseServerResponse(response);
            }
        };
        requestHandler.execute("http://sushi.mimosa-soft.com/order/status/" + status);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_list_activity);

        ordersAdapter = new OrderAdapter(new ArrayList<>(), getApplicationContext());

        ListView ordersListView = findViewById(R.id.order_list);
        ordersListView.setAdapter(ordersAdapter);
        ordersListView.setClickable(true);
        ordersListView.setOnItemClickListener((arg0, arg1, position, arg3) ->
                startDetailsActivity((Order) ordersListView.getItemAtPosition(position)));

        fetch_server(2);

        Button fetchButton = findViewById(R.id.fetchButton);
        fetchButton.setOnClickListener(v -> fetch_server(2));

    }
}
