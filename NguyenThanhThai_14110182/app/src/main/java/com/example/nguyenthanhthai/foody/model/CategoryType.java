package com.example.nguyenthanhthai.foody.model;

import com.orm.SugarRecord;

/**
 * Created by NguyenThanhThai on 4/5/2017.
 */

public class CategoryType extends SugarRecord {
    String name;
    String imageBackgroud;
    String colorBackgroud;

    public CategoryType() {
    }

    public CategoryType(String name, String imageBackgroud, String colorBackgroud) {
        this.name = name;
        this.imageBackgroud = imageBackgroud;
        this.colorBackgroud = colorBackgroud;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageBackgroud() {
        return imageBackgroud;
    }

    public void setImageBackgroud(String imageBackgroud) {
        this.imageBackgroud = imageBackgroud;
    }

    public String getColorBackgroud() {
        return colorBackgroud;
    }

    public void setColorBackgroud(String colorBackgroud) {
        this.colorBackgroud = colorBackgroud;
    }
}
