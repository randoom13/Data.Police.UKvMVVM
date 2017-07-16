package com.amber.random.datapoliceukv2.viewmodel;

import com.amber.random.datapoliceukv2.api.BackendServiceApi;
import com.amber.random.datapoliceukv2.model.force.ForceItem;

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

    public void loadData() {
        clearSubscriptions();
        try {
            Disposable disposable = mBackendServiceApi.getAllForces()
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(res -> mData.setValue(new ServiceResult(res)),
                            ex -> mData.setValue(new ServiceResult(ex)));

            mCompositeDisposable.add(disposable);
        } catch (Exception ex) {
            mData.setValue(new ServiceResult(ex));
        }
    }
}
