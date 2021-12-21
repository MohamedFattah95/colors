package com.fci.colors_app.ui.faqs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fci.colors_app.R;
import com.fci.colors_app.di.component.ActivityComponent;
import com.fci.colors_app.ui.base.BaseActivity;
import com.fci.colors_app.utils.ErrorHandlingUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("NonConstantResourceId")
public class FAQsActivity extends BaseActivity<FAQsViewModel> implements FAQsNavigator, FAQsAdapter.Callback {

    @Inject
    LinearLayoutManager linearLayoutManager;
    @Inject
    FAQsAdapter faqsAdapter;

    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.rv_faq)
    RecyclerView rvFaq;
    @BindView(R.id.swipe_faq)
    SwipeRefreshLayout swipeFaq;

    public static Intent newIntent(Context context) {
        return new Intent(context, FAQsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);
        ButterKnife.bind(this);
        mViewModel.setNavigator(this);
        faqsAdapter.setCallback(this);

        setUp();

    }

    private void setUp() {

        toolbarTitle.setText(R.string.faqs);

        subscribeViewModel();

        rvFaq.setLayoutManager(linearLayoutManager);
        rvFaq.setAdapter(faqsAdapter);

        swipeFaq.setOnRefreshListener(() -> {
            showLoading();
            swipeFaq.setRefreshing(true);
            mViewModel.getFAQs();
        });

        showLoading();
        swipeFaq.setRefreshing(true);
        mViewModel.getFAQs();
    }

    private void subscribeViewModel() {
        mViewModel.getFaqsLiveData().observe(this, response -> {
            hideLoading();
            swipeFaq.setRefreshing(false);
            faqsAdapter.addItems(response.getData());
            rvFaq.scheduleLayoutAnimation();
        });
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
