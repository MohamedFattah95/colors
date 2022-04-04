package com.fci.colors_app.ui.settings;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fci.colors_app.R;
import com.fci.colors_app.di.component.FragmentComponent;
import com.fci.colors_app.ui.base.BaseFragment;
import com.fci.colors_app.ui.faqs.FAQsActivity;
import com.fci.colors_app.ui.main.MainActivity;
import com.google.android.material.switchmaterial.SwitchMaterial;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("NonConstantResourceId")
public class SettingsFragment extends BaseFragment<SettingsViewModel> implements SettingsNavigator {

    public static final String TAG = "SettingsFragment";

    @BindView(R.id.swNight)
    SwitchMaterial swNight;

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

    @OnClick({R.id.tv_faq, R.id.tv_share_app})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tv_faq:
                startActivity(FAQsActivity.newIntent(getActivity()));
                break;

            case R.id.tv_share_app:
                shareApp();
                break;

        }
    }

    public void shareApp() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));

        String shareBody = getString(R.string.app_name) + "\n" + getString(R.string.for_android) + ":\n"
                + "https://play.google.com/store/apps/details?id=" + requireActivity().getApplicationContext().getPackageName();
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        this.startActivity(Intent.createChooser(shareIntent, getString(R.string.share_via)));
    }

}
