package com.example.nguyenthanhthai.foody.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyenthanhthai.foody.R;
import com.example.nguyenthanhthai.foody.holder.LastestViewHolder;
import com.example.nguyenthanhthai.foody.model.Lastest;

import java.util.List;

/**
 * Created by NguyenThanhThai on 4/4/2017.
 */

public class LastestRecyclerViewAdapter extends RecyclerView.Adapter<LastestViewHolder> {

    List<Lastest> lastestList;
    public int selectItemIndex;

    public LastestRecyclerViewAdapter(List<Lastest> lastestList, int selectItemIndex) {
        this.lastestList = lastestList;
        this.selectItemIndex=selectItemIndex;
    }

    @Override
    public LastestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_home_collection_item, parent, false);

        return new LastestViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LastestViewHolder holder, int position) {
        if (position==selectItemIndex){
            holder.setValueItemName(lastestList.get(position),true);
        }
        else {
            holder.setValueItemName(lastestList.get(position), false);
        }
    }

    @Override
    public int getItemCount() {
        return lastestList.size();
    }
}
