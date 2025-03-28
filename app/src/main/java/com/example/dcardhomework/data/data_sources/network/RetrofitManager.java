package com.example.dcardhomework.data.data_sources.network;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static final RetrofitManager mInstance = new RetrofitManager();

    private final GithubAPIService githubAPIService;

    private RetrofitManager() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
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
