package com.fci.colors_app.data.apis;

import com.fci.colors_app.data.models.DataWrapperModel;
import com.fci.colors_app.data.models.SettingsModel;

import io.reactivex.Single;

public interface ApiHelper {

    Single<DataWrapperModel<SettingsModel>> getSettings();

}
