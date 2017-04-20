package com.example.nguyenthanhthai.foody.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nguyenthanhthai.foody.R;
import com.example.nguyenthanhthai.foody.model.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenThanhThai on 3/23/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    List<Category> categories;
    public int selectItemIndex;
    private List<ViewHolder> viewHolderList;

    private final int HEADER_VIEWTYPE = 1;
    private final int ITEM_VIEWTYPE = 0;

    public CategoryAdapter(List<Category> categories, int selectItemIndex) {
        this.categories = categories;
        this.selectItemIndex = selectItemIndex;
        viewHolderList = new ArrayList<>();
        Log.d("Category", "Category select index " + selectItemIndex);
    }

    /*
    * Set status Selectted item
    * */
    public void setChangeSelected(View viewSelectOn, int positionSelectOn, int positionSelectOff) {

        if (positionSelectOff < viewHolderList.size()) {
            ViewHolder holder = viewHolderList.get(positionSelectOff);
            holder.setDisableSelectItem();
        }

        selectItemIndex = positionSelectOn;

        viewSelectOn.findViewById(R.id.img_is_choose_type).setVisibility(View.VISIBLE);
        ((TextView) viewSelectOn.findViewById(R.id.txt_name_one_category)).setTextColor(Color.parseColor("#ffcc0000"));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == 0) ? HEADER_VIEWTYPE : ITEM_VIEWTYPE;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER_VIEWTYPE:
                return new CategoryAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.category_header, parent, false));
            default:
                return new CategoryAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.category_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        viewHolderList.add(holder);

        //        // Load a bitmap from the drawable folder
        //Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.images);
//        // Resize the bitmap to 150x100 (width x height)
//        Bitmap bMapScaled = Bitmap.createScaledBitmap(bMap, 300, 150, true);

        //String uri = "drawable/fd"+category.getImg().replace(".png","");
//        Bitmap bMap = BitmapFactory.decodeFile(uri);
//        holder.imgType.setImageBitmap(bMap);
        if (position == 0) { //header cannot bind data
            Log.d("DebugCategory", "position Category item" + position + "and default select" + selectItemIndex);
            return;
        }
        Category category = categories.get(position - 1);
        Context context = holder.imgType.getContext();
        int id = context.getResources().getIdentifier("fd" + category.getImg().replace(".png", ""),
                "drawable", context.getPackageName());
        //holder.imgType.setImageResource(id);
        Glide.with(context).load(id).into(holder.imgType);

        holder.txtNameType.setText(category.getName());

        if (selectItemIndex == position) {
            holder.setSelectItem();
        } else {
            holder.setDisableSelectItem();
        }
    }

    @Override
    public int getItemCount() {
        return categories.size() + 1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgType, imgIsChoose;
        TextView txtNameType;

        public ViewHolder(View itemView) {
            super(itemView);
            imgType = (ImageView) itemView.findViewById(R.id.img_icon_category);
            imgIsChoose = (ImageView) itemView.findViewById(R.id.img_is_choose_type);
            txtNameType = (TextView) itemView.findViewById(R.id.txt_name_one_category);
        }

        public void setDisableSelectItem() {
            imgIsChoose.setVisibility(View.GONE);
            txtNameType.setTextColor(Color.parseColor("#ff555555"));
        }

        public void setSelectItem() {
            txtNameType.setTextColor(Color.parseColor("#ffcc0000"));
            imgIsChoose.setVisibility(View.VISIBLE);
        }

    }
}
