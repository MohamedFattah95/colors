
package com.fci.colors_app.ui.splash;

import com.fci.colors_app.data.DataManager;
import com.fci.colors_app.ui.base.BaseViewModel;
import com.fci.colors_app.utils.rx.SchedulerProvider;

public class SplashViewModel extends BaseViewModel<SplashNavigator> {


    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void decideNextActivity() {

        if (getDataManager().isFirstTimeLaunch()) {

            getNavigator().openLanguageActivity();
            getDataManager().setFirstTimeLaunch(false);

        } else {


                getNavigator().openMainActivity();


        }


    }
}
