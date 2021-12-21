
package com.fci.colors_app.ui.web_view;

import com.fci.colors_app.data.DataManager;
import com.fci.colors_app.ui.base.BaseViewModel;
import com.fci.colors_app.utils.rx.SchedulerProvider;

public class WebViewModel extends BaseViewModel<WebNavigator> {

    public WebViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

    }


}
