package com.fci.colors_app.ui.palettes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fci.colors_app.R;
import com.fci.colors_app.data.models.PaletteModel;
import com.fci.colors_app.di.component.FragmentComponent;
import com.fci.colors_app.ui.base.BaseFragment;
import com.fci.colors_app.ui.palette_details.PaletteDetailsActivity;
import com.fci.colors_app.utils.ErrorHandlingUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("NonConstantResourceId")
public class PalettesFragment extends BaseFragment<PalettesViewModel> implements PalettesNavigator, PalettesAdapter.Callback {


    @Inject
    LinearLayoutManager linearLayoutManager;
    @Inject
    PalettesAdapter palettesAdapter;

    @BindView(R.id.rvPalettes)
    RecyclerView rvPalettes;
    @BindView(R.id.swipeRefreshView)
    SwipeRefreshLayout swipeRefreshView;

    public static PalettesFragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(BaseFragment.ARGS_INSTANCE, instance);
        PalettesFragment fragment = new PalettesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void refreshData() {
        if (swipeRefreshView != null) {
            swipeRefreshView.setRefreshing(true);
            mViewModel.getPalettes();
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
        palettesAdapter.setCallback(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_palettes, container, false);
        ButterKnife.bind(this, view);

        setUp();

        return view;
    }

    private void setUp() {
        subscribeViewModel();

        rvPalettes.setLayoutManager(linearLayoutManager);
        rvPalettes.setAdapter(palettesAdapter);

        swipeRefreshView.setOnRefreshListener(() -> {
            swipeRefreshView.setRefreshing(true);
            mViewModel.getPalettes();
        });

        swipeRefreshView.setRefreshing(true);
        mViewModel.getPalettes();
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

        mViewModel.getPalettesLiveData().observe(requireActivity(), response -> {
            hideLoading();
            swipeRefreshView.setRefreshing(false);
            palettesAdapter.addItems(response.getSchemes());
            rvPalettes.scheduleLayoutAnimation();
        });

    }

    @Override
    public void showPaletteDetails(PaletteModel.SchemesBean paletteModel) {

        startActivity(PaletteDetailsActivity.newIntent(getActivity()).putExtra("palette", paletteModel));

    }
}
