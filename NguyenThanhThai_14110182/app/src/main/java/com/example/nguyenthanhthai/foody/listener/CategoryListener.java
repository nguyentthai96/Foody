package com.example.nguyenthanhthai.foody.listener;

import com.example.nguyenthanhthai.foody.model.Category;

/**
 * Created by NguyenThanhThai on 4/6/2017.
 */

/*
* Category event get where or what fragment,
* when click item Category recyclerView
* */
public interface CategoryListener {
    public void onItemClick(Category category);
}
