package gastronauts.waiter_app.Activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.Button;
import android.widget.TextView;
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
        TextView textBody = findViewById(R.id.txtBody);

        order = (Order) getIntent().getSerializableExtra("ORDER");

        textTitle.setText(String.format("Table %s", order.getTable()));
        textBody.setText(order.getMeals().toString());

        Button notifyButton = findViewById(R.id.notificationButton);
        notifyButton.setOnClickListener(v -> {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), DEFAULT_CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle("Order Ready")
                    .setContentText("Order at Table " + order.getTable() + " is ready to be served!")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            assert notificationManager != null;
            notificationManager.notify(0, mBuilder.build());

        });

        Button closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(v -> {
            WebServiceSender requestHandler = new WebServiceSender();
            requestHandler.execute("http://sushi.mimosa-soft.com/order/served/" + order.getId());
            startActivity(new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
        });
    }


}