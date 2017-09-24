package com.amber.random.datapoliceukv2.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.amber.random.datapoliceukv2.api.BackendServiceApi;


public class ViewModelFactory implements ViewModelProvider.Factory {
    private final BackendServiceApi mBackendServiceApi;

    public ViewModelFactory(BackendServiceApi backendServiceApi) {
        mBackendServiceApi = backendServiceApi;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ForcesListViewModel.class)) {
            return (T) new ForcesListViewModel(mBackendServiceApi);
        }
        return null;
    }
}
