package gastronauts.waiter_app;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;


public class OrderDetailsActivity extends Activity {

    private Button closeButton;
    private Button notifyButton;
    private TextView textBody;
    private TextView textTable;

    private int get_order_id(JSONObject json_response) {
        try {
            return json_response.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int get_order_table(JSONObject json_response) {
        try {
            return json_response.getJSONObject("table").getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);

        textBody = findViewById(R.id.txtBody);
        textTable = findViewById(R.id.txtTableTitle);

        JSONObject response_json = null;

        try {
            response_json = new JSONObject(getIntent().getStringExtra("RESPONSE_BODY"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final JSONObject finalResponse_json = response_json;

        textBody.setText(getIntent().getStringExtra("RESPONSE_BODY"));
        textTable.setText(String.format("Table %d", get_order_table(finalResponse_json)));

        notifyButton = findViewById(R.id.notificationButton);
        notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                assert vib != null;
                vib.vibrate(500);

                Notification notif = null;
                notif = new Notification.Builder(getApplicationContext())
                        .setContentTitle("Order Ready")
                        .setContentText(String.format("Table %d is ready to be served", get_order_table(finalResponse_json)))
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .build();

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                assert notificationManager != null;
                notificationManager.notify(0, notif);
            }
        });

        closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebServiceSender requestHandler = new WebServiceSender() {
                    public void handleString(String string) {
                    }
                };
                requestHandler.execute(String.format("http://sushi.mimosa-soft.com/order/served/%d", get_order_id(finalResponse_json)));
                startActivity(new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
    }


}