package com.example.nguyenthanhthai.foody.listener;

import com.example.nguyenthanhthai.foody.custommodel.District_;
import com.example.nguyenthanhthai.foody.model.Street;

/**
 * Created by NguyenThanhThai on 4/5/2017.
 */

/*
* Custom event click expand recyclerView Item
* */
public interface DistrictStreetListener {
    public void onItemChildClick(Street street);
    public void onItemParentClick(District_ district);
}
