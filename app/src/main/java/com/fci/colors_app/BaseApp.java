
package com.fci.colors_app;
import android.app.Application;
import android.content.Context;

import com.fci.colors_app.di.component.AppComponent;
import com.fci.colors_app.di.component.DaggerAppComponent;
import com.fci.colors_app.utils.AppLogger;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class BaseApp extends Application {

    public AppComponent appComponent;

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    private static BaseApp instance;
    public static Context context;


    public static Context getContext(){
        return context;
    }
    public static BaseApp getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        if(instance == null){
            instance = this;
        }

        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build();

        appComponent.inject(this);

        AppLogger.init();


        CalligraphyConfig.initDefault(mCalligraphyConfig);


    }

}
