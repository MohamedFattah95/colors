
package com.fci.colors_app.ui.palette_details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fci.colors_app.data.DataManager;
import com.fci.colors_app.data.models.DataWrapperModel;
import com.fci.colors_app.data.models.SettingsModel;
import com.fci.colors_app.ui.base.BaseViewModel;
import com.fci.colors_app.utils.rx.SchedulerProvider;


public class PaletteDetailsViewModel extends BaseViewModel<PaletteDetailsNavigator> {
    private MutableLiveData<DataWrapperModel<SettingsModel>> settingsLiveData;

    public PaletteDetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        settingsLiveData = new MutableLiveData<>();
    }

    public LiveData<DataWrapperModel<SettingsModel>> getSettingsLiveData() {
        return settingsLiveData;
    }

    public void getSettings() {
        getCompositeDisposable().add(getDataManager()
                .getSettings()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {

                    if (response.getCode() == 200)
                        settingsLiveData.setValue(response);
                    else
                        getNavigator().showMyApiMessage(response.getMessage());

                }, throwable -> getNavigator().handleError(throwable)));
    }

}
