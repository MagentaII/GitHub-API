package com.example.dcardhomework.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.dcardhomework.data.models.SingleRepo;
import com.example.dcardhomework.helper.ApiResponse;
import com.example.dcardhomework.data.repositories.Repository;

public class DetailViewModel extends ViewModel {
    private final Repository repository;
    public DetailViewModel() {
        repository = new Repository();
    }

    public LiveData<ApiResponse<SingleRepo>> getRepoDetail(String login, String name) {
        return repository.getRepoDetail(login, name);
    }
}
