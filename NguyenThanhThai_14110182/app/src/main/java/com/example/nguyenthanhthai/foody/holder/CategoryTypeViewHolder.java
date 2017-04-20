package com.example.nguyenthanhthai.foody.holder;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nguyenthanhthai.foody.R;
import com.example.nguyenthanhthai.foody.adapter.CategoryTypeAdapter;
import com.example.nguyenthanhthai.foody.model.CategoryType;

/**
 * Created by NguyenThanhThai on 4/7/2017.
 */

public class CategoryTypeViewHolder extends RecyclerView.ViewHolder {

    ImageView imgIconItem;
    TextView textItemName;
    View roundColor;
    public View parentViewItem;

    public CategoryTypeViewHolder(View itemView) {
        super(itemView);
        this.parentViewItem = itemView;
        imgIconItem = (ImageView) itemView.findViewById(R.id.imgIconItem);
        textItemName = (TextView) itemView.findViewById(R.id.text);
        roundColor = (View) itemView.findViewById(R.id.roundColor);
    }

    public void setValueItem(final CategoryType item) {
        textItemName.setText(item.getName());

        Context context = imgIconItem.getContext();
        Glide.with(context)
                .load(context.getResources().getIdentifier(item.getImageBackgroud(), "drawable", context.getPackageName()))
                .into(imgIconItem);
        if (item.getColorBackgroud() != null && item.getColorBackgroud().compareTo("") != 0) {
            Drawable background = roundColor.getBackground();
            if (background instanceof ShapeDrawable) {
                // cast to 'ShapeDrawable'
                ShapeDrawable shapeDrawable = (ShapeDrawable) background;
                shapeDrawable.getPaint().setColor(Color.parseColor(item.getColorBackgroud()));
            } else if (background instanceof GradientDrawable) {
                // cast to 'GradientDrawable'
                GradientDrawable gradientDrawable = (GradientDrawable) background;
                gradientDrawable.setColor(Color.parseColor(item.getColorBackgroud()));
            } else if (background instanceof ColorDrawable) {
                // alpha value may need to be set again after this call
                ColorDrawable colorDrawable = (ColorDrawable) background;
                colorDrawable.setColor(Color.parseColor(item.getColorBackgroud()));
            }
        }
//TODO Envent Adapter
//        parentViewItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onClickLisener.onClickItem(item);
//            }
//        });
    }

    //Event click set delegate
    public void setOnCLickItemEvent(CategoryTypeAdapter.OnClickLisener onClickLisener) {
        this.onClickLisener = onClickLisener;
    }

    CategoryTypeAdapter.OnClickLisener onClickLisener;

    public interface OnClickLisener {
        public void onClickItem(CategoryType categoryType);
    }
}
