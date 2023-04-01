package com.example.dcardhomework.data;

import com.google.gson.annotations.SerializedName;

public class SingleRepo {
    @SerializedName("subscribers_count")
    private int subscribers_count;

    public int getSubscribers_count() {
        return subscribers_count;
    }

    public void setSubscribers_count(int subscribers_count) {
        this.subscribers_count = subscribers_count;
    }
}
