
package com.fci.colors_app.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fci.colors_app.BaseApp;
import com.fci.colors_app.di.component.DaggerFragmentComponent;
import com.fci.colors_app.di.component.FragmentComponent;
import com.fci.colors_app.di.module.FragmentModule;
import com.fci.colors_app.utils.CommonUtils;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

public abstract class BaseFragment<V extends BaseViewModel> extends Fragment {

    private BaseActivity mActivity;
    private View mRootView;
    private ProgressDialog mProgressDialog;


    protected static final String ARGS_INSTANCE = "com.qrc.CSC.argsInstance";
    FragmentNavigation fragmentNavigation;
    int mInt = 0;


    @Inject
    protected V mViewModel;


    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        } else if (context instanceof FragmentNavigation) {
            fragmentNavigation = (FragmentNavigation) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection(getBuildComponent());
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mInt = args.getInt(ARGS_INSTANCE);
        }
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return mRootView;
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(mActivity);
    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }


    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    public void showKeyboard() {
        if (mActivity != null) {
            mActivity.showKeyboard();
        }
    }

    public boolean isNetworkConnected() {
        return mActivity != null && mActivity.isNetworkConnected();
    }

    public abstract void performDependencyInjection(FragmentComponent buildComponent);


    private FragmentComponent getBuildComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(((BaseApp) (getContext().getApplicationContext())).appComponent)
                .fragmentModule(new FragmentModule(this))
                .build();
    }

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }

    public interface FragmentNavigation {
        void pushFragment(Fragment fragment);

    }

    protected void showMessage(String m) {
        if (mActivity != null)
            mActivity.showMessage(m);
    }

    protected void showMessage(int id) {
        if (mActivity != null)
            mActivity.showMessage(id);
    }

    protected void showErrorMessage(String m) {
        if (mActivity != null)
            mActivity.showErrorMessage(m);
    }

    protected void showSuccessMessage(String m) {
        if (mActivity != null)
            mActivity.showSuccessMessage(m);
    }

}
