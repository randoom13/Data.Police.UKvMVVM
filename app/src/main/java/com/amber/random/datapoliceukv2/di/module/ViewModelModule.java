package com.amber.random.datapoliceukv2.di.module;

import com.amber.random.datapoliceukv2.api.BackendServiceApi;
import com.amber.random.datapoliceukv2.viewmodel.ForcesListViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {
    @Provides
    @Singleton
    public ForcesListViewModel provideMainViewModel(BackendServiceApi backendServiceApi) {
        return new ForcesListViewModel(backendServiceApi);
    }
}
