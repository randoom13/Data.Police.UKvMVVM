package com.amber.random.datapoliceukv2.di.module;

import com.amber.random.datapoliceukv2.ui.fragments.ForcesListFragment;
import com.amber.random.datapoliceukv2.viewmodel.ForcesListModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {
    @ContributesAndroidInjector(modules = ForcesListModule.class)
    public abstract ForcesListFragment bindForcesListFragment();
}
