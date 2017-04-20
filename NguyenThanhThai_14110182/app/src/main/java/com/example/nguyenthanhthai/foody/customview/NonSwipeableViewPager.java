package com.example.nguyenthanhthai.foody.customview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by NguyenThanhThai on 3/23/2017.
 */

/*
* none Swipeabler for three tab Lastest, Category, Address
* */
public class NonSwipeableViewPager  extends ViewPager{
    public NonSwipeableViewPager(Context context) {
        super(context);
    }

    public NonSwipeableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean canScrollHorizontally(int direction) {
        return false;
    }
}
