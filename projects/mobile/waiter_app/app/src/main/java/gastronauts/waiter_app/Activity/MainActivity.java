package gastronauts.waiter_app.Activity;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
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

import static android.app.NotificationChannel.DEFAULT_CHANNEL_ID;

public class MainActivity extends AppCompatActivity {

    private static OrderAdapter preparingOrdersAdapter;
    private static OrderAdapter readyOrdersAdapter;


    private Handler handler = new Handler();
    private boolean is_handler_running = false;


    private void startDetailsActivity(Order order) {
        Intent intent = new Intent(this, OrderDetailsActivity.class);
        intent.putExtra("ORDER", order);
        startActivity(intent);
    }

    private static void parseServerResponse(String response, OrderAdapter adapter) {
        adapter.clear();

        try {
            JSONArray orderArray = new JSONArray(response);

            for (int i = 0; i < orderArray.length(); i++) {
                JSONObject orderJSON = orderArray.getJSONObject(i);

                ArrayList<Meal> meals = new ArrayList<>();

                JSONArray mealsArray = orderJSON.getJSONArray("meals");
                for (int j = 0; j < mealsArray.length(); j++) {
                    JSONObject mealJSON = mealsArray.getJSONObject(j).getJSONObject("meal");
                    meals.add(new Meal(
                            mealJSON.getString("name"),
                            mealJSON.getString("price"),
                            mealsArray.getJSONObject(j).getString("amount")
                    ));
                }

                adapter.add(new Order(
                        orderJSON.getString("id"),
                        orderJSON.getJSONObject("table").getString("id"),
                        orderJSON.getString("summaryPrice"),
                        orderJSON.getString("status"),
                        meals
                ));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter.notifyDataSetChanged();
    }

    private void notifyOrderReady(Order order) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), DEFAULT_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Order Ready")
                .setContentText("Order at Table " + order.getTable() + " is ready to be served!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        assert notificationManager != null;
        notificationManager.notify(Integer.parseInt(order.getTable()), mBuilder.build());
    }

    private void fetch_server() {
        new PreparingOrdersTask().execute("http://sushi.mimosa-soft.com/order/status/" + 1);
        new ReadyOrdersTask(this).execute("http://sushi.mimosa-soft.com/order/status/" + 2);
    }

    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            fetch_server();
            handler.postDelayed(this, 2000);
        }
    };

    static private class PreparingOrdersTask extends WebServiceHandler {
        @Override
        public void handleResponse(String response) {
            parseServerResponse(response, preparingOrdersAdapter);
        }
    }

    static private class ReadyOrdersTask extends WebServiceHandler {
         static ArrayList<String> old_id_arr;
         static ArrayList<String> new_id_arr;

        private MainActivity mContext;

        ReadyOrdersTask(MainActivity context){
            mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (old_id_arr == null) {
                old_id_arr = readyOrdersAdapter.getOrdersID();
            }
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            new_id_arr = readyOrdersAdapter.getOrdersID();
            new_id_arr.removeAll(old_id_arr);

            for (String id : new_id_arr) {
                Order o = readyOrdersAdapter.getOrderByID(id);
                mContext.notifyOrderReady(o);
            }

            old_id_arr = readyOrdersAdapter.getOrdersID();
        }

        @Override
        public void handleResponse(String response) {
            parseServerResponse(response, readyOrdersAdapter);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        preparingOrdersAdapter = new OrderAdapter(new ArrayList<>(), getApplicationContext());

        ListView preparingOrdersListView = findViewById(R.id.order_list_preparing);
        preparingOrdersListView.setAdapter(preparingOrdersAdapter);

        readyOrdersAdapter = new OrderAdapter(new ArrayList<>(), getApplicationContext());

        ListView readyOrdersListView = findViewById(R.id.order_list_ready);
        readyOrdersListView.setAdapter(readyOrdersAdapter);
        readyOrdersListView.setClickable(true);
        readyOrdersListView.setOnItemClickListener((arg0, arg1, position, arg3) ->
                startDetailsActivity((Order) readyOrdersListView.getItemAtPosition(position)));

        if (!is_handler_running) {
            handler.post(runnableCode);
            is_handler_running = true;
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putStringArrayList("ID_RETENTION", readyOrdersAdapter.getOrdersID());
        savedInstanceState.putBoolean("IS_HANDLER_RUNNING", is_handler_running);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ReadyOrdersTask.old_id_arr = savedInstanceState.getStringArrayList("ID_RETENTION");
        is_handler_running = savedInstanceState.getBoolean("IS_HANDLER_RUNNING");
    }
}
