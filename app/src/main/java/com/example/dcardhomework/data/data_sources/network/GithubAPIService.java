package com.example.dcardhomework.data.data_sources.network;

import com.example.dcardhomework.data.models.Repo;
import com.example.dcardhomework.data.models.Detail;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubAPIService {

    @GET("search/repositories")
    Single<Response<Repo>> searchRepo(@Query("q") String query);


    @GET("repos/{login}/{name}")
    Single<Response<Detail>> getDetail(@Path("login") String login, @Path("name") String name);
}
