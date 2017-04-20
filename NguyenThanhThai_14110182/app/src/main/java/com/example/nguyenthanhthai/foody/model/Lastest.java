package com.example.nguyenthanhthai.foody.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenThanhThai on 4/4/2017.
 */

public class Lastest {
    Integer idIndexItem;
    String imageNameIcon;
    String imageNameIconSelect;
    String nameItemList;


    public Lastest(Integer idIndexItem, String imageNameIcon, String imageNameIconSelect, String nameItemList) {
        this.imageNameIcon = imageNameIcon;
        this.imageNameIconSelect = imageNameIconSelect;
        this.nameItemList = nameItemList;
    }

    public String getImageNameIcon() {
        return imageNameIcon;
    }

    public void setImageNameIcon(String imageNameIcon) {
        this.imageNameIcon = imageNameIcon;
    }

    public String getNameItemList() {
        return nameItemList;
    }

    public void setNameItemList(String nameItemList) {
        this.nameItemList = nameItemList;
    }

    public Integer getIdIndexItem() {
        return idIndexItem;
    }

    public void setIdIndexItem(Integer idIndexItem) {
        this.idIndexItem = idIndexItem;
    }

    public String getImageNameIconSelect() {
        return imageNameIconSelect;
    }

    public void setImageNameIconSelect(String imageNameIconSelect) {
        this.imageNameIconSelect = imageNameIconSelect;
    }

    public static  List<Lastest> getListLastestWhere(){
        List<Lastest> list=new ArrayList<>();
        list.add(new Lastest(1,"home_ic_filter_latest","home_ic_filter_latest_act","Lastest"));
        list.add(new Lastest(2,"home_ic_filter_most_near","home_ic_filter_most_near_act","Nearbly"));
        list.add(new Lastest(3,"home_ic_filter_top_of_week","home_ic_filter_top_of_week_act","Most Reviewed"));
        list.add(new Lastest(4,"home_ic_filter_tourist","home_ic_filter_tourist_act","For tourist"));
        list.add(new Lastest(5,"home_ic_filter_ecard","home_ic_filter_ecard_act","E-card"));
        list.add(new Lastest(6,"home_ic_filter_most_reservation","home_ic_filter_most_reservation_act","Reservation"));
        list.add(new Lastest(7,"home_ic_filter_bankcard","home_ic_filter_bankcard_act","Bank-card"));
        list.add(new Lastest(8,"home_ic_delivery","home_ic_delivery_act","Order Delivery"));
        return list;
    }

    public static List<Lastest> getListLastestWhat(){
        List<Lastest> list=new ArrayList<>();
        list.add(new Lastest(1,"home_ic_filter_latest","home_ic_filter_latest_act","Lastest"));
        list.add(new Lastest(2,"home_ic_filter_most_near","home_ic_filter_most_near_act","Nearbly"));
        list.add(new Lastest(3,"home_ic_filter_top_of_week","home_ic_filter_top_of_week_act","Top"));
        list.add(new Lastest(4,"home_ic_filter_tourist","home_ic_filter_tourist_act","For tourist"));
        return list;
    }
}
