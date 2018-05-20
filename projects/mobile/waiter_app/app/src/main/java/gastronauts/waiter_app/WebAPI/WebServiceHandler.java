package gastronauts.waiter_app.WebAPI;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class WebServiceHandler extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(urlConnection.getInputStream(), "UTF-8");
            java.util.Scanner scanner = new java.util.Scanner(in).useDelimiter("\\A");
            String response = scanner.hasNext() ? scanner.next() : "";

            urlConnection.connect();
            urlConnection.disconnect();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public abstract void handleResponse(String response);

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);

        handleResponse(response);

        if (response == null || response.isEmpty()) {
            response = " ";
        }
        Log.d("HTTP Response", response);
    }
}

