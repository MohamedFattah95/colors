
package com.fci.colors_app.di.module;

import static com.fci.colors_app.BaseApp.getContext;

import android.app.Application;
import android.content.Context;

import com.fci.colors_app.BuildConfig;
import com.fci.colors_app.R;
import com.fci.colors_app.data.AppDataManager;
import com.fci.colors_app.data.DataManager;
import com.fci.colors_app.data.apis.ApiHelper;
import com.fci.colors_app.data.apis.AppApiHelper;
import com.fci.colors_app.data.apis.NetworkService;
import com.fci.colors_app.data.prefs.AppPreferencesHelper;
import com.fci.colors_app.data.prefs.PreferencesHelper;
import com.fci.colors_app.di.DatabaseInfo;
import com.fci.colors_app.di.PreferenceInfo;
import com.fci.colors_app.utils.AppConstants;
import com.fci.colors_app.utils.LanguageHelper;
import com.fci.colors_app.utils.rx.AppSchedulerProvider;
import com.fci.colors_app.utils.rx.SchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@Module
public class AppModule {

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    public OkHttpClient provideClient(PreferencesHelper preferencesHelper) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(chain -> {
            Request.Builder requestBuilder = chain.request().newBuilder();
            requestBuilder.addHeader("Accept", "application/json");
            requestBuilder.addHeader("Content-Language", LanguageHelper.getLanguage(getContext()));
            return chain.proceed(requestBuilder.build());
        }).build();
    }

    /**
     * provide Retrofit instances
     *
     * @param baseURL base url for api calling
     * @param client  OkHttp client
     * @return Retrofit instances
     */

    @Provides
    public Retrofit provideRetrofit(String baseURL, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Provide Api service
     *
     * @return ApiService instances
     */
    @Provides
    public NetworkService provideApiService(PreferencesHelper preferencesHelper) {
        return provideRetrofit(BuildConfig.BASE_URL, provideClient(preferencesHelper)).create(NetworkService.class);
    }

}
