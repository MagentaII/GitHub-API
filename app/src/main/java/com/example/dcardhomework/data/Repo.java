package com.example.dcardhomework.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Repo {

    @SerializedName("total_count")
    private int total_count;

    @SerializedName("items")
    private List<Items> items;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}
