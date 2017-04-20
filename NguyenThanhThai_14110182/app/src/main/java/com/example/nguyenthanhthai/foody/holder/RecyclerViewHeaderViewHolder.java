package com.example.nguyenthanhthai.foody.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nguyenthanhthai.foody.R;
import com.example.nguyenthanhthai.foody.custommodel.HeaderItem;

/**
 * Created by NguyenThanhThai on 4/7/2017.
 */

public class RecyclerViewHeaderViewHolder extends RecyclerView.ViewHolder {

    ImageView imgIconItem;
    TextView textItemName;

    public RecyclerViewHeaderViewHolder(View itemView) {
        super(itemView);
        imgIconItem = (ImageView) itemView.findViewById(R.id.imgIconItem);
        textItemName = (TextView) itemView.findViewById(R.id.textItemName);
    }

    public void setValueItem(HeaderItem item) {
        textItemName.setText(item.getName());

        Context context = imgIconItem.getContext();
        Glide.with(context)
                .load(context.getResources().getIdentifier(item.getImage(), "drawable", context.getPackageName()))
                .into(imgIconItem);
    }
}
