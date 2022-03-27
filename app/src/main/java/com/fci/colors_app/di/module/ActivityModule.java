package com.fci.colors_app.di.module;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fci.colors_app.ViewModelProviderFactory;
import com.fci.colors_app.data.DataManager;
import com.fci.colors_app.ui.base.BaseActivity;
import com.fci.colors_app.ui.error_handler.ErrorHandlerViewModel;
import com.fci.colors_app.ui.faqs.FAQsAdapter;
import com.fci.colors_app.ui.faqs.FAQsViewModel;
import com.fci.colors_app.ui.main.MainViewModel;
import com.fci.colors_app.ui.palette_details.PaletteDetailsViewModel;
import com.fci.colors_app.ui.select_language.SelectLanguageViewModel;
import com.fci.colors_app.ui.splash.SplashViewModel;
import com.fci.colors_app.ui.web_view.WebViewModel;
import com.fci.colors_app.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private BaseActivity<?> activity;

    public ActivityModule(BaseActivity<?> activity) {
        this.activity = activity;
    }

    @Provides
    FAQsAdapter provideFAQsAdapter() {
        return new FAQsAdapter(new ArrayList<>());
    }


    @Provides
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(activity);
    }

    @Provides
    WebViewModel provideWebViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<WebViewModel> supplier = () -> new WebViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<WebViewModel> factory = new ViewModelProviderFactory<>(WebViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(WebViewModel.class);
    }

    @Provides
    MainViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<MainViewModel> supplier = () -> new MainViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<MainViewModel> factory = new ViewModelProviderFactory<>(MainViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(MainViewModel.class);
    }

    @Provides
    SplashViewModel provideSplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<SplashViewModel> supplier = () -> new SplashViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<SplashViewModel> factory = new ViewModelProviderFactory<>(SplashViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(SplashViewModel.class);
    }

    @Provides
    PaletteDetailsViewModel provideTermsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<PaletteDetailsViewModel> supplier = () -> new PaletteDetailsViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<PaletteDetailsViewModel> factory = new ViewModelProviderFactory<>(PaletteDetailsViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(PaletteDetailsViewModel.class);
    }

    @Provides
    SelectLanguageViewModel provideSelectLanguageViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<SelectLanguageViewModel> supplier = () -> new SelectLanguageViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<SelectLanguageViewModel> factory = new ViewModelProviderFactory<>(SelectLanguageViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(SelectLanguageViewModel.class);
    }

    @Provides
    FAQsViewModel provideFAQViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<FAQsViewModel> supplier = () -> new FAQsViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<FAQsViewModel> factory = new ViewModelProviderFactory<>(FAQsViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(FAQsViewModel.class);
    }

    @Provides
    ErrorHandlerViewModel provideErrorHandlerViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<ErrorHandlerViewModel> supplier = () -> new ErrorHandlerViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<ErrorHandlerViewModel> factory = new ViewModelProviderFactory<>(ErrorHandlerViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(ErrorHandlerViewModel.class);
    }
}
