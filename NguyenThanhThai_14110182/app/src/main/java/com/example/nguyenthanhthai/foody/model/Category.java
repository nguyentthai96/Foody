package com.example.nguyenthanhthai.foody.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;
import com.orm.dsl.Table;

import java.io.Serializable;
import java.util.List;

/**
 * Created by NguyenThanhThai on 3/23/2017.
 */

public class Category extends SugarRecord{

    Long id=getId();
    String name;
    String img;
    Integer categoryTypeId;

    public Category() {
    }

    public Category(String name, String img, Integer categoryTypeId) {
        this.name = name;
        this.img = img;
        this.categoryTypeId = categoryTypeId;
    }

    protected Category(Parcel in) {
        name = in.readString();
        img = in.readString();
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
}
