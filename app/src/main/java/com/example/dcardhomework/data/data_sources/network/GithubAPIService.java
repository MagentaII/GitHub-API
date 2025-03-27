package com.example.dcardhomework.data.data_sources.network;

import androidx.lifecycle.LiveData;

import com.example.dcardhomework.data.models.Repo;
import com.example.dcardhomework.data.models.SingleRepo;
import com.example.dcardhomework.helper.ApiResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubAPIService {

    @GET("search/repositories")
    Single<ApiResponse<Repo>> searchRepo(@Query("q") String query);


    @GET("repos/{login}/{name}")
    Single<ApiResponse<SingleRepo>> getRepoDetail(@Path("login") String login, @Path("name") String name);
}
