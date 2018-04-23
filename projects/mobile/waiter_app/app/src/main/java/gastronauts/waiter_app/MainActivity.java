package gastronauts.waiter_app;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<OrderModel> dataModels;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.start_button))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new WebServiceHandler()
                                .execute("http://sushi.mimosa-soft.com/order/status/0");
                    }
                });


        listView = findViewById(R.id.order_list);

        dataModels = new ArrayList<>();

        dataModels.add(new OrderModel(0, 0, 0));
        dataModels.add(new OrderModel(1, 1, 1));
        dataModels.add(new OrderModel(2, 2, 2));
        dataModels.add(new OrderModel(3, 3, 3));

        OrderAdapter adapter = new OrderAdapter(dataModels, getApplicationContext());
        listView.setAdapter(adapter);

    }

    private class WebServiceHandler extends AsyncTask<String, Void, String> {

        private ProgressDialog dialog = new ProgressDialog(MainActivity.this);

        private String streamToString(InputStream is) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;

            try {

                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }

                reader.close();

            } catch (IOException e) {
                Log.d(MainActivity.class.getSimpleName(), e.toString());
            }

            return stringBuilder.toString();
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Processing ...");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);
                URLConnection connection = url.openConnection();

                InputStream in = new BufferedInputStream(connection.getInputStream());
                return streamToString(in);

            } catch (Exception e) {
                Log.d(MainActivity.class.getSimpleName(), e.toString());
                return null;
            }

        }

        @Override
        protected void onPostExecute(String result) {

            dialog.dismiss();

            Toast toast = Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT);
            toast.show();

        }
    }
}
