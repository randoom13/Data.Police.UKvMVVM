package com.amber.random.datapoliceukv2.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import io.reactivex.disposables.CompositeDisposable;


public class BaseViewModel<T> {
    protected final CompositeDisposable mCompositeDisposable;
    protected MutableLiveData<ServiceResult<T>> mData = new MutableLiveData<>();

    public BaseViewModel() {
        mCompositeDisposable = new CompositeDisposable();
    }

    public LiveData<ServiceResult<T>> getData() {
        return mData;
    }

    public void clearSubscriptions() {
        mCompositeDisposable.clear();
    }
}
