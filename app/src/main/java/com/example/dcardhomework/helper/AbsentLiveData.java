package com.example.dcardhomework.helper;

import androidx.lifecycle.LiveData;

//這是為了方便在某些情況下表示缺少數據，而不需要每次都創建一個新的 LiveData 實例。這樣的特殊 LiveData 實例可以用於觀察者模式中，通知觀察者表示當前狀態為缺少數據。
//主要用於表示缺少數據的情況
public class AbsentLiveData extends LiveData {
    private AbsentLiveData() {
        postValue(null);
    }
    public static <T> LiveData<T> create() {
        //noinspection unchecked
        return new AbsentLiveData();
    }
}
