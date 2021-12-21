
package com.fci.colors_app.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.fci.colors_app.BaseApp;
import com.fci.colors_app.data.models.SettingsModel;
import com.fci.colors_app.di.PreferenceInfo;
import com.fci.colors_app.utils.AppConstants;
import com.google.gson.Gson;

import javax.inject.Inject;

public class AppPreferencesHelper implements PreferencesHelper {

    private static final String IS_FIRST_TIME_LAUNCH = "PREF_FIRST_TIME";

    private static final String SETTINGS_OBJECT = "SETTINGS_OBJECT";

    private static final String IS_DARK_MODE = "IS_DARK_MODE";

    private final SharedPreferences mPrefs;
    private static AppPreferencesHelper mSharedPrefs;


    public static AppPreferencesHelper getInstance() {
        if (mSharedPrefs == null) {
            mSharedPrefs = new AppPreferencesHelper(BaseApp.getContext(), AppConstants.PREF_NAME);
        }
        return mSharedPrefs;
    }

    @Inject
    public AppPreferencesHelper(Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public SettingsModel getSettingsObject() {
        String objStr = mPrefs.getString(SETTINGS_OBJECT, "");
        if (!objStr.matches("")) {
            return new Gson().fromJson(objStr, SettingsModel.class);
        } else return null;
    }

    @Override
    public void setSettingsObject(SettingsModel settingsModel) {
        mPrefs.edit().putString(SETTINGS_OBJECT, new Gson().toJson(settingsModel)).apply();
    }

    @Override
    public void setFirstTimeLaunch(boolean isFirstTime) {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.apply();
    }

    @Override
    public boolean isFirstTimeLaunch() {
        return mPrefs.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    @Override
    public void setIsDarkMode(boolean isDarkMode) {
        mPrefs.edit().putBoolean(IS_DARK_MODE, isDarkMode).apply();
    }

    @Override
    public boolean isDarkMode() {
        return mPrefs.getBoolean(IS_DARK_MODE, false);
    }
}
