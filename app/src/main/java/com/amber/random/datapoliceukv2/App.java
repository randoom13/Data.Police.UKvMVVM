package com.amber.random.datapoliceukv2;

import android.app.Application;

import com.amber.random.datapoliceukv2.di.component.AppComponent;
import com.amber.random.datapoliceukv2.di.component.DaggerAppComponent;
import com.amber.random.datapoliceukv2.di.module.NetworkModule;
import com.amber.random.datapoliceukv2.di.module.RestApiModule;
import com.amber.random.datapoliceukv2.di.module.ViewModelModule;

public class App extends Application {
    private static AppComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
      mComponent = DaggerAppComponent.builder()
              .networkModule(new NetworkModule())
              .restApiModule(new RestApiModule())
              .viewModelModule(new ViewModelModule())
              .build();
    }

    public static AppComponent getComponent() {
        return mComponent;
    }
}
