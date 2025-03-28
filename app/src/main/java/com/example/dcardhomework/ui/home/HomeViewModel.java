package com.example.dcardhomework.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dcardhomework.data.models.Repo;
import com.example.dcardhomework.data.repositories.Repository;
import com.example.dcardhomework.helper.ApiResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    private static final String TAG = "HomeViewModel";
    private final Repository repository = new Repository();
    private final CompositeDisposable disposables = new CompositeDisposable();

    // UI State
    private final MutableLiveData<HomeUiState> _uiState = new MutableLiveData<>();
    public LiveData<HomeUiState> uiState = _uiState;

    public HomeViewModel() {
        _uiState.setValue(new HomeUiState(
                null,
                false,
                false
        ));
    }

    public void searchRepo(String userInput) {
        _uiState.setValue(new HomeUiState(
                null,
                true,
                false
        ));

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
                        int code = repoApiResponse.code;
                        Repo data = repoApiResponse.body;
                        String error = repoApiResponse.errorMessage;
                        if (repoApiResponse.isSuccessful()) {
                            if (data != null) {
                                _uiState.setValue(new HomeUiState(
                                        data,
                                        false,
                                        false
                                ));
                            } else {
                                Log.d(TAG, "code: " + code + " error: " + error);
                                _uiState.setValue(new HomeUiState(
                                        null,
                                        false,
                                        true
                                ));
                            }
                        } else {
                            Log.d(TAG, "code: " + code + " error: " + error);
                            _uiState.setValue(new HomeUiState(
                                    null,
                                    false,
                                    true
                            ));
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        _uiState.setValue(new HomeUiState(
                                null,
                                false,
                                true
                        ));
                    }
                });
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
