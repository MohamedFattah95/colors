
package com.fci.colors_app.ui.img_palette;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.palette.graphics.Palette;

import com.bumptech.glide.Glide;
import com.fci.colors_app.R;
import com.fci.colors_app.di.component.ActivityComponent;
import com.fci.colors_app.ui.base.BaseActivity;
import com.fci.colors_app.utils.ErrorHandlingUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("NonConstantResourceId")
public class ImgPaletteActivity extends BaseActivity<ImgPaletteViewModel> implements ImgPaletteNavigator {

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
    @BindView(R.id.ivPic)
    ImageView ivPic;

    File img = null;

    public static Intent newIntent(Context context) {
        return new Intent(context, ImgPaletteActivity.class);
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n", "CheckResult"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_palette);
        ButterKnife.bind(this);
        mViewModel.setNavigator(this);

        img = (File) getIntent().getSerializableExtra("img");
        Glide.with(this).load(img.getPath()).into(ivPic);
        createPaletteAsync(BitmapFactory.decodeFile(img.getPath()));

        subscribeViewModel();

    }

    String toVerticalText(String text) {

        StringBuilder verticalText = new StringBuilder("#\n");
        for (int i = 0; i < text.length(); i++) {
            verticalText.append(text.charAt(i)).append("\n");
        }
        return verticalText.toString();
    }

    public void createPaletteAsync(Bitmap bitmap) {
        Palette.from(bitmap).generate(p -> {

//            color1.setText(String.valueOf(paletteModel.getDarkMutedColor(0)));
//            color1.setBackgroundColor(Color.parseColor("#" +
//                    String.valueOf(paletteModel.getDarkMutedColor(0)).replace("-", "")));

            Palette.Swatch vibrantSwatch = p.getVibrantSwatch();

            if (vibrantSwatch != null) {
                color1.setBackgroundColor(vibrantSwatch.getRgb());
                color1.setText(toVerticalText(
                        String.valueOf(vibrantSwatch.getRgb()).replace("-", "")));
                color1.setTextColor(vibrantSwatch.getTitleTextColor());
            } else {
                color1.setVisibility(View.GONE);
            }


            Palette.Swatch lightVibrantSwatch = p.getLightVibrantSwatch();

            if (lightVibrantSwatch != null) {
                color2.setBackgroundColor(lightVibrantSwatch.getRgb());
                color2.setText(toVerticalText(
                        String.valueOf(lightVibrantSwatch.getRgb()).replace("-", "")));
                color2.setTextColor(lightVibrantSwatch.getTitleTextColor());
            } else {
                color2.setVisibility(View.GONE);
            }


            Palette.Swatch mutedSwatch = p.getMutedSwatch();

            if (mutedSwatch != null) {
                color3.setBackgroundColor(mutedSwatch.getRgb());
                color3.setText(toVerticalText(
                        String.valueOf(mutedSwatch.getRgb()).replace("-", "")));
                color3.setTextColor(mutedSwatch.getTitleTextColor());
            } else {
                color3.setVisibility(View.GONE);
            }


            Palette.Swatch lightMutedSwatch = p.getLightMutedSwatch();

            if (lightMutedSwatch != null) {
                color4.setBackgroundColor(lightMutedSwatch.getRgb());
                color4.setText(toVerticalText(
                        String.valueOf(lightMutedSwatch.getRgb()).replace("-", "")));
                color4.setTextColor(lightMutedSwatch.getTitleTextColor());
            } else {
                color4.setVisibility(View.GONE);
            }


            Palette.Swatch darkVibrantSwatch = p.getDarkVibrantSwatch();

            if (darkVibrantSwatch != null) {
                color5.setBackgroundColor(darkVibrantSwatch.getRgb());
                color5.setText(toVerticalText(
                        String.valueOf(darkVibrantSwatch.getRgb()).replace("-", "")));
                color5.setTextColor(darkVibrantSwatch.getTitleTextColor());
            } else {
                color5.setVisibility(View.GONE);
            }


//            paletteModel.getLightVibrantColor(0);

//            color1.setBackgroundColor(Color.parseColor("#" + paletteModel.getLightVibrantColor()));
//            color1.setText("#" + paletteModel.getColors().get(0));
//
//            if (paletteModel.getColors().size() >= 2) {
//                color2.setBackgroundColor(Color.parseColor("#" + paletteModel.getColors().get(1)));
//                color2.setVisibility(View.VISIBLE);
//                color2.setText("#" + paletteModel.getColors().get(1));
//            } else
//                color2.setVisibility(View.GONE);
//
//            if (paletteModel.getColors().size() >= 3) {
//                color3.setBackgroundColor(Color.parseColor("#" + paletteModel.getColors().get(2)));
//                color3.setVisibility(View.VISIBLE);
//                color3.setText("#" + paletteModel.getColors().get(2));
//            } else
//                color3.setVisibility(View.GONE);
//
//            if (paletteModel.getColors().size() >= 4) {
//                color4.setBackgroundColor(Color.parseColor("#" + paletteModel.getColors().get(3)));
//                color4.setVisibility(View.VISIBLE);
//                color4.setText("#" + paletteModel.getColors().get(3));
//            } else
//                color4.setVisibility(View.GONE);
//
//            if (paletteModel.getColors().size() >= 5) {
//                color5.setBackgroundColor(Color.parseColor("#" + paletteModel.getColors().get(4)));
//                color5.setVisibility(View.VISIBLE);
//                color5.setText("#" + paletteModel.getColors().get(4));
//            } else
//                color5.setVisibility(View.GONE);


        });
    }

    private void subscribeViewModel() {

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
