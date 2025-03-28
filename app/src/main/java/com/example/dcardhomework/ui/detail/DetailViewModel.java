package com.example.dcardhomework.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dcardhomework.data.models.SingleRepo;
import com.example.dcardhomework.data.repositories.Repository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

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
        repository.getRepoDetail(login, name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<SingleRepo>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull SingleRepo singleRepo) {
                        _uiState.setValue(new DetailUiState(
                                singleRepo,
                                false,
                                false
                        ));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        _uiState.setValue(new DetailUiState(
                                null,
                                false,
                                true
                        ));
                    }
                });
    }

//    public LiveData<ApiResponse<SingleRepo>> getRepoDetail(String login, String name) {
//        return repository.getRepoDetail(login, name);
//    }

//    public void NavigateRepoDetail(String login, String name) {
//        repository.getRepoDetail(login, name)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new SingleObserver<ApiResponse<SingleRepo>>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        disposables.add(d);
//                    }
//
//                    @Override
//                    public void onSuccess(@NonNull ApiResponse<SingleRepo> singleRepoApiResponse) {
//                        if (singleRepoApiResponse.isSuccessful()) {
//                            _repoDetail.setValue(singleRepoApiResponse);
//                        }
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.d(TAG, "Error: " + e.getMessage());
//                    }
//                });
//    }
}
