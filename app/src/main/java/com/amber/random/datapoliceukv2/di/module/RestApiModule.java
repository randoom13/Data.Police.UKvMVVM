package com.amber.random.datapoliceukv2.di.module;

import com.amber.random.datapoliceukv2.api.BackendServiceApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RestApiModule {

    @Provides
    @Singleton
    public BackendServiceApi provideRestApi(Retrofit retrofit) {
        return retrofit.create(BackendServiceApi.class);
    }
}
