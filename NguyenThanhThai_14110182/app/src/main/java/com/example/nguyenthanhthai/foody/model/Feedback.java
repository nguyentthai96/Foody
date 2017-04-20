package com.example.nguyenthanhthai.foody.model;

import com.orm.SugarRecord;

/**
 * Created by NguyenThanhThai on 3/23/2017.
 */

public class Feedback extends SugarRecord {
    Long id;
    String name;
    Double rating;
    String commenttrim;
    String comment;
    String avatar;
    Integer restaurantId;
    String reviewurl;


    public Feedback() {
    }

    public Feedback(String userNameDisplay, String commentContext, String avatar, Double rating) {
        this.name = userNameDisplay;
        this.commenttrim = commentContext;
        this.avatar = avatar;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommenttrim() {
        return commenttrim;
    }

    public void setCommenttrim(String commenttrim) {
        this.commenttrim = commenttrim;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
