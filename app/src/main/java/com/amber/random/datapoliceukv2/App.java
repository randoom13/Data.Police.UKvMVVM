package com.amber.random.datapoliceukv2;

import android.app.Application;

import com.amber.random.datapoliceukv2.di.component.AppComponent;
import com.amber.random.datapoliceukv2.di.component.DaggerAppComponent;
import com.amber.random.datapoliceukv2.di.module.NetworkModule;
import com.amber.random.datapoliceukv2.di.module.RestApiModule;
import com.amber.random.datapoliceukv2.di.module.ViewModelModule;

public class App extends Application {
    private static AppComponent sComponent;

    public static AppComponent getComponent() {
        return sComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sComponent = DaggerAppComponent.builder()
              .networkModule(new NetworkModule())
              .restApiModule(new RestApiModule())
              .viewModelModule(new ViewModelModule())
              .build();
    }
}
