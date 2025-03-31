package com.example.dcardhomework.ui.screens.detail;

import androidx.annotation.NonNull;

import com.example.dcardhomework.data.models.Detail;

public class DetailUiState {
    private final Detail item;
    private final Boolean isLoading;
    private final Boolean isError;

    public DetailUiState(Detail item, Boolean isLoading, Boolean isError) {
        this.item = item;
        this.isLoading = isLoading;
        this.isError = isError;
    }

    @NonNull
    @Override
    public String toString() {
        return "DetailUiState{" +
                "item=" + item +
                ", isLoading=" + isLoading +
                ", isError=" + isError +
                '}';
    }

    public Detail getItem() {
        return item;
    }

    public Boolean isLoading() {
        return isLoading;
    }

    public Boolean isError() {
        return isError;
    }
}
