package com.example.nguyenthanhthai.foody.custommodel;

import android.graphics.Bitmap;
import android.media.Image;

import com.example.nguyenthanhthai.foody.model.Feedback;

/**
 * Created by NguyenThanhThai on 3/23/2017.
 */

public class PlaceRecycler {
    String nameRestaurant;
    String address;
    String image;
    Double ratingRestaurant;
    Feedback feedbackOne;
    Feedback feedbackTwo;

    public PlaceRecycler() {
    }

    public PlaceRecycler(String nameRestaurant, String address, String image, Double ratingRestaurant, Feedback feedbackOne, Feedback feedbackTwo) {
        this.nameRestaurant = nameRestaurant;
        this.address = address;
        this.image = image;
        this.ratingRestaurant = ratingRestaurant;
        this.feedbackOne = feedbackOne;
        this.feedbackTwo = feedbackTwo;
    }

    public String getNameRestaurant() {
        return nameRestaurant;
    }

    public void setNameRestaurant(String nameRestaurant) {
        this.nameRestaurant = nameRestaurant;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getRatingRestaurant() {
        return ratingRestaurant;
    }

    public void setRatingRestaurant(Double ratingRestaurant) {
        this.ratingRestaurant = ratingRestaurant;
    }

    public Feedback getFeedbackOne() {
        return feedbackOne;
    }

    public void setFeedbackOne(Feedback feedbackOne) {
        this.feedbackOne = feedbackOne;
    }

    public Feedback getFeedbackTwo() {
        return feedbackTwo;
    }

    public void setFeedbackTwo(Feedback feedbackTwo) {
        this.feedbackTwo = feedbackTwo;
    }
}
