package com.example.dcardhomework.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.dcardhomework.data.Repo;
import com.example.dcardhomework.data.SingleRepo;
import com.example.dcardhomework.helper.ApiResponse;
import com.example.dcardhomework.retrofit.GithubAPIService;
import com.example.dcardhomework.retrofit.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private final GithubAPIService githubAPIService = RetrofitManager.getInstance().getAPI();

    //由於LiveDataCallAdapter會幫我們執行call並轉成LiveData，所以DataModel中不用再enqueue了
    public LiveData<ApiResponse<Repo>> searchRepo(String query){
        return githubAPIService.searchRepo(query);
    }

    //當點擊cardView會呼叫API 回傳觀看數
    public void getSingleRepo(String login, String name, final onFullNameCallback callback) {
        Call<SingleRepo> call = githubAPIService.getSingleRepo(login, name);
        call.enqueue(new Callback<SingleRepo>() {
            @Override
            public void onResponse(@NonNull Call<SingleRepo> call, @NonNull Response<SingleRepo> response) {
                callback.sendFullName(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<SingleRepo> call, @NonNull Throwable t) {

            }
        });
    }

    public interface onFullNameCallback {
        void sendFullName(SingleRepo singleRepo);
    }
}
