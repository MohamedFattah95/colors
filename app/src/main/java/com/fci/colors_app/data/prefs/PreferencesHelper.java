
package com.fci.colors_app.data.prefs;

import com.fci.colors_app.data.models.SettingsModel;

public interface PreferencesHelper {

    SettingsModel getSettingsObject();

    void setSettingsObject(SettingsModel settingsModel);

    void setFirstTimeLaunch(boolean isFirstTime);

    boolean isFirstTimeLaunch();

    void setIsDarkMode(boolean isDarkMode);

    boolean isDarkMode();
}
