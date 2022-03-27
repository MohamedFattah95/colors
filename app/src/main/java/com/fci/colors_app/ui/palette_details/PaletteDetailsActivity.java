
package com.fci.colors_app.ui.palette_details;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.fci.colors_app.R;
import com.fci.colors_app.di.component.ActivityComponent;
import com.fci.colors_app.ui.base.BaseActivity;
import com.fci.colors_app.utils.ErrorHandlingUtils;

import butterknife.ButterKnife;

@SuppressLint("NonConstantResourceId")
public class PaletteDetailsActivity extends BaseActivity<PaletteDetailsViewModel> implements PaletteDetailsNavigator {

    public static Intent newIntent(Context context) {
        return new Intent(context, PaletteDetailsActivity.class);
    }

    @SuppressLint("NonConstantResourceId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette_details);
        ButterKnife.bind(this);
        mViewModel.setNavigator(this);

        subscribeViewModel();

    }

    private void subscribeViewModel() {
        mViewModel.getSettingsLiveData().observe(this, response -> {
            hideLoading();

        });
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    public void handleError(Throwable throwable) {
        hideLoading();
        ErrorHandlingUtils.handleErrors(throwable);
    }

    @Override
    public void showMyApiMessage(String message) {
        hideLoading();
        showErrorMessage(message);
    }

}
