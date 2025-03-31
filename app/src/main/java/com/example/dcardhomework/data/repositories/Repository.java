package com.example.dcardhomework.data.repositories;

import android.util.Log;
import com.example.dcardhomework.data.models.Repo;
import com.example.dcardhomework.data.models.Detail;
import com.example.dcardhomework.data.data_sources.network.GithubAPIService;
import com.example.dcardhomework.data.data_sources.network.RetrofitManager;
import com.example.dcardhomework.helper.ApiResponse;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Repository {
    private static final String TAG = "Repository";
    private final GithubAPIService githubAPIService = RetrofitManager.getInstance().getAPI();

    //由於LiveDataCallAdapter會幫我們執行call並轉成LiveData，所以DataModel中不用再enqueue了 (遺棄)
    //我改成用RxJava了
    public Single<ApiResponse<Repo>> searchRepo(String query) {
        Log.d(TAG, "Query:" + query);
        return githubAPIService.searchRepo(query)
                .map(ApiResponse::new)
                .subscribeOn(Schedulers.io());
    }

    public Single<ApiResponse<Detail>> getDetail(String login, String name) {
        return githubAPIService.getDetail(login, name)
                .map(ApiResponse::new)
                .subscribeOn(Schedulers.io());
    }
}
