package com.fci.colors_app.ui.home;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
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
    private List<PaletteModel> mPaletteList;

    public PalettesAdapter(List<PaletteModel> list) {
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

    public void addItems(List<PaletteModel> list) {
        mPaletteList.clear();
        mPaletteList.addAll(list);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mPaletteList.clear();
        notifyDataSetChanged();
    }

    public interface Callback {
    }

    @SuppressLint("NonConstantResourceId")
    public class PaletteViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_question)
        AppCompatTextView tvQuestion;
        @BindView(R.id.ll_expand_collapse)
        LinearLayout llExpandCollapse;
        @BindView(R.id.iv_expand_collapse)
        ImageView ivExpandCollapse;
        @BindView(R.id.tv_answer)
        AppCompatTextView tvAnswer;


        public PaletteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        protected void clear() {

        }

        @SuppressLint("SetTextI18n")
        public void onBind(int position) {

            PaletteModel paletteModel = mPaletteList.get(position);


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
