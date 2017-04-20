package com.example.nguyenthanhthai.foody.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyenthanhthai.foody.R;
import com.example.nguyenthanhthai.foody.custommodel.HeaderItem;
import com.example.nguyenthanhthai.foody.holder.RecyclerViewHeaderViewHolder;

import java.util.List;

/**
 * Created by NguyenThanhThai on 4/7/2017.
 */

public class HeaderRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHeaderViewHolder> {

    List<HeaderItem> headerItems;
    public int selectItemIndex;

    public HeaderRecyclerViewAdapter(List<HeaderItem> headerItems) {
        this.headerItems = headerItems;
        this.selectItemIndex = selectItemIndex;
    }

    @Override
    public RecyclerViewHeaderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_header_recyclerview, parent, false);

        return new RecyclerViewHeaderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHeaderViewHolder holder, int position) {
        holder.setValueItem(headerItems.get(position));
    }

    @Override
    public int getItemCount() {
        return headerItems.size();
    }
}
