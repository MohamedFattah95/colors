package com.fci.colors_app.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.fci.colors_app.utils.ErrorHandlingUtils;
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
//        handleNavItems();

    }


    private void setupNavMenu() {
        setupNavDrawerHeader();
//        mNavigationView.setItemIconTintList(null);
//        mNavigationView.setNavigationItemSelectedListener(
//                item -> {
//                    mDrawer.closeDrawer(GravityCompat.START);
//                    switch (item.getItemId()) {
//                        case R.id.navHome:
//                            fragNavController.switchTab(0);
//                            handleToolBar(navigation_fragments.get(0));
//                            ((HomeFragment) navigation_fragments.get(0)).refreshData();
//                            return true;
//                        case R.id.navProfile:
//                            fragNavController.switchTab(1);
//                            handleToolBar(navigation_fragments.get(1));
//                            ((ProfileFragment) navigation_fragments.get(1)).refreshData();
//                            return true;
//                        case R.id.navOrders:
//                            fragNavController.switchTab(2);
//                            handleToolBar(navigation_fragments.get(2));
//                            ((OrdersFragment) navigation_fragments.get(2)).refreshData();
//                            return true;
//                        case R.id.navNotifications:
//                            fragNavController.switchTab(3);
//                            handleToolBar(navigation_fragments.get(3));
//                            ((NotificationsFragment) navigation_fragments.get(3)).refreshData();
//                            return true;
//                        case R.id.navBalance:
//                            fragNavController.switchTab(4);
//                            handleToolBar(navigation_fragments.get(4));
//                            ((BalanceFragment) navigation_fragments.get(4)).refreshData();
//                            return true;
//                        case R.id.navCallCenter:
//
//                            startActivity(new Intent(Intent.ACTION_DIAL).setData(
//                                    Uri.parse("tel:" + mViewModel.getDataManager().getSettingsObject().getCustomerService())));
//
//                            return true;
//                        case R.id.navTelegram:
//
//                            final String appName = "org.telegram.messenger";
//                            final boolean isAppInstalled = AppUtils.isAppAvailable(this, appName);
//                            if (isAppInstalled) {
//                                try {
//                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mViewModel.getDataManager().getSettingsObject().getTelegram())));
//                                } catch (Exception e) {
//                                    startActivity(WebViewActivity.newIntent(this)
//                                            .putExtra("link", mViewModel.getDataManager().getSettingsObject().getTelegram())
//                                            .putExtra("title", getString(R.string.telegram_menu)));
//                                }
//                            } else {
//                                try {
//                                    startActivity(WebViewActivity.newIntent(this)
//                                            .putExtra("link", mViewModel.getDataManager().getSettingsObject().getTelegram())
//                                            .putExtra("title", getString(R.string.telegram_menu)));
//                                } catch (Exception e) {
//                                    showMessage(R.string.invalid_telegram_link);
//                                }
//                            }
//
//                            return true;
//                        case R.id.navNumber:
//
//                            startActivity(new Intent(Intent.ACTION_DIAL).setData(
//                                    Uri.parse("tel:" + mViewModel.getDataManager().getSettingsObject().getUnifiedNumber())));
//
//                            return true;
//                        case R.id.navSettings:
//                            fragNavController.switchTab(5);
//                            handleToolBar(navigation_fragments.get(5));
//                            return true;
//                        case R.id.navLogout:
//                            mViewModel.doLogout();
//                            return true;
//
//                        default:
//                            return false;
//                    }
//                });
    }


    public void setupNavDrawerHeader() {
//        View parent = mNavigationView.getHeaderView(0);
//        TextView tvLanguage = parent.findViewById(R.id.tvLanguage);
//        TextView tvUsername = parent.findViewById(R.id.tvUsername);
//        CircleImageView cUserImage = parent.findViewById(R.id.userImageView);
//        RatingBar ratingBar = parent.findViewById(R.id.ratingBarDelegate);
//        TextView tvRateCount = parent.findViewById(R.id.tvRateCount);


    }

    private void subscribeToLiveData() {


    }

    private void unlockDrawer() {
        if (mDrawer != null) {
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }

    @Override
    public void pushFragment(Fragment fragment) {
        fragNavController.pushFragment(fragment);
    }


}
