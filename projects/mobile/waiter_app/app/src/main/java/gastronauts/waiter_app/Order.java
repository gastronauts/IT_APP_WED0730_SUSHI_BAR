package gastronauts.waiter_app;

import org.json.JSONException;
import org.json.JSONObject;

public class Order {
    String response_body;

    int id;
    String status;
    int table;
    String meals;
    int summaryPrice;

    public Order(Order copy) {
        this.id = copy.id;
        this.table = copy.table;
        this.status = copy.status;
        this.meals = copy.meals;
        this.response_body = copy.response_body;
    }

    public Order(String response) throws JSONException {
        this.response_body = response;

        JSONObject obj = new JSONObject(response);
        this.id = obj.getInt("id");
        this.table = obj.getJSONObject("table").getInt("id");
        this.status = obj.getString("status");
        this.meals = obj.getJSONArray("meals").toString();
        this.summaryPrice = obj.getInt("summaryPrice");
    }
}
