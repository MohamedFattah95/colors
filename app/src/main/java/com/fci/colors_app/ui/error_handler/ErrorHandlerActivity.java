package com.fci.colors_app.ui.error_handler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fci.colors_app.R;
import com.fci.colors_app.di.component.ActivityComponent;
import com.fci.colors_app.ui.base.BaseActivity;
import com.fci.colors_app.utils.ErrorHandlingUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("NonConstantResourceId")
public class ErrorHandlerActivity extends BaseActivity<ErrorHandlerViewModel> implements ErrorHandlerNavigator {

    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.okBtn)
    Button okBtn;

    public static Intent newIntent(Context context) {
        return new Intent(context, ErrorHandlerActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_handler);
        ButterKnife.bind(this);
        mViewModel.setNavigator(this);

        setUp();

    }

    private void setUp() {

        subscribeViewModel();

        showLoading();
    }

    private void subscribeViewModel() {

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
    public void showUserDeletedMsg() {
        hideLoading();
        text.setText(R.string.your_account_deleted);
        okBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMyApiMessage(String message) {
        hideLoading();
        showErrorMessage(message);
    }

    @OnClick(R.id.okBtn)
    void onClickOk() {

    }

}
