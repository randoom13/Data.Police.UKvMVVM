package com.amber.random.datapoliceukv2.viewmodel;

import android.text.TextUtils;
import android.util.Log;

import com.amber.random.datapoliceukv2.api.BackendServiceApi;
import com.amber.random.datapoliceukv2.model.force.ForceItem;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ForcesListViewModel extends BaseViewModel<List<ForceItem>> {
    private final BackendServiceApi mBackendServiceApi;

    public ForcesListViewModel(BackendServiceApi backendServiceApi) {
        super();
        mBackendServiceApi = backendServiceApi;

    }

    public void loadData(String filter) {
        clearSubscriptions();
        boolean emptyFilter = TextUtils.isEmpty(filter);
        Disposable disposable = mBackendServiceApi.getAllForces()
                .subscribeOn(Schedulers.computation())
                .flatMapIterable(items -> items)
                .filter(it -> emptyFilter || it.name().contains(filter))
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(res -> mData.setValue(new ServiceResult(res)),
                        ex -> {
                            if (ex instanceof HttpException)
                                Log.e(getClass().getSimpleName(), "Failed to load the forcesList from internet", ex);
                            else throw (Exception) ex;
                            mData.setValue(new ServiceResult(ex));
                        });

        mCompositeDisposable.add(disposable);
    }
}
