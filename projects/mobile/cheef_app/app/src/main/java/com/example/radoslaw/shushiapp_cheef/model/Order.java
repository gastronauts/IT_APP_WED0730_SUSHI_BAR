package com.example.radoslaw.shushiapp_cheef.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order implements Serializable{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("summaryPrice")
    @Expose
    private Object summaryPrice;
    @SerializedName("dateStart")
    @Expose
    private List<Integer> dateStart = null;
    @SerializedName("dateEnd")
    @Expose
    private Object dateEnd;
    @SerializedName("table")
    @Expose
    private Table table;
    @SerializedName("meals")
    @Expose
    private List<Meal> meals = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(Object summaryPrice) {
        this.summaryPrice = summaryPrice;
    }

    public List<Integer> getDateStart() {
        return dateStart;
    }

    public void setDateStart(List<Integer> dateStart) {
        this.dateStart = dateStart;
    }

    public Object getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Object dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

}