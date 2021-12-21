
package com.fci.colors_app.ui.terms;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.fci.colors_app.R;
import com.fci.colors_app.di.component.ActivityComponent;
import com.fci.colors_app.ui.base.BaseActivity;
import com.fci.colors_app.utils.ErrorHandlingUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("NonConstantResourceId")
public class TermsActivity extends BaseActivity<TermsViewModel> implements TermsNavigator {

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_terms)
    TextView tvTerms;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        ButterKnife.bind(this);
        mViewModel.setNavigator(this);

        toolbarTitle.setText(R.string.terms);
        subscribeViewModel();

        showLoading();
        mViewModel.getSettings();
    }

    private void subscribeViewModel() {
        mViewModel.getSettingsLiveData().observe(this, response -> {
            hideLoading();
            tvTerms.setText(Html.fromHtml(response.getData().getTerms()));
        });
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, TermsActivity.class);
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

    @OnClick(R.id.backButton)
    public void onViewClicked() {
        finish();
    }
}
