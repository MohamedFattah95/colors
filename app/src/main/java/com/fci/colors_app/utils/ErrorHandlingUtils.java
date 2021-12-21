package com.fci.colors_app.utils;

import static com.fci.colors_app.BaseApp.getContext;

import android.widget.Toast;

import com.fci.colors_app.R;
import com.fci.colors_app.data.prefs.AppPreferencesHelper;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import es.dmoral.toasty.Toasty;

public class ErrorHandlingUtils {

    private static final AppPreferencesHelper appPreferencesHelper = AppPreferencesHelper.getInstance();

    public static void handleErrors(Throwable e) {
        Toasty.error(getContext(), getErrorMessage(e), Toast.LENGTH_LONG, false).show();
    }


    private static String getErrorMessage(Throwable e) {
        e.printStackTrace();
        if (e instanceof UnknownHostException) {
            return getContext().getResources().getString(R.string.check_internet_conn);
        } else if (e instanceof SocketTimeoutException) {
            return getContext().getResources().getString(R.string.weak_internet_conn);
        } else if (e instanceof HttpException && ((HttpException) e).code() == HttpURLConnection.HTTP_UNAUTHORIZED) {

            return getContext().getResources().getString(R.string.not_authorized);
        } else {
            return getContext().getResources().getString(R.string.some_error);
        }

    }
}
