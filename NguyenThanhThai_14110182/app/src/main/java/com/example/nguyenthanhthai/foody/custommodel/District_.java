package com.example.nguyenthanhthai.foody.custommodel;

import android.os.Parcel;

import com.example.nguyenthanhthai.foody.model.City;
import com.example.nguyenthanhthai.foody.model.Street;
import com.orm.SugarRecord;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by NguyenThanhThai on 4/2/2017.
 */

public class District_ extends ExpandableGroup<Street> {

    Integer id;
    String nameDistrict;
    Integer countStreet;
    City city;
    List<Street> streets;


    public District_(Integer id,String title, List<Street> items) {
        super(title, items);
        this.nameDistrict=title;
        streets=items;
        countStreet=items.size();
        this.id=id;
    }

    protected District_(Parcel in) {
        super(in);
    }

    public String getNameDistrict() {
        return nameDistrict;
    }

    public void setNameDistrict(String nameDistrict) {
        this.nameDistrict = nameDistrict;
    }

    public Integer getCountStreet() {
        return countStreet;
    }

    public void setCountStreet(Integer countStreet) {
        this.countStreet = countStreet;
    }

    public City getCity() {
        return city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Street> getStreets() {
        return streets;
    }

    public void setStreets(List<Street> streets) {
        this.streets = streets;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
