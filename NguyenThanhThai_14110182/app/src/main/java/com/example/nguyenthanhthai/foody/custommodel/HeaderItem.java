package com.example.nguyenthanhthai.foody.custommodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenThanhThai on 4/7/2017.
 */

public class HeaderItem {
    String image;
    String name;

    public HeaderItem(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static List<HeaderItem> setDataHeader() {
        List<HeaderItem> headerItemList = new ArrayList<>();
        headerItemList.add(new HeaderItem("ic_nearby", "Nearby"));
        headerItemList.add(new HeaderItem("ic_ecoupon", "Coupon"));
        headerItemList.add(new HeaderItem("ic_reservation", "Reservation"));
        headerItemList.add(new HeaderItem("ic_more_deli", "Order Delivery"));
        headerItemList.add(new HeaderItem("ic_ecard", "E-Card"));
        headerItemList.add(new HeaderItem("ic_game", "Game & Fun"));
        headerItemList.add(new HeaderItem("ic_icon_binhluanmoi", "Reviews"));
        headerItemList.add(new HeaderItem("ic_icon_chuyende", "Blogs"));
        headerItemList.add(new HeaderItem("ic_icon_topthanhvien", "Top members"));
        headerItemList.add(new HeaderItem("ic_video", "Video"));
        return headerItemList;
    }
}
