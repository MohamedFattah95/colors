
package com.fci.colors_app.data.apis;

import com.fci.colors_app.data.models.DataWrapperModel;
import com.fci.colors_app.data.models.PaletteModel;
import com.fci.colors_app.data.models.SettingsModel;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;


@Singleton
public class AppApiHelper implements ApiHelper {

    private NetworkService networkService;

    @Inject
    public AppApiHelper(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public Single<DataWrapperModel<SettingsModel>> getSettings() {
        return networkService.getSettings();
    }

    @Override
    public Single<PaletteModel> getPalettesApiCall() {
        return networkService.getPalettesApiCall();
    }

}
