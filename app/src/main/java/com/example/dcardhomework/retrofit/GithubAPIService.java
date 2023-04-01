package com.example.dcardhomework.retrofit;

import androidx.lifecycle.LiveData;

import com.example.dcardhomework.data.Repo;
import com.example.dcardhomework.data.SingleRepo;
import com.example.dcardhomework.helper.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubAPIService {

    @GET("search/repositories")
    LiveData<ApiResponse<Repo>> searchRepo(@Query("q") String query);

    @GET("repos/{login}/{name}")
    Call<SingleRepo> getSingleRepo(@Path("login") String login, @Path("name") String name);
}
