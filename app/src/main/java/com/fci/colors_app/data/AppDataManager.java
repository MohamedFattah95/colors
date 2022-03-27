
package com.fci.colors_app.data;

import android.content.Context;

import com.fci.colors_app.data.apis.ApiHelper;
import com.fci.colors_app.data.models.DataWrapperModel;
import com.fci.colors_app.data.models.PaletteModel;
import com.fci.colors_app.data.models.SettingsModel;
import com.fci.colors_app.data.prefs.PreferencesHelper;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final ApiHelper mApiHelper;

    private final Context mContext;

    private final Gson mGson;

    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(Context context, PreferencesHelper preferencesHelper, ApiHelper apiHelper, Gson gson) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
        mGson = gson;
    }


    @Override
    public SettingsModel getSettingsObject() {
        return mPreferencesHelper.getSettingsObject();
    }

    @Override
    public void setSettingsObject(SettingsModel settingsModel) {
        mPreferencesHelper.setSettingsObject(settingsModel);
    }

    @Override
    public void setFirstTimeLaunch(boolean isFirstTime) {
        mPreferencesHelper.setFirstTimeLaunch(isFirstTime);
    }

    @Override
    public boolean isFirstTimeLaunch() {
        return mPreferencesHelper.isFirstTimeLaunch();
    }


    @Override
    public void setIsDarkMode(boolean isDarkMode) {
        mPreferencesHelper.setIsDarkMode(isDarkMode);
    }

    @Override
    public boolean isDarkMode() {
        return mPreferencesHelper.isDarkMode();
    }

    @Override
    public Single<DataWrapperModel<SettingsModel>> getSettings() {
        return mApiHelper.getSettings();
    }

    @Override
    public Single<List<PaletteModel>> getPalettesApiCall() {
        return mApiHelper.getPalettesApiCall();
    }

}
