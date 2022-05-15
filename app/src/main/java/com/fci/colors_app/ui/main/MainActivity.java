package com.fci.colors_app.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.fci.colors_app.R;
import com.fci.colors_app.di.component.ActivityComponent;
import com.fci.colors_app.ui.base.BaseActivity;
import com.fci.colors_app.ui.base.BaseFragment;
import com.fci.colors_app.ui.home.HomeFragment;
import com.fci.colors_app.ui.palettes.PalettesFragment;
import com.fci.colors_app.ui.settings.SettingsFragment;
import com.fci.colors_app.utils.ErrorHandlingUtils;
import com.fci.colors_app.utils.LanguageHelper;
import com.google.android.material.navigation.NavigationView;
import com.ncapdevi.fragnav.FragNavController;
import com.ncapdevi.fragnav.tabhistory.UniqueTabHistoryStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("NonConstantResourceId")
public class MainActivity extends BaseActivity<MainViewModel> implements MainNavigator, BaseFragment.FragmentNavigation {

    @BindView(R.id.drawerView)
    DrawerLayout mDrawer;
    @BindView(R.id.navigationView)
    NavigationView mNavigationView;
    @BindView(R.id.drawerButton)
    ImageView drawerButton;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    FragNavController fragNavController;
    List<Fragment> navigation_fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mViewModel.setNavigator(this);
        setUp();
        setUpNavigationController(savedInstanceState);
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        fragNavController.onSaveInstanceState(outState);
    }

    private void setUpNavigationController(Bundle savedInstanceState) {

        fragNavController = new FragNavController(getSupportFragmentManager(), R.id.fragment_container);

        navigation_fragments = new ArrayList<>();
        navigation_fragments.add(HomeFragment.newInstance(0));
        navigation_fragments.add(PalettesFragment.newInstance(0));
        navigation_fragments.add(SettingsFragment.newInstance(0));
        fragNavController.setRootFragments(navigation_fragments);

        fragNavController.setCreateEager(true);
        fragNavController.setFragmentHideStrategy(FragNavController.HIDE);
        UniqueTabHistoryStrategy NavSwitchController = new UniqueTabHistoryStrategy((index, fragNavTransactionOptions) ->
                mNavigationView.setCheckedItem(mNavigationView.getMenu().getItem(index)));

        fragNavController.setNavigationStrategy(NavSwitchController);

        fragNavController.initialize(FragNavController.TAB1, savedInstanceState);

        if (savedInstanceState == null) {
            mNavigationView.setCheckedItem(mNavigationView.getMenu().getItem(FragNavController.TAB1));
        }

    }

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
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

    @Override
    public void onBackPressed() {
        if (mDrawer.isOpen()) {
            mDrawer.closeDrawers();
        } else if (!(fragNavController.getCurrentFrag() instanceof HomeFragment)) {
            fragNavController.switchTab(0);
            toolbarTitle.setText(getString(R.string.home_menu));
            mNavigationView.setCheckedItem(mNavigationView.getMenu().getItem(FragNavController.TAB1));
            fragNavController.clearStack();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mDrawer != null) {
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }

    }

    private void setUp() {

        toolbarTitle.setText(getString(R.string.home_menu));

        drawerButton.setOnClickListener(view -> mDrawer.openDrawer(GravityCompat.START));
        setupNavMenu();
        mNavigationView.setItemIconTintList(null);
        subscribeToLiveData();

    }


    private void setupNavMenu() {
        setupNavDrawerHeader();
        mNavigationView.setItemIconTintList(null);
        mNavigationView.setNavigationItemSelectedListener(
                item -> {
                    mDrawer.closeDrawer(GravityCompat.START);
                    switch (item.getItemId()) {
                        case R.id.navHome:
                            fragNavController.switchTab(0);
                            toolbarTitle.setText(R.string.home_menu);
                            ((HomeFragment) navigation_fragments.get(0)).refreshData();
                            return true;

                        case R.id.navPalettes:
                            fragNavController.switchTab(1);
                            toolbarTitle.setText(R.string.palettes_menu);
                            ((PalettesFragment) navigation_fragments.get(1)).refreshData();
                            return true;

                        case R.id.navSettings:
                            fragNavController.switchTab(2);
                            toolbarTitle.setText(R.string.settings_menu);
                            return true;

                        default:
                            return false;
                    }
                });
    }


    public void setupNavDrawerHeader() {
        View parent = mNavigationView.getHeaderView(0);
        TextView tvLanguage = parent.findViewById(R.id.tvLanguage);

        tvLanguage.setOnClickListener(v -> {
            LanguageHelper.setLanguage(this, LanguageHelper.getLanguage(this).equalsIgnoreCase("ar") ? "en" : "ar");
            mDrawer.closeDrawer(GravityCompat.START);
            startActivity(getIntentWithClearHistory(MainActivity.class));
        });

    }

    private void subscribeToLiveData() {


    }

    @Override
    public void pushFragment(Fragment fragment) {
        fragNavController.pushFragment(fragment);
    }


    public void navigateToPalettes() {
        fragNavController.switchTab(1);
        toolbarTitle.setText(R.string.palettes_menu);
    }
}
