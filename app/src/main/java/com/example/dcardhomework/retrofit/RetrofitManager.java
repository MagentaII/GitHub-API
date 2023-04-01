package com.example.dcardhomework.retrofit;

import com.example.dcardhomework.helper.LiveDataCallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static final RetrofitManager mInstance = new RetrofitManager();

    private final GithubAPIService githubAPIService;

    private RetrofitManager() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build();

        githubAPIService = retrofit.create(GithubAPIService.class);
    }

    public static RetrofitManager getInstance() {
        return mInstance;
    }

    public GithubAPIService getAPI() {
        return githubAPIService;
    }
}
