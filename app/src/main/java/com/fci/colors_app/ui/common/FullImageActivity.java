package com.fci.colors_app.ui.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jsibbold.zoomage.ZoomageView;
import com.fci.colors_app.R;
import com.fci.colors_app.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("NonConstantResourceId")
public class FullImageActivity extends AppCompatActivity {

    @BindView(R.id.image)
    ZoomageView image;

    public static Intent newIntent(Context context) {
        return new Intent(context, FullImageActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        ButterKnife.bind(this);

        String flag = getIntent().getStringExtra("flag");
        if (flag != null && flag.equals("chat"))
            ImageUtils.loadChatZoomImage(getIntent().getStringExtra("image"), image, this);
        else
            ImageUtils.loadZoomImage(getIntent().getStringExtra("image"), image, this);
    }
}
