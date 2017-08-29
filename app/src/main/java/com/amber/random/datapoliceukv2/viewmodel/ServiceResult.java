package com.amber.random.datapoliceukv2.viewmodel;

public class ServiceResult<T> {
    public final Throwable exception;
    public final T result;
    public final boolean isSuccessful;

    public ServiceResult(Throwable exception) {
        this.exception = exception;
        isSuccessful = false;
        result = null;
    }

    public ServiceResult(T result) {
        exception = null;
        isSuccessful = true;
        this.result = result;
    }
}
