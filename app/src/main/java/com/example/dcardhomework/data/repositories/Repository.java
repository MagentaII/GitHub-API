package com.example.dcardhomework.data.repositories;

import androidx.lifecycle.LiveData;

import com.example.dcardhomework.data.models.Repo;
import com.example.dcardhomework.data.models.SingleRepo;
import com.example.dcardhomework.helper.ApiResponse;
import com.example.dcardhomework.data.data_sources.network.GithubAPIService;
import com.example.dcardhomework.data.data_sources.network.RetrofitManager;

import io.reactivex.rxjava3.core.Single;

public class Repository {

    private final GithubAPIService githubAPIService = RetrofitManager.getInstance().getAPI();

    //由於LiveDataCallAdapter會幫我們執行call並轉成LiveData，所以DataModel中不用再enqueue了 (遺棄)
    //我改成用RxJava了
    public Single<ApiResponse<Repo>> searchRepo(String query) {
        return githubAPIService.searchRepo(query);
    }

    public Single<ApiResponse<SingleRepo>> getRepoDetail(String login, String name) {
        return githubAPIService.getRepoDetail(login, name);
    }
}
