

package com.fci.colors_app.ui.settings;

import com.fci.colors_app.data.DataManager;
import com.fci.colors_app.ui.base.BaseViewModel;
import com.fci.colors_app.utils.rx.SchedulerProvider;

public class SettingsViewModel extends BaseViewModel<SettingsNavigator> {

    public SettingsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

}
