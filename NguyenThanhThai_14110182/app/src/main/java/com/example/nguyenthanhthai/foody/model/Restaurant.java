package com.example.nguyenthanhthai.foody.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;
import java.util.Random;

/**
 * Created by NguyenThanhThai on 3/23/2017.
 */

public class Restaurant extends SugarRecord {
    //MethodOrder 2 3 5 7
    Long id;
    Integer restaurantid, categorytypeid, districtid, streetid, totalpictures, totalreviews, categoryid, methodOrder;
    Double avgrating;
    String address, name, img, videoname;
    @Ignore
    List<Feedback> feedbacks;

    public Restaurant() {
    }

    public Restaurant(Integer restaurantid, Integer categorytypeid, Integer districtid, Integer streetid, Integer totalpictures, Integer totalreviews, Integer categoryid, Double avgrating, String address, String name, String img, List<Feedback> feedbacks) {
        this.restaurantid = restaurantid;
        this.categorytypeid = categorytypeid;
        this.districtid = districtid;
        this.streetid = streetid;
        this.totalpictures = totalpictures;
        this.totalreviews = totalreviews;
        this.categoryid = categoryid;
        this.avgrating = avgrating;
        this.address = address;
        this.name = name;
        this.img = img;
        this.feedbacks = feedbacks;
    }

    protected Restaurant(Parcel in) {
        address = in.readString();
        name = in.readString();
        img = in.readString();
        videoname = in.readString();
    }

    public Integer getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(Integer restaurantid) {
        this.restaurantid = restaurantid;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getDistrictid() {
        return districtid;
    }

    public void setDistrictid(Integer districtid) {
        this.districtid = districtid;
    }

    public Integer getTotalpictures() {
        return totalpictures;
    }

    public void setTotalpictures(Integer totalpictures) {
        this.totalpictures = totalpictures;
    }

    public Integer getTotalreviews() {
        return totalreviews;
    }

    public void setTotalreviews(Integer totalreviews) {
        this.totalreviews = totalreviews;
    }

    public Integer getTypeid() {
        return categorytypeid;
    }

    public void setTypeid(Integer typeid) {
        this.categorytypeid = categorytypeid;
    }

    public Double getAvgrating() {
        return avgrating;
    }

    public void setAvgrating(Double avgrating) {
        this.avgrating = avgrating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getCategorytypeid() {
        return categorytypeid;
    }

    public void setCategorytypeid(Integer categorytypeid) {
        this.categorytypeid = categorytypeid;
    }

    public Integer getStreetid() {
        return streetid;
    }

    public void setStreetid(Integer streetid) {
        this.streetid = streetid;
    }

    public Integer getMethodOrder() {
        return methodOrder;
    }

    public void setMethodOrder(Integer methodOrder) {
        this.methodOrder = methodOrder;
    }

    public List<Feedback> getFeedbacks() {
        return Feedback.find(Feedback.class, "RESTAURANT_ID = ?", this.restaurantid.toString());
    }

    /*
    * get Feedback for what fragment
    * */
    public Feedback getFirstFeedbacks() {
        return Select.from(Feedback.class).where(Condition.prop("RESTAURANT_ID").eq(this.getId().toString())).first();
    }

    /*
    * get Feedback for where fragment
    * */
    public List<Feedback> getTwoFeedbacks() {
        return Select.from(Feedback.class).where(Condition.prop("RESTAURANT_ID").eq(this.getId().toString())).limit("2").list();
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

}