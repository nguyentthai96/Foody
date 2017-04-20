package com.example.nguyenthanhthai.foody.holder;

import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.nguyenthanhthai.foody.R;
import com.example.nguyenthanhthai.foody.model.Street;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Created by NguyenThanhThai on 4/2/2017.
 */

public class StreetViewHolder extends ChildViewHolder {

    private TextView streetName;

    public StreetViewHolder(View itemView) {
        super(itemView);

        streetName = (TextView) itemView.findViewById(R.id.txtStreetName);
    }

    public void onBind(Street street, ExpandableGroup group) {
        streetName.setText(street.getName());
    }

    public void setSelected() {
        streetName.setTextColor(Color.RED);
    }

    public void setDisableSelected() {
        streetName.setTextColor(Color.parseColor("#ff333333"));
    }
}