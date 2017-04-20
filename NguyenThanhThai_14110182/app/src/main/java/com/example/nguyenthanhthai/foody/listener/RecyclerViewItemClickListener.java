package com.example.nguyenthanhthai.foody.listener;

import android.view.View;

/**
 * Created by NguyenThanhThai on 3/27/2017.
 */

/*
* Interface use by CustomRVItemTouchListener
* */
public interface RecyclerViewItemClickListener {

    public void onClick(View view, int position);

    public void onLongClick(View view, int position);
}
