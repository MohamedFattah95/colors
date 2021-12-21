package com.fci.colors_app.ui.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.fci.colors_app.BaseApp;
import com.fci.colors_app.R;
import com.fci.colors_app.data.prefs.AppPreferencesHelper;
import com.fci.colors_app.di.component.ActivityComponent;
import com.fci.colors_app.di.component.DaggerActivityComponent;
import com.fci.colors_app.di.module.ActivityModule;
import com.fci.colors_app.utils.CommonUtils;
import com.fci.colors_app.utils.LanguageHelper;
import com.fci.colors_app.utils.NetworkUtils;

import java.util.List;

import javax.inject.Inject;

import es.dmoral.toasty.Toasty;

public abstract class BaseActivity<V extends BaseViewModel> extends AppCompatActivity implements BaseFragment.Callback {

    private ProgressDialog mProgressDialog;

    @Inject
    protected V mViewModel;

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        CommonUtils.getLanguageAwareContext(newBase);
        super.attachBaseContext(LanguageHelper.setLanguage(newBase, LanguageHelper.getLanguage(newBase)));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        CommonUtils.getLanguageAwareContext(this);
        LanguageHelper.setLanguage(this, LanguageHelper.getLanguage(this));
        if (AppPreferencesHelper.getInstance().isDarkMode()) {
            setTheme(R.style.AppThemeDark);
        } else {
            setTheme(R.style.AppTheme);
        }

        performDependencyInjection(getBuildComponent());
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public void showKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            }
        }
    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    private ActivityComponent getBuildComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(((BaseApp) getApplication()).appComponent)
                .activityModule(new ActivityModule(this))
                .build();
    }

    public abstract void performDependencyInjection(ActivityComponent buildComponent);

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    public Intent getIntentWithClearHistory(Class<?> c) {
        Intent intent = new Intent(this, c);
        intent.setFlags(intent.getFlags() |
                Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    protected void showApiMessage(String m) {
        Toast toast = Toast.makeText(this, m, Toast.LENGTH_LONG);

        ViewGroup group = (ViewGroup) toast.getView();
        TextView messageTextView = (TextView) group.getChildAt(0);
        messageTextView.setTextSize(12);

        toast.show();
    }

    protected void showMessage(int id) {
        Toast toast = Toast.makeText(this, id, Toast.LENGTH_LONG);

        ViewGroup group = (ViewGroup) toast.getView();
        TextView messageTextView = (TextView) group.getChildAt(0);
        messageTextView.setTextSize(12);

        toast.show();
    }

    protected void showMessage(String m) {
        Toast toast = Toast.makeText(this, m, Toast.LENGTH_LONG);

        ViewGroup group = (ViewGroup) toast.getView();
        TextView messageTextView = (TextView) group.getChildAt(0);
        messageTextView.setTextSize(12);

        toast.show();
    }

    protected void showErrorMessage(String m) {
        Toasty.error(this, m, Toast.LENGTH_LONG, false).show();
    }

    protected void showSuccessMessage(String m) {
        Toasty.success(this, m, Toast.LENGTH_LONG, true).show();
    }

    public boolean isLastActivity(Activity activity) {
        ActivityManager mngr = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

        List<ActivityManager.RunningTaskInfo> taskList = mngr.getRunningTasks(10);

        return taskList.get(0).numActivities == 1 &&
                taskList.get(0).topActivity.getClassName().equals(activity.getClass().getName());
    }

    @Override
    public void applyOverrideConfiguration(Configuration overrideConfiguration) {
        if (overrideConfiguration != null) {
            int uiMode = overrideConfiguration.uiMode;
            overrideConfiguration.setTo(getBaseContext().getResources().getConfiguration());
            overrideConfiguration.uiMode = uiMode;
        }
        super.applyOverrideConfiguration(overrideConfiguration);
    }
}

