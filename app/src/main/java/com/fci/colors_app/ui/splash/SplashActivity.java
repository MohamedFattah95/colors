package com.fci.colors_app.ui.splash;

import android.os.Bundle;

import com.fci.colors_app.R;
import com.fci.colors_app.di.component.ActivityComponent;
import com.fci.colors_app.ui.base.BaseActivity;
import com.fci.colors_app.ui.main.MainActivity;
import com.fci.colors_app.ui.select_language.SelectLanguageActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity<SplashViewModel> implements SplashNavigator {

    @Override
    public void openMainActivity() {
        startActivity(MainActivity.newIntent(SplashActivity.this));
        finish();
    }

    @Override
    public void openLanguageActivity() {
        startActivity(SelectLanguageActivity.newIntent(SplashActivity.this));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mViewModel.setNavigator(this);


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mViewModel.decideNextActivity();
            }
        }, 1000);

    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

}
