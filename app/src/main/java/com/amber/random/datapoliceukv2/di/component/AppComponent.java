package com.amber.random.datapoliceukv2.di.component;

import com.amber.random.datapoliceukv2.App;
import com.amber.random.datapoliceukv2.di.module.BuildersModule;
import com.amber.random.datapoliceukv2.di.module.NetworkModule;
import com.amber.random.datapoliceukv2.di.module.RestApiModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
        BuildersModule.class, NetworkModule.class,
        RestApiModule.class})
public interface AppComponent {
    void inject(App app);

    @Component.Builder
    interface Builder {
        Builder restApiModule(RestApiModule restApiModule);

        @BindsInstance
        Builder application(App app);

        AppComponent build();
    }
}
