package com.example.nguyenthanhthai.foody.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

/**
 * Created by NguyenThanhThai on 4/2/2017.
 */

public class Street extends SugarRecord implements Parcelable {
    private String name;
    private Integer districtid;

    public Street() {
    }

    public Street(String name, Integer districtid) {
        this.name = name;
        this.districtid = districtid;
    }

    protected Street(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Street> CREATOR = new Creator<Street>() {
        @Override
        public Street createFromParcel(Parcel in) {
            return new Street(in);
        }

        @Override
        public Street[] newArray(int size) {
            return new Street[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDistrictid() {
        return districtid;
    }

    public void setDistrictid(Integer districtid) {
        this.districtid = districtid;
    }

    public District getDistrict() {
        return District.findById(District.class,districtid);
    }

    public static Creator<Street> getCREATOR() {
        return CREATOR;
    }
}
