
package com.fci.colors_app.di.component;

import android.app.Application;

import com.fci.colors_app.BaseApp;
import com.fci.colors_app.data.DataManager;
import com.fci.colors_app.di.module.AppModule;
import com.fci.colors_app.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(BaseApp app);

    DataManager getDataManager();

    SchedulerProvider getSchedulerProvider();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
