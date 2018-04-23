package gastronauts.waiter_app;

public class OrderModel {

    /* 0 - Ordered 1 - Preparing 2 - Ready 3 - Served */
    private static String status_label[] = {"Ordered", "Preparing", "Ready", "Served"};

    private Integer id;
    private Integer table;
    private Integer status;

    public OrderModel(Integer id, Integer table, Integer status ) {
        this.id=id;
        this.table=table;
        this.status=status;
    }

    public String getId() {
        return "Order " + Integer.toString(id);
    }

    public String getTable() {
        return "Table " + Integer.toString(table);
    }

    public String getStatus() {
        return status_label[status];
    }
}
