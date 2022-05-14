

package com.fci.colors_app.ui.palettes;

import androidx.lifecycle.MutableLiveData;

import com.fci.colors_app.data.DataManager;
import com.fci.colors_app.data.models.PaletteModel;
import com.fci.colors_app.ui.base.BaseViewModel;
import com.fci.colors_app.utils.rx.SchedulerProvider;

public class PalettesViewModel extends BaseViewModel<PalettesNavigator> {

    private MutableLiveData<PaletteModel> palettesLiveData;


    public PalettesViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        palettesLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<PaletteModel> getPalettesLiveData() {
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
