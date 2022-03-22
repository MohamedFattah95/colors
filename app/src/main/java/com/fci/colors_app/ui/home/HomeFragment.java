package com.fci.colors_app.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fci.colors_app.R;
import com.fci.colors_app.di.component.FragmentComponent;
import com.fci.colors_app.ui.base.BaseFragment;
import com.fci.colors_app.utils.ErrorHandlingUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("NonConstantResourceId")
public class HomeFragment extends BaseFragment<HomeViewModel> implements HomeNavigator {

    public static final String TAG = "HomeFragment";

    @BindView(R.id.rvPalettes)
    RecyclerView rvPalettes;
    @BindView(R.id.swipeRefreshView)
    SwipeRefreshLayout swipeRefreshView;


    public static HomeFragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(BaseFragment.ARGS_INSTANCE, instance);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void refreshData() {
        if (swipeRefreshView != null)
            swipeRefreshView.setRefreshing(true);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        setUp();

        return view;
    }

    private void setUp() {
        subscribeViewModel();

        if (swipeRefreshView != null)
            swipeRefreshView.setRefreshing(true);

        handleSwipeLayout();
    }

    private void handleSwipeLayout() {
        swipeRefreshView.setOnRefreshListener(() -> {
            swipeRefreshView.setRefreshing(true);

        });
    }


    @Override
    public void handleError(Throwable throwable) {
        hideLoading();
        if (swipeRefreshView != null)
            swipeRefreshView.setRefreshing(false);
        ErrorHandlingUtils.handleErrors(throwable);
    }

    @Override
    public void showMyApiMessage(String m) {
        hideLoading();
        if (swipeRefreshView != null)
            swipeRefreshView.setRefreshing(false);
        showErrorMessage(m);
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    private void subscribeViewModel() {


    }
}
