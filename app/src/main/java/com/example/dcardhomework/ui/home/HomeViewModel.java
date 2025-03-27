package com.example.dcardhomework.ui.home;

import android.text.TextUtils;

import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.dcardhomework.data.models.Repo;
import com.example.dcardhomework.helper.AbsentLiveData;
import com.example.dcardhomework.helper.ApiResponse;
import com.example.dcardhomework.data.repositories.Repository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.jvm.functions.Function1;

public class HomeViewModel extends ViewModel {

//    private final MutableLiveData<String> query = new MutableLiveData<>();
    private final Repository repository;
    private final CompositeDisposable disposables = new CompositeDisposable();

    // UI State
    private final LiveData<ApiResponse<Repo>> itemsListLive;
    public final ObservableBoolean isLoading = new ObservableBoolean(false);
    public final ObservableBoolean isError = new ObservableBoolean(false);
    public HomeViewModel() {
        repository = new Repository();

//        itemsListLive = Transformations.switchMap(query, new Function1<String, LiveData<ApiResponse<Repo>>>() {
//            @Override
//            public LiveData<ApiResponse<Repo>> invoke(String s) {
//                if (TextUtils.isEmpty(s)) {
//                    return AbsentLiveData.create(); // 呼叫靜態方法，返回值為null的LiveData
//                } else {
//                    return repository.searchRepo(s);
//                }
//            }
//        });
    }

//    public void searchRepo(String userInput) {
//        query.setValue(userInput);
//    }

    public void searchRepo(String userInput) {
        repository.searchRepo(userInput)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ApiResponse<Repo>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull ApiResponse<Repo> repoApiResponse) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                })
    }

//    public LiveData<ApiResponse<Repo>> getItemsListLive() {
//        return itemsListLive;
//    }




}
