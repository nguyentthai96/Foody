package com.example.nguyenthanhthai.foody.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyenthanhthai.foody.R;
import com.example.nguyenthanhthai.foody.holder.CategoryTypeViewHolder;
import com.example.nguyenthanhthai.foody.model.CategoryType;

import java.util.List;

/**
 * Created by NguyenThanhThai on 4/7/2017.
 */

public class CategoryTypeAdapter extends RecyclerView.Adapter<CategoryTypeViewHolder> {

    List<CategoryType> items;
    public int selectItemIndex;

    public CategoryTypeAdapter(List<CategoryType> items) {
        this.items = items;
    }

    @Override
    public CategoryTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_type_item, parent, false);

        return new CategoryTypeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryTypeViewHolder holder, int position) {
        holder.setValueItem(items.get(position));

        final int po=position;
        holder.parentViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLisener.onClickItem(items.get(po));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnCLickItemEventAdapter(OnClickLisener onClickLisener) {
        this.onClickLisener = onClickLisener;
    }

    OnClickLisener onClickLisener;

    public interface OnClickLisener {
        public void onClickItem(CategoryType categoryType);
    }
}