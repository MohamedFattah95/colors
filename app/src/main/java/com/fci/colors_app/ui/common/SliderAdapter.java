package com.fci.colors_app.ui.common;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.fci.colors_app.R;
import com.fci.colors_app.data.models.SliderModel;
import com.fci.colors_app.ui.web_view.WebViewActivity;
import com.fci.colors_app.utils.ImageUtils;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private List<SliderModel.ImagesBean> mImgsList;

    public SliderAdapter(List<SliderModel.ImagesBean> mImgsList) {
        this.mImgsList = mImgsList;
    }


    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        @SuppressLint("InflateParams")
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        viewHolder.onBind(position);
    }

    public void addItems(List<SliderModel.ImagesBean> list) {
        mImgsList.clear();
        mImgsList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mImgsList.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {
        View itemView;
        ImageView imageView;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.sliderImageView);
            this.itemView = itemView;
        }

        public void onBind(int position) {
            SliderModel.ImagesBean sliderModel = mImgsList.get(position);
            ImageUtils.loadImageLink(sliderModel.getImage(), imageView, itemView.getContext());

            imageView.setOnClickListener(v -> {
                if (sliderModel.getLink() != null)
                    itemView.getContext().startActivity(WebViewActivity.newIntent(itemView.getContext())
                            .putExtra("link", sliderModel.getLink())
                            .putExtra("title", itemView.getResources().getString(R.string.app_name)));
                else
                    Toast.makeText(itemView.getContext(), itemView.getContext().getString(R.string.link_not_available), Toast.LENGTH_SHORT).show();
            });

        }
    }


    private void openLink(String link, Context context) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(link));
            context.startActivity(intent);
        } catch (ActivityNotFoundException | NullPointerException e) {
            e.printStackTrace();
            Toast.makeText(context, context.getString(R.string.link_not_available), Toast.LENGTH_SHORT).show();
        }
    }
}
