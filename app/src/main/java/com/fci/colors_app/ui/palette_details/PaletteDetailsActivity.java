
package com.fci.colors_app.ui.palette_details;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fci.colors_app.R;
import com.fci.colors_app.data.models.PaletteModel;
import com.fci.colors_app.di.component.ActivityComponent;
import com.fci.colors_app.ui.base.BaseActivity;
import com.fci.colors_app.utils.ErrorHandlingUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("NonConstantResourceId")
public class PaletteDetailsActivity extends BaseActivity<PaletteDetailsViewModel> implements PaletteDetailsNavigator {

    @BindView(R.id.color1)
    TextView color1;
    @BindView(R.id.color2)
    TextView color2;
    @BindView(R.id.color3)
    TextView color3;
    @BindView(R.id.color4)
    TextView color4;
    @BindView(R.id.color5)
    TextView color5;

    PaletteModel.SchemesBean paletteModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, PaletteDetailsActivity.class);
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette_details);
        ButterKnife.bind(this);
        mViewModel.setNavigator(this);

        paletteModel = (PaletteModel.SchemesBean) getIntent().getSerializableExtra("palette");

        if (paletteModel.getColors() != null && !paletteModel.getColors().isEmpty()) {

            try {
                color1.setBackgroundColor(Color.parseColor("#" + paletteModel.getColors().get(0)));
                color1.setText("#" + paletteModel.getColors().get(0));

                if (paletteModel.getColors().size() >= 2) {
                    color2.setBackgroundColor(Color.parseColor("#" + paletteModel.getColors().get(1)));
                    color2.setVisibility(View.VISIBLE);
                    color2.setText("#" + paletteModel.getColors().get(1));
                } else
                    color2.setVisibility(View.GONE);

                if (paletteModel.getColors().size() >= 3) {
                    color3.setBackgroundColor(Color.parseColor("#" + paletteModel.getColors().get(2)));
                    color3.setVisibility(View.VISIBLE);
                    color3.setText("#" + paletteModel.getColors().get(2));
                } else
                    color3.setVisibility(View.GONE);

                if (paletteModel.getColors().size() >= 4) {
                    color4.setBackgroundColor(Color.parseColor("#" + paletteModel.getColors().get(3)));
                    color4.setVisibility(View.VISIBLE);
                    color4.setText("#" + paletteModel.getColors().get(3));
                } else
                    color4.setVisibility(View.GONE);

                if (paletteModel.getColors().size() >= 5) {
                    color5.setBackgroundColor(Color.parseColor("#" + paletteModel.getColors().get(4)));
                    color5.setVisibility(View.VISIBLE);
                    color5.setText("#" + paletteModel.getColors().get(4));
                } else
                    color5.setVisibility(View.GONE);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        subscribeViewModel();

    }

    private void subscribeViewModel() {
        mViewModel.getSettingsLiveData().observe(this, response -> {
            hideLoading();

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

}
