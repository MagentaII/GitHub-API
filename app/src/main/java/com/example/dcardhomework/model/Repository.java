package com.example.dcardhomework.model;

import androidx.lifecycle.LiveData;

import com.example.dcardhomework.data.Repo;
import com.example.dcardhomework.data.SingleRepo;
import com.example.dcardhomework.helper.ApiResponse;
import com.example.dcardhomework.retrofit.GithubAPIService;
import com.example.dcardhomework.retrofit.RetrofitManager;

public class Repository {

    private final GithubAPIService githubAPIService = RetrofitManager.getInstance().getAPI();

    //由於LiveDataCallAdapter會幫我們執行call並轉成LiveData，所以DataModel中不用再enqueue了
    public LiveData<ApiResponse<Repo>> searchRepo(String query) {
        return githubAPIService.searchRepo(query);
    }

    public LiveData<ApiResponse<SingleRepo>> getRepoDetail(String login, String name) {
        return githubAPIService.getRepoDetail(login, name);
    }
}
