package com.fci.colors_app.ui.palettes;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fci.colors_app.R;
import com.fci.colors_app.data.models.PaletteModel;
import com.fci.colors_app.ui.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PalettesAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_PALETTE = 1;

    private Callback mCallback;
    private List<PaletteModel.SchemesBean> mPaletteList;

    public PalettesAdapter(List<PaletteModel.SchemesBean> list) {
        mPaletteList = list;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_PALETTE:
                return new PaletteViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_palette, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_view, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mPaletteList != null && mPaletteList.size() > 0) {
            return VIEW_TYPE_PALETTE;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (mPaletteList != null && mPaletteList.size() > 0) {
            return mPaletteList.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<PaletteModel.SchemesBean> list) {
        mPaletteList.clear();
        mPaletteList.addAll(list);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mPaletteList.clear();
        notifyDataSetChanged();
    }

    public interface Callback {
        void showPaletteDetails(PaletteModel.SchemesBean paletteModel);
    }

    @SuppressLint("NonConstantResourceId")
    public class PaletteViewHolder extends BaseViewHolder {

        @BindView(R.id.iv1)
        ImageView iv1;
        @BindView(R.id.iv2)
        ImageView iv2;
        @BindView(R.id.iv3)
        ImageView iv3;
        @BindView(R.id.iv4)
        ImageView iv4;
        @BindView(R.id.iv5)
        ImageView iv5;


        public PaletteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        protected void clear() {

        }

        @SuppressLint({"SetTextI18n", "ResourceType"})
        public void onBind(int position) {

            PaletteModel.SchemesBean paletteModel = mPaletteList.get(position);

            if (paletteModel.getColors() != null && !paletteModel.getColors().isEmpty()) {

                try {
                    iv1.setBackgroundColor(Color.parseColor("#" + paletteModel.getColors().get(0)));

                    if (paletteModel.getColors().size() >= 2) {
                        iv2.setBackgroundColor(Color.parseColor("#" + paletteModel.getColors().get(1)));
                        iv2.setVisibility(View.VISIBLE);
                    } else
                        iv2.setVisibility(View.GONE);

                    if (paletteModel.getColors().size() >= 3) {
                        iv3.setBackgroundColor(Color.parseColor("#" + paletteModel.getColors().get(2)));
                        iv3.setVisibility(View.VISIBLE);
                    } else
                        iv3.setVisibility(View.GONE);

                    if (paletteModel.getColors().size() >= 4) {
                        iv4.setBackgroundColor(Color.parseColor("#" + paletteModel.getColors().get(3)));
                        iv4.setVisibility(View.VISIBLE);
                    } else
                        iv4.setVisibility(View.GONE);

                    if (paletteModel.getColors().size() >= 5) {
                        iv5.setBackgroundColor(Color.parseColor("#" + paletteModel.getColors().get(4)));
                        iv5.setVisibility(View.VISIBLE);
                    } else
                        iv5.setVisibility(View.GONE);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            itemView.setOnClickListener(v -> mCallback.showPaletteDetails(paletteModel));

        }
    }

    public class EmptyViewHolder extends BaseViewHolder {


        EmptyViewHolder(View itemView) {
            super(itemView);

        }

        @Override
        public void onBind(int position) {

        }


    }
}
