package com.amber.random.datapoliceukv2.viewmodel;

import com.amber.random.datapoliceukv2.api.BackendServiceApi;

import dagger.Module;
import dagger.Provides;

@Module
public class ForcesListModule {
    @Provides
    public ViewModelFactory provideViewModelFactory(BackendServiceApi backendServiceApi) {
        return new ViewModelFactory(backendServiceApi);
    }
}
