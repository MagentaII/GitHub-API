package com.example.dcardhomework.helper;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

//其用途是將Call的結果以LiveData<ApiResponse>回傳，概念跟我們上面的DataModel一樣。
public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<ApiResponse<R>>> {
    private final Type responseType;

    public LiveDataCallAdapter(Type responseType) {
        this.responseType = responseType;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public LiveData<ApiResponse<R>> adapt(Call<R> call) {
        AtomicBoolean started = new AtomicBoolean(false);
        return new LiveData<ApiResponse<R>>() {
            @Override
            protected void onActive() {
                super.onActive();
                // 確保同時間只被執行一次。
                if (started.compareAndSet(false, true)) {
                    call.enqueue(new Callback<R>() {
                        @Override
                        public void onResponse(Call<R> call1, Response<R> response) {
                            postValue(new ApiResponse<>(response));
                        }

                        @Override
                        public void onFailure(Call<R> call1, Throwable t) {
                            postValue(new ApiResponse<>(t));
                        }
                    });
                }
            }
        };
    }
}
