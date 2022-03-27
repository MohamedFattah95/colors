package com.fci.colors_app.data.apis;


import com.fci.colors_app.data.models.DataWrapperModel;
import com.fci.colors_app.data.models.PaletteModel;
import com.fci.colors_app.data.models.SettingsModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface NetworkService {

    @GET("settings/all")
    Single<DataWrapperModel<SettingsModel>> getSettings();

    @GET("settings/all")
    Single<List<PaletteModel>> getPalettesApiCall();
}
