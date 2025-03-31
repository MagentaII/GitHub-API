package com.example.dcardhomework.helper;

import android.util.Log;
import androidx.annotation.Nullable;
import java.io.IOException;
import retrofit2.Response;

public class ApiResponse<T> {

    private static final String TAG = "ApiResponse";

    public final int code;
    @Nullable
    public final T body;
    @Nullable
    public final String errorMessage;

    public ApiResponse(Throwable error) {
        this.code = 500;
        this.body = null;
        this.errorMessage = error.getMessage();
    }

    public ApiResponse(Response<T> response) {
        this.code = response.code();
        if (response.isSuccessful()) {
            this.body = response.body();
            this.errorMessage = null;
        } else {
            String message = null;
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody().string();
                } catch (IOException ignored) {
                    Log.d(TAG, "error while parsing response: " + ignored);
                }
            }
            if (message == null || message.trim().length() == 0) {
                message = response.message();
            }
            this.errorMessage = message;
            this.body = null;
        }
    }

    public boolean isSuccessful() {
        return this.code >= 200 && this.code < 300;
    }
}
