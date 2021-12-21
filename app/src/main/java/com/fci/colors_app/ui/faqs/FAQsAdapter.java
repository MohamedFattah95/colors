package com.fci.colors_app.ui.faqs;

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
import com.fci.colors_app.data.models.FAQsModel;
import com.fci.colors_app.ui.base.BaseViewHolder;
import com.fci.colors_app.utils.ImageUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FAQsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_FAQ = 1;

    private Callback mCallback;
    private List<FAQsModel> mFAQsList;

    public FAQsAdapter(List<FAQsModel> list) {
        mFAQsList = list;
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
            case VIEW_TYPE_FAQ:
                return new FAQViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faq, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_view, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mFAQsList != null && mFAQsList.size() > 0) {
            return VIEW_TYPE_FAQ;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (mFAQsList != null && mFAQsList.size() > 0) {
            return mFAQsList.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<FAQsModel> list) {
        mFAQsList.clear();
        mFAQsList.addAll(list);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mFAQsList.clear();
        notifyDataSetChanged();
    }

    public interface Callback {
    }

    @SuppressLint("NonConstantResourceId")
    public class FAQViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_question)
        AppCompatTextView tvQuestion;
        @BindView(R.id.ll_expand_collapse)
        LinearLayout llExpandCollapse;
        @BindView(R.id.iv_expand_collapse)
        ImageView ivExpandCollapse;
        @BindView(R.id.tv_answer)
        AppCompatTextView tvAnswer;


        public FAQViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        protected void clear() {

        }

        @SuppressLint("SetTextI18n")
        public void onBind(int position) {

            FAQsModel faQsModel = mFAQsList.get(position);

            tvQuestion.setText(faQsModel.getQuestion());
            tvAnswer.setText(faQsModel.getAnswer());

            llExpandCollapse.setOnClickListener(v -> {
                ImageUtils.rotate(ivExpandCollapse, tvAnswer.getVisibility() == View.VISIBLE ? 0 : 1);
                tvAnswer.setVisibility(tvAnswer.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            });

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
