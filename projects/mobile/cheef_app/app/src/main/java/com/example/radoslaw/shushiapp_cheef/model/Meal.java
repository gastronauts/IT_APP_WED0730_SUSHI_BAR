package com.example.radoslaw.shushiapp_cheef.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Meal implements Serializable{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("meal")
    @Expose
    private Meal_ meal;
    @SerializedName("amount")
    @Expose
    private Integer amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Meal_ getMeal() {
        return meal;
    }

    public void setMeal(Meal_ meal) {
        this.meal = meal;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}