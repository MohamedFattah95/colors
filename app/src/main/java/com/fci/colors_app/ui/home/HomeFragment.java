package com.fci.colors_app.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fci.colors_app.R;
import com.fci.colors_app.di.component.FragmentComponent;
import com.fci.colors_app.ui.base.BaseFragment;
import com.fci.colors_app.ui.common.SliderAdapter;
import com.fci.colors_app.utils.ErrorHandlingUtils;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("NonConstantResourceId")
public class HomeFragment extends BaseFragment<HomeViewModel> implements HomeNavigator {

    public static final String TAG = "HomeFragment";

    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.imageSlider)
    SliderView sliderView;
    @BindView(R.id.rvServices)
    RecyclerView rvServices;
    @BindView(R.id.swipeRefreshView)
    SwipeRefreshLayout swipeRefreshView;
    @BindView(R.id.btnChat)
    LinearLayout btnChat;

    private SliderAdapter sliderAdapter;


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
        setupSliderAdapter();

        setUp();

        return view;
    }

    private void setupSliderAdapter() {
        sliderAdapter = new SliderAdapter(new ArrayList<>());
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LOCALE);
        sliderView.setScrollTimeInSec(3);
        sliderView.startAutoCycle();
    }

    private void setUp() {
        subscribeViewModel();

        if (swipeRefreshView != null)
            swipeRefreshView.setRefreshing(true);

        chatLayoutVisibility();
        handleSwipeLayout();
    }

    private void chatLayoutVisibility() {
        scrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            View view = v.getChildAt(v.getChildCount() - 1);
            int diff = (view.getBottom() - (v.getHeight() + v.getScrollY()));
            if (diff == 0) {
                btnChat.setVisibility(View.GONE);
            } else btnChat.setVisibility(View.VISIBLE);
        });
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

    @SuppressLint("LogNotTimber")
    private void subscribeViewModel() {


    }
}
