
package com.fci.colors_app.ui.main;

import com.fci.colors_app.data.DataManager;
import com.fci.colors_app.ui.base.BaseViewModel;
import com.fci.colors_app.utils.rx.SchedulerProvider;


public class MainViewModel extends BaseViewModel<MainNavigator> {

    public MainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

    }

}
