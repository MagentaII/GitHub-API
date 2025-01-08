package com.example.dcardhomework.helper;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class LiveDataCallAdapterFactory extends CallAdapter.Factory {
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (getRawType(returnType) != LiveData.class) {
            return null; // 只處理LiveData類型
        }
        Type responseType = getParameterUpperBound(0, (ParameterizedType) returnType); // 取得第一個泛型的類型
        Class<?> rawResponseType = getRawType(responseType); // 取得第一個泛型的原始類型
        if (rawResponseType != ApiResponse.class) {
            throw new IllegalArgumentException("type must be a resource");
        }
        // responseType是否是包含泛型的類型
        if (!(responseType instanceof ParameterizedType)) {
            throw new IllegalArgumentException("resource must be parameterized");
        }
        Type type = getParameterUpperBound(0, (ParameterizedType) responseType);
        return new LiveDataCallAdapter<>(type);
    }

    public static LiveDataCallAdapterFactory create() {
        return new LiveDataCallAdapterFactory();
    }
}
