package com.example.nguyenthanhthai.foody.holder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nguyenthanhthai.foody.R;
import com.example.nguyenthanhthai.foody.model.Lastest;

import java.io.File;

/**
 * Created by NguyenThanhThai on 4/4/2017.
 */

public class LastestViewHolder extends RecyclerView.ViewHolder {

    ImageView imgIcon;
    TextView textItemListName;
    CheckBox chkLastestAddedToList;

    public LastestViewHolder(View itemView) {
        super(itemView);
        imgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);
        textItemListName = (TextView) itemView.findViewById(R.id.textItemListName);
        chkLastestAddedToList = (CheckBox) itemView.findViewById(R.id.chkLastestAddedToList);
    }

    public void setValueItemName(Lastest lastest, boolean isSelected){
        textItemListName.setText(lastest.getNameItemList());

        if (isSelected){
            setSelectedItems(lastest);
        }else {
            setDisableSelectedItems(lastest);
        }
    }

    public void setSelectedItems(Lastest lastest) {
        chkLastestAddedToList.setVisibility(View.VISIBLE);
        textItemListName.setTextColor(Color.RED);
        Context context=imgIcon.getContext();
        Glide.with(context)
                .load(context.getResources().getIdentifier(lastest.getImageNameIconSelect(), "drawable",  context.getPackageName()))
                .into(imgIcon);
    }

    public void setDisableSelectedItems(Lastest lastest) {
        chkLastestAddedToList.setVisibility(View.GONE);
        textItemListName.setTextColor(Color.GRAY);
        Context context=imgIcon.getContext();
        Glide.with(context)
                .load(context.getResources().getIdentifier(lastest.getImageNameIcon(), "drawable",  context.getPackageName()))
                .into(imgIcon);
    }
}
