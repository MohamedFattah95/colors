package com.fci.colors_app.di.component;

import com.fci.colors_app.di.module.ActivityModule;
import com.fci.colors_app.di.scope.ActivityScope;
import com.fci.colors_app.ui.error_handler.ErrorHandlerActivity;
import com.fci.colors_app.ui.faqs.FAQsActivity;
import com.fci.colors_app.ui.main.MainActivity;
import com.fci.colors_app.ui.select_language.SelectLanguageActivity;
import com.fci.colors_app.ui.splash.SplashActivity;
import com.fci.colors_app.ui.terms.TermsActivity;
import com.fci.colors_app.ui.web_view.WebViewActivity;

import dagger.Component;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

    void inject(SplashActivity activity);

    void inject(TermsActivity activity);

    void inject(SelectLanguageActivity activity);

    void inject(FAQsActivity activity);

    void inject(ErrorHandlerActivity activity);

    void inject(WebViewActivity activity);
}
