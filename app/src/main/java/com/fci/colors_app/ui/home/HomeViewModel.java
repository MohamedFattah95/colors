

package com.fci.colors_app.ui.home;

import androidx.lifecycle.MutableLiveData;

import com.fci.colors_app.data.DataManager;
import com.fci.colors_app.data.models.PaletteModel;
import com.fci.colors_app.ui.base.BaseViewModel;
import com.fci.colors_app.utils.rx.SchedulerProvider;

import java.util.List;

public class HomeViewModel extends BaseViewModel<HomeNavigator> {

    private MutableLiveData<List<PaletteModel>> palettesLiveData;


    public HomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        palettesLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<PaletteModel>> getPalettesLiveData() {
        return palettesLiveData;
    }

    public void getPalettes() {

        getCompositeDisposable().add(getDataManager()
                .getPalettesApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {

                    palettesLiveData.setValue(response);


                }, throwable -> {
                    getNavigator().handleError(throwable);
                }));

    }
}
