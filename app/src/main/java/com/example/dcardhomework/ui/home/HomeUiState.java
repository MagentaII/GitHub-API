package com.example.dcardhomework.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.dcardhomework.data.models.Repo;

public class HomeUiState {
    private final Repo items;
    private final Boolean isLoading;
    private final Boolean isError;

    public HomeUiState(Repo items, Boolean isLoading, Boolean isError) {
        this.items = items;
        this.isLoading = isLoading;
        this.isError = isError;
    }

    @NonNull
    @Override
    public String toString() {
        return "HomeUiState{" +
                "items=" + items +
                ", isLoading=" + isLoading +
                ", isError=" + isError +
                '}';
    }

    public Repo getItems() {
        return items;
    }

    public Boolean isLoading() {
        return isLoading;
    }

    public Boolean isError() {
        return isError;
    }
}
