package com.example.nguyenthanhthai.foody.model;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by NguyenThanhThai on 4/5/2017.
 */

public class District extends SugarRecord {

    String name;
    Integer count;
    Integer cityid;
    City city;

    public District() {
    }

    public District(String name, City city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getCityid() {
        return cityid;
    }

    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    /*
    * get list Streets for districtId
    * */
    public List<Street> getStreets() {
        return Street.find(Street.class,"DISTRICTID = ?",this.getId().toString());
    }
}
