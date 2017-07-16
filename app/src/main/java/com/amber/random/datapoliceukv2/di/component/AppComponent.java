package com.amber.random.datapoliceukv2.di.component;

import com.amber.random.datapoliceukv2.di.module.NetworkModule;
import com.amber.random.datapoliceukv2.di.module.RestApiModule;
import com.amber.random.datapoliceukv2.di.module.ViewModelModule;
import com.amber.random.datapoliceukv2.ui.fragments.ForcesListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, RestApiModule.class, ViewModelModule.class})
public interface AppComponent {
    void inject(ForcesListFragment forcesListFragment);
}
