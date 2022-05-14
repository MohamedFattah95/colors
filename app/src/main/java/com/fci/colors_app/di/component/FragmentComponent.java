package com.fci.colors_app.di.component;

import com.fci.colors_app.di.module.FragmentModule;
import com.fci.colors_app.di.scope.FragmentScope;
import com.fci.colors_app.ui.home.HomeFragment;
import com.fci.colors_app.ui.palettes.PalettesFragment;
import com.fci.colors_app.ui.settings.SettingsFragment;

import dagger.Component;

@FragmentScope
@Component(modules = FragmentModule.class, dependencies = AppComponent.class)
public interface FragmentComponent {

    void inject(HomeFragment fragment);

    void inject(SettingsFragment fragment);

    void inject(PalettesFragment fragment);
}
