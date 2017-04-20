package com.example.nguyenthanhthai.foody.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nguyenthanhthai.foody.R;
import com.example.nguyenthanhthai.foody.model.Feedback;
import com.example.nguyenthanhthai.foody.model.Restaurant;

import java.util.List;

/**
 * Created by NguyenThanhThai on 3/23/2017.
 */

public class PlacesRecyclerAdapter extends RecyclerView.Adapter<PlacesRecyclerAdapter.MyViewHolder> {

    private List<Restaurant> restaurantList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nameRestaurant, address, raitingRestaurant;
        public ImageView imageRestaurant;

        public ImageView avatar1, avatar2;
        public TextView accNameDisplay1, accNameDisplay2;
        public TextView feedBack1, feedBack2;
        public TextView raitingFeedBack1, raitingFeedBack2;

        public TextView countComment, countPostImage;

        public LinearLayout deliveryNow, tableNow, eCard, bankCard;

        public MyViewHolder(View view) {
            super(view);
            nameRestaurant = (TextView) view.findViewById(R.id.nameRestaurant);
            address = (TextView) view.findViewById(R.id.address);
            raitingRestaurant = (TextView) view.findViewById(R.id.raitingRestaurant);
            imageRestaurant = (ImageView) view.findViewById(R.id.imageRestaurant);
            avatar1 = (ImageView) view.findViewById(R.id.feedBackContent1).findViewById(R.id.avatar);
            accNameDisplay1 = (TextView) view.findViewById(R.id.feedBackContent1).findViewById(R.id.accNameDisplay);
            feedBack1 = (TextView) view.findViewById(R.id.feedBackContent1).findViewById(R.id.feedBackContent);
            raitingFeedBack1 = (TextView) view.findViewById(R.id.feedBackContent1).findViewById(R.id.raitingFeedBack);

            avatar2 = (ImageView) view.findViewById(R.id.feedBackContent2).findViewById(R.id.avatar);
            accNameDisplay2 = (TextView) view.findViewById(R.id.feedBackContent2).findViewById(R.id.accNameDisplay);
            feedBack2 = (TextView) view.findViewById(R.id.feedBackContent2).findViewById(R.id.feedBackContent);
            raitingFeedBack2 = (TextView) view.findViewById(R.id.feedBackContent2).findViewById(R.id.raitingFeedBack);

            countComment = (TextView) view.findViewById(R.id.iconComment);
            countPostImage = (TextView) view.findViewById(R.id.cameraImage);

            deliveryNow = (LinearLayout) view.findViewById(R.id.btn_delivery_now);
            tableNow = (LinearLayout) view.findViewById(R.id.btn_table_now);
            eCard = (LinearLayout) view.findViewById(R.id.btn_e_card);
            bankCard = (LinearLayout) view.findViewById(R.id.btn_bank_card);
        }
    }


    public PlacesRecyclerAdapter(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.place_item_recycler, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.get(position);
        holder.nameRestaurant.setText(restaurant.getName());
        holder.address.setText(restaurant.getAddress());
        holder.raitingRestaurant.setText(String.format("%.1f", restaurant.getAvgrating()));

        Context context = holder.imageRestaurant.getContext();
        int id = context.getResources().getIdentifier("fdi" + restaurant.getImg().replace(".png", ""),
                "drawable", context.getPackageName());

        //holder.imageRestaurant.setImageResource(id);
        Glide.with(context).load(id).into(holder.imageRestaurant);
        List<Feedback> feedbacks = restaurant.getTwoFeedbacks();

        if (feedbacks.size() >= 2) {

            holder.accNameDisplay1.setText(feedbacks.get(0).getName());
            holder.feedBack1.setText(feedbacks.get(0).getCommenttrim());
            holder.raitingFeedBack1.setText(String.format("%.1f", feedbacks.get(0).getRating()));

            context = holder.avatar1.getContext();
            id = context.getResources().getIdentifier("ava" + feedbacks.get(0).getAvatar().replace(".png", ""),
                    "drawable", context.getPackageName());
            Glide.with(context).load(id).into(holder.avatar1);
            //holder.avatar1.setImageResource(id);

            holder.accNameDisplay2.setText(feedbacks.get(1).getName());
            holder.feedBack2.setText(feedbacks.get(1).getCommenttrim());
            holder.raitingFeedBack2.setText(String.format("%.1f", feedbacks.get(1).getRating()));

            context = holder.avatar2.getContext();
            id = context.getResources().getIdentifier("ava" + feedbacks.get(1).getAvatar().replace(".png", ""),
                    "drawable", context.getPackageName());
            Glide.with(context).load(id).into(holder.avatar2);
            //holder.avatar2.setImageResource(id);
        }

        holder.countComment.setText(restaurant.getTotalreviews().toString());
        holder.countPostImage.setText(restaurant.getTotalpictures().toString());
        int methodOrder = 1;
        try {
            methodOrder = restaurant.getMethodOrder().intValue();
        } catch (Exception e) {
            e.toString();
        }
        if (methodOrder % 3 == 0) {
            holder.deliveryNow.setVisibility(View.VISIBLE);
        }
        if (methodOrder % 3 == 0) {
            holder.tableNow.setVisibility(View.VISIBLE);
        }
        if (methodOrder % 5 == 0) {
            holder.eCard.setVisibility(View.VISIBLE);
        }
        if (methodOrder % 7 == 0) {
            holder.bankCard.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }
}