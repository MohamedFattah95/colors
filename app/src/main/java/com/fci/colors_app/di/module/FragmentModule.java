package com.fci.colors_app.di.module;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fci.colors_app.ViewModelProviderFactory;
import com.fci.colors_app.data.DataManager;
import com.fci.colors_app.ui.base.BaseFragment;
import com.fci.colors_app.ui.home.HomeViewModel;
import com.fci.colors_app.ui.home.PalettesAdapter;
import com.fci.colors_app.ui.settings.SettingsViewModel;
import com.fci.colors_app.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private BaseFragment<?> fragment;

    public FragmentModule(BaseFragment<?> fragment) {
        this.fragment = fragment;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(fragment.getActivity());
    }

    @Provides
    PalettesAdapter providePalettesAdapter() {
        return new PalettesAdapter(new ArrayList<>());
    }

    @Provides
    HomeViewModel provideHomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<HomeViewModel> supplier = () -> new HomeViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<HomeViewModel> factory = new ViewModelProviderFactory<>(HomeViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(HomeViewModel.class);
    }

    @Provides
    SettingsViewModel provideSettingsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<SettingsViewModel> supplier = () -> new SettingsViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<SettingsViewModel> factory = new ViewModelProviderFactory<>(SettingsViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(SettingsViewModel.class);
    }

}
