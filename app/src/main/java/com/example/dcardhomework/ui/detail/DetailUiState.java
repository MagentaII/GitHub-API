package com.example.dcardhomework.ui.detail;

import androidx.annotation.NonNull;

import com.example.dcardhomework.data.models.SingleRepo;

public class DetailUiState {
    private final SingleRepo item;
    private final Boolean isLoading;
    private final Boolean isError;

    public DetailUiState(SingleRepo item, Boolean isLoading, Boolean isError) {
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

    public SingleRepo getItem() {
        return item;
    }

    public Boolean isLoading() {
        return isLoading;
    }

    public Boolean isError() {
        return isError;
    }
}
