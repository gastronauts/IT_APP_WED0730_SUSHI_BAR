package com.example.radoslaw.shushiapp_cheef.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meal_ {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("category")
    @Expose
    private Object category;
    @SerializedName("details")
    @Expose
    private Object details;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("properTime")
    @Expose
    private Object properTime;
    @SerializedName("ingredients")
    @Expose
    private Object ingredients;
    @SerializedName("price")
    @Expose
    private Object price;
    @SerializedName("possibleToDo")
    @Expose
    private Boolean possibleToDo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getCategory() {
        return category;
    }

    public void setCategory(Object category) {
        this.category = category;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Object getProperTime() {
        return properTime;
    }

    public void setProperTime(Object properTime) {
        this.properTime = properTime;
    }

    public Object getIngredients() {
        return ingredients;
    }

    public void setIngredients(Object ingredients) {
        this.ingredients = ingredients;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public Boolean getPossibleToDo() {
        return possibleToDo;
    }

    public void setPossibleToDo(Boolean possibleToDo) {
        this.possibleToDo = possibleToDo;
    }

}