package com.example.nguyenthanhthai.foody.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nguyenthanhthai.foody.R;
import com.example.nguyenthanhthai.foody.model.Feedback;
import com.example.nguyenthanhthai.foody.model.Restaurant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by NguyenThanhThai on 3/23/2017.
 */

public class FoodRecyclerAdapter extends RecyclerView.Adapter<FoodRecyclerAdapter.ViewHolderShowListItem> {
    List<Restaurant> items;

    public FoodRecyclerAdapter(List<Restaurant> items) {
        this.items = items;
    }

    public class ViewHolderShowListItem extends RecyclerView.ViewHolder {

        ImageView imgItem;
        TextView txtName, txtRestaurant, txtAddress, txtNameReview, txtDate;
        CircleImageView imgAvatarReview;

        public ViewHolderShowListItem(View itemView) {
            super(itemView);
            imgItem = (ImageView) itemView.findViewById(R.id.imgFood);
            imgAvatarReview = (CircleImageView) itemView.findViewById(R.id.imgAvatar);
            txtName = (TextView) itemView.findViewById(R.id.foodName);
            txtRestaurant = (TextView) itemView.findViewById(R.id.restaurantName);
            txtAddress = (TextView) itemView.findViewById(R.id.address);
            txtNameReview = (TextView) itemView.findViewById(R.id.accoutName);
            txtDate = (TextView) itemView.findViewById(R.id.dateFeeback);
        }
    }

    @Override
    public ViewHolderShowListItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_item_recycler, parent, false);
        ViewHolderShowListItem viewHolderShowListItem = new ViewHolderShowListItem(view);

        return viewHolderShowListItem;
    }

    @Override
    public void onBindViewHolder(ViewHolderShowListItem holder, int position) {
        Restaurant item = items.get(position);

        if (!item.getImg().equals("")) {
            Context context = holder.imgItem.getContext();
            int imageResource = context.getResources().getIdentifier("fdi" + item.getImg(), "drawable", context.getPackageName());
            if (imageResource != 0) {
                Glide.with(context).load(imageResource).into(holder.imgItem);
                //holder.imgItem.setImageResource(imageResource);
            } else {
                Glide.with(context).load(R.drawable.fdi1).into(holder.imgItem);
                //holder.imgItem.setImageResource(R.drawable.fdi1);
            }
        } else {
            holder.imgItem.setImageDrawable(null);
        }

        holder.txtRestaurant.setText(item.getName());

        holder.txtAddress.setText(item.getAddress());

        Feedback review = item.getFirstFeedbacks();
        if (review != null) {
            Context context = holder.imgAvatarReview.getContext();
            int imageResource = context.getResources().getIdentifier("ava" + review.getAvatar(), "drawable", context.getPackageName());
            if (imageResource != 0) {
                Glide.with(context).load(imageResource).into(holder.imgAvatarReview);
                //holder.imgAvatarReview.setImageResource(imageResource);
            } else {
                Glide.with(context).load(R.drawable.ava3).into(holder.imgAvatarReview);
                //holder.imgAvatarReview.setImageResource(R.drawable.ava3);
            }
            holder.txtNameReview.setText(review.getName());
            DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");//foramt date
            String date = df1.format(Calendar.getInstance().getTime());
            holder.txtDate.setText(date);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
