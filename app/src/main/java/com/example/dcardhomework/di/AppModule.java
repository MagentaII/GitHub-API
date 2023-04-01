package com.example.dcardhomework.di;

import com.example.dcardhomework.helper.LiveDataCallAdapterFactory;
import com.example.dcardhomework.retrofit.GithubAPIService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Provides
    @Singleton
    GithubAPIService provideGithubAPIService(){
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(GithubAPIService.class);
    }

}
