package com.fci.colors_app.data.apis;

import com.fci.colors_app.data.models.DataWrapperModel;
import com.fci.colors_app.data.models.PaletteModel;
import com.fci.colors_app.data.models.SettingsModel;

import java.util.List;

import io.reactivex.Single;

public interface ApiHelper {

    Single<DataWrapperModel<SettingsModel>> getSettings();

    Single<List<PaletteModel>> getPalettesApiCall();
}
