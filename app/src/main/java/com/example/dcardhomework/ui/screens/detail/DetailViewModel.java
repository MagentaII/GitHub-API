package com.example.dcardhomework.ui.screens.detail;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dcardhomework.data.models.Detail;
import com.example.dcardhomework.data.repositories.Repository;
import com.example.dcardhomework.helper.ApiResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class DetailViewModel extends ViewModel {
    private static final String TAG = "DetailViewModel";
    private final Repository repository = new Repository();
    private final CompositeDisposable disposables = new CompositeDisposable();

    // UI State
    private final MutableLiveData<DetailUiState> _uiState = new MutableLiveData<>();
    public LiveData<DetailUiState> uiState = _uiState;

    public DetailViewModel() {
        _uiState.setValue(new DetailUiState(
                null,
                false,
                false
        ));
    }

    public void NavigateRepoDetail(String login, String name) {
        _uiState.setValue(new DetailUiState(
                null,
                true,
                false
        ));
        repository.getDetail(login, name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ApiResponse<Detail>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull ApiResponse<Detail> detailApiResponse) {
                        int code = detailApiResponse.code;
                        Detail data = detailApiResponse.body;
                        String error = detailApiResponse.errorMessage;

                        if (detailApiResponse.isSuccessful()) {
                            if (data != null) {
                                Log.d(TAG, "Success!!");
                                Log.d(TAG, "code: " + code + " error: " + error);
                                _uiState.setValue(new DetailUiState(
                                        data,
                                        false,
                                        false
                                ));
                            } else {
                                Log.d(TAG, "data is null");
                                Log.d(TAG, "code: " + code + " error: " + error);
                                _uiState.setValue(new DetailUiState(
                                        null,
                                        false,
                                        true
                                ));
                            }
                        } else {
                            Log.d(TAG, "Api Response is Failure");
                            Log.d(TAG, "code: " + code + " error: " + error);
                            _uiState.setValue(new DetailUiState(
                                    null,
                                    false,
                                    true
                            ));
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e);
                        _uiState.setValue(new DetailUiState(
                                null,
                                false,
                                true
                        ));
                    }
                });
    }
}
