package com.fci.colors_app.ui.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.fci.colors_app.R;
import com.fci.colors_app.data.models.PaletteModel;
import com.fci.colors_app.di.component.FragmentComponent;
import com.fci.colors_app.ui.base.BaseFragment;
import com.fci.colors_app.ui.img_palette.ImgPaletteActivity;
import com.fci.colors_app.ui.main.MainActivity;
import com.fci.colors_app.ui.palette_details.PaletteDetailsActivity;
import com.fci.colors_app.ui.palettes.PalettesAdapter;
import com.fci.colors_app.utils.ErrorHandlingUtils;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("NonConstantResourceId")
public class HomeFragment extends BaseFragment<HomeViewModel> implements HomeNavigator, PalettesAdapter.Callback {

    public static final String TAG = "HomeFragment";
    private static final int PICK_IMG = 100;

    @BindView(R.id.cvExplorePalettes)
    CardView cvExplorePalette;
    @BindView(R.id.cvPickWithCam)
    CardView cvPickWithCam;

    File img = null;

    public static HomeFragment newInstance(int instance) {
        Bundle args = new Bundle();
        args.putInt(BaseFragment.ARGS_INSTANCE, instance);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void refreshData() {


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

        cvExplorePalette.setOnClickListener(v -> ((MainActivity) requireActivity()).navigateToPalettes());

        cvPickWithCam.setOnClickListener(v -> {
            ImagePicker.Companion.with(this)
                    .crop()
                    .compress(1024)
                    .maxResultSize(720, 720).start(PICK_IMG);
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == PICK_IMG) {
                img = ImagePicker.Companion.getFile(data);

                startActivity(ImgPaletteActivity.newIntent(requireActivity()).putExtra("img", img));


            }

        }
    }

    @Override
    public void handleError(Throwable throwable) {
        hideLoading();
        ErrorHandlingUtils.handleErrors(throwable);
    }

    @Override
    public void showMyApiMessage(String m) {
        hideLoading();
        showErrorMessage(m);
    }

    @Override
    public void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    private void subscribeViewModel() {

    }

    @Override
    public void showPaletteDetails(PaletteModel.SchemesBean paletteModel) {

        startActivity(PaletteDetailsActivity.newIntent(getActivity()).putExtra("palette", paletteModel));

    }

}
