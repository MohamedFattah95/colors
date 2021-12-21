

package com.fci.colors_app.ui.select_language;

import com.fci.colors_app.data.DataManager;
import com.fci.colors_app.ui.base.BaseViewModel;
import com.fci.colors_app.utils.rx.SchedulerProvider;


public class SelectLanguageViewModel extends BaseViewModel<SelectLanguageNavigator> {

    public SelectLanguageViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


}
