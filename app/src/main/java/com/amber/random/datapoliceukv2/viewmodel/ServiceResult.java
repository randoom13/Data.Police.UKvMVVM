package com.amber.random.datapoliceukv2.viewmodel;

/**
 * Created by alexander on 16.07.17.
 */

public class ServiceResult<T> {
    public final Throwable mException;
    public final T mResult;
    public final boolean isSuccessful;
    public ServiceResult(Throwable exception){
        mException = exception;
        isSuccessful = false;
        mResult = null;
    }
    public ServiceResult(T result){
        mException = null;
        isSuccessful = true;
        mResult = result;
    }
}
