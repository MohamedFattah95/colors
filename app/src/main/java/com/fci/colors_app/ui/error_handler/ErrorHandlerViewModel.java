
package com.fci.colors_app.ui.error_handler;

import androidx.lifecycle.MutableLiveData;

import com.fci.colors_app.data.DataManager;
import com.fci.colors_app.data.models.DataWrapperModel;
import com.fci.colors_app.ui.base.BaseViewModel;
import com.fci.colors_app.utils.rx.SchedulerProvider;

public class ErrorHandlerViewModel extends BaseViewModel<ErrorHandlerNavigator> {

    private MutableLiveData<DataWrapperModel<Void>> checkUserLiveData;

    public ErrorHandlerViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        checkUserLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<DataWrapperModel<Void>> getCheckUserLiveData() {
        return checkUserLiveData;
    }
}
