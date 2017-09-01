package com.amber.random.datapoliceukv2;


import com.amber.random.datapoliceukv2.di.component.AppComponent;
import com.amber.random.datapoliceukv2.di.module.NetworkModule;
import com.amber.random.datapoliceukv2.di.module.RestApiModule;
import com.amber.random.datapoliceukv2.di.module.ViewModelModule;

import it.cosenonjaviste.daggermock.DaggerMockRule;

public class TestRule extends DaggerMockRule<AppComponent> {
    public TestRule() {
        super(AppComponent.class, new NetworkModule(), new RestApiModule(), new ViewModelModule());
        set(App::setComponent);
    }
}
