package gastronauts.waiter_app.Activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.*;
import gastronauts.waiter_app.Model.Meal;
import gastronauts.waiter_app.Model.Order;
import gastronauts.waiter_app.R;
import gastronauts.waiter_app.WebAPI.WebServiceSender;

import static android.app.NotificationChannel.DEFAULT_CHANNEL_ID;


public class OrderDetailsActivity extends Activity {
    private Order order = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        TextView textTitle = findViewById(R.id.txtTitle);
        TextView textPrice = findViewById(R.id.txtPrice);

        order = (Order) getIntent().getSerializableExtra("ORDER");

        textTitle.setText(String.format("Table %s", order.getTable()));
        textPrice.setText(String.format("Total Price: %s $", order.getSummaryPrice()));

        Button closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(v -> {
            WebServiceSender requestHandler = new WebServiceSender();
            requestHandler.execute("http://sushi.mimosa-soft.com/order/served/" + order.getId());

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            assert notificationManager != null;
            notificationManager.cancel(Integer.parseInt(order.getTable()));
            finish();
        });

        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.simple_list_item, order.getMeals());
        ListView listMeals = findViewById(R.id.listMeals);
        listMeals.setAdapter(adapter);
    }


}