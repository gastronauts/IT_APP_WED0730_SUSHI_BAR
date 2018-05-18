package gastronauts.waiter_app.Model;

import java.io.Serializable;

public class Meal implements Serializable {
    private String name;
    private String price;
    private String amount;

    public Meal(String name, String price, String amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
