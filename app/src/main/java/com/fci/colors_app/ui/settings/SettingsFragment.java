package com.fci.colors_app.ui.settings;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fci.colors_app.R;
import com.fci.colors_app.di.component.FragmentComponent;
import com.fci.colors_app.ui.base.BaseFragment;
import com.fci.colors_app.ui.faqs.FAQsActivity;
import com.fci.colors_app.ui.main.MainActivity;
import com.fci.colors_app.ui.terms.TermsActivity;
import com.google.android.material.switchmaterial.SwitchMaterial;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("NonConstantResourceId")
public class SettingsFragment extends BaseFragment<SettingsViewModel> implements SettingsNavigator {

    public static final String TAG = "SettingsFragment";

    @BindView(R.id.swNight)
    SwitchMaterial swNight;
    @BindView(R.id.tv_app_version)
    TextView tvAppVersion;

    public static SettingsFragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(BaseFragment.ARGS_INSTANCE, instance);
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);

        swNight.setChecked(mViewModel.getDataManager().isDarkMode());

        swNight.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (buttonView.isPressed()) {
                startActivity(((MainActivity) requireActivity()).getIntentWithClearHistory(MainActivity.class));
                mViewModel.getDataManager().setIsDarkMode(isChecked);
            }
        });

        return view;
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    @OnClick({R.id.tv_privacy_policy, R.id.tv_terms, R.id.tv_about, R.id.tv_faq, R.id.tv_complaints_and_suggestions,
            R.id.tv_using_app, R.id.tv_share_app, R.id.tv_rate_us, R.id.ll_update_app})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_privacy_policy:

                break;
            case R.id.tv_terms:
                startActivity(TermsActivity.newIntent(getActivity()));
                break;
            case R.id.tv_about:

                break;
            case R.id.tv_faq:
                startActivity(FAQsActivity.newIntent(getActivity()));
                break;
            case R.id.tv_complaints_and_suggestions:

                break;
            case R.id.tv_using_app:

                break;
            case R.id.tv_share_app:
                shareApp();
                break;
            case R.id.tv_rate_us:

                break;
            case R.id.ll_update_app:
                showLoading();

                break;
        }
    }

    public void shareApp() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        String appName = mViewModel.getDataManager().getSettingsObject().getAppTitle();
        String shareMessage = mViewModel.getDataManager().getSettingsObject().getAppShareNote();
        String androidLink = mViewModel.getDataManager().getSettingsObject().getAppAndroidLnk();
        String iosLink = mViewModel.getDataManager().getSettingsObject().getAppIosLink();
        String shareBody = appName + "\n" + shareMessage + "\n" + getString(R.string.for_android) + ":\n" + androidLink + "\n" + getString(R.string.for_ios) + ":\n" + iosLink;
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        this.startActivity(Intent.createChooser(shareIntent, getString(R.string.share_via)));
    }

}
