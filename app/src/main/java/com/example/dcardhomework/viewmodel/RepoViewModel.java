package com.example.dcardhomework.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.dcardhomework.data.Repo;
import com.example.dcardhomework.data.SingleRepo;
import com.example.dcardhomework.helper.AbsentLiveData;
import com.example.dcardhomework.helper.ApiResponse;
import com.example.dcardhomework.model.Repository;

import kotlin.jvm.functions.Function1;

public class RepoViewModel extends AndroidViewModel {

    private final MutableLiveData<String> query = new MutableLiveData<>();
    public final ObservableBoolean isLoading = new ObservableBoolean(false);
    public final ObservableBoolean isError = new ObservableBoolean(false);

    private final LiveData<ApiResponse<Repo>> itemsListLive;
    private final MutableLiveData<SingleRepo> singleRepoLive;
    private final Repository repository;

        public RepoViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();

        itemsListLive = Transformations.switchMap(query, new Function1<String, LiveData<ApiResponse<Repo>>>() {
            @Override
            public LiveData<ApiResponse<Repo>> invoke(String s) {
                if (TextUtils.isEmpty(s)) {
                    return AbsentLiveData.create();
                } else {
                    return repository.searchRepo(s);
                }
            }
        });
            singleRepoLive = new MutableLiveData<>();
    }

    public LiveData<ApiResponse<Repo>> getItemsListLive() {
        return itemsListLive;
    }

    public MutableLiveData<SingleRepo> getSingleRepoLive() {
        return singleRepoLive;
    }


    public void searchRepo(String userInput) {
        query.setValue(userInput);
    }

    //利用callback的方式
    public void getSingleRepo(String login, String name){
        repository.getSingleRepo(login, name, new Repository.onFullNameCallback() {
            @Override
            public void sendFullName(SingleRepo singleRepo) {
                singleRepoLive.setValue(singleRepo);
            }
        });
    }
}
