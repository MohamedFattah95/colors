
package com.fci.colors_app.ui.select_language;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.fci.colors_app.R;
import com.fci.colors_app.di.component.ActivityComponent;
import com.fci.colors_app.ui.base.BaseActivity;
import com.fci.colors_app.ui.main.MainActivity;
import com.fci.colors_app.utils.LanguageHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("NonConstantResourceId")
public class SelectLanguageActivity extends BaseActivity<SelectLanguageViewModel> implements SelectLanguageNavigator {

    @BindView(R.id.rb_english)
    RadioButton rbEnglish;
    @BindView(R.id.rb_arabic)
    RadioButton rbArabic;

    public static Intent newIntent(Context context) {
        return new Intent(context, SelectLanguageActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_language);
        ButterKnife.bind(this);
        mViewModel.setNavigator(this);

    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    @OnClick({R.id.rb_english, R.id.rb_arabic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_english:
                LanguageHelper.setLanguage(this, "en");
                startActivity(MainActivity.newIntent(SelectLanguageActivity.this));
                break;
            case R.id.rb_arabic:
                LanguageHelper.setLanguage(this, "ar");
                startActivity(MainActivity.newIntent(SelectLanguageActivity.this));
                break;
        }
    }
}
