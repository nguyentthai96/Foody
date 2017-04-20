package com.example.nguyenthanhthai.foody.customview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by NguyenThanhThai on 3/23/2017.
 */

/*
* custom rating math retaurant
* */
public class TextViewRating extends android.support.v7.widget.AppCompatTextView {

    TextView textView;

    public TextViewRating(Context context) {
        super(context);

    }

    public TextViewRating(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewRating(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onTextChanged(CharSequence text, int start, int count, int after) {
        try {
            if (Double.parseDouble(text.toString()) < 3) {
                this.setTextColor(Color.RED);
            } else {
                this.setTextColor(Color.GREEN);
            }
        }catch (Exception e){
            e.toString();
        }
    }

}
