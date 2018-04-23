package gastronauts.waiter_app;

import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

abstract class WebServiceHandler extends AsyncTask<String, String, String> {
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

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        if (response == null || response.isEmpty()) {
            response = " ";
        }
        Log.d("HTTP Response", response);
    }
}

abstract class WebServiceSender extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);
            urlConnection.setRequestMethod("PUT");

            InputStreamReader in = new InputStreamReader(urlConnection.getInputStream(), "UTF-8");
            java.util.Scanner scanner = new java.util.Scanner(in).useDelimiter("\\A");
            String response = scanner.hasNext() ? scanner.next() : "";

            OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
            out.write("");
            out.close();

            urlConnection.connect();
            urlConnection.disconnect();

            return response;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        if (response == null || response.isEmpty()) {
            response = " ";
        }
        Log.d("HTTP Response", response);
    }
}
