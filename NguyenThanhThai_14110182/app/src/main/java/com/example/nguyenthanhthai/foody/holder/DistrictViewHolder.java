package com.example.nguyenthanhthai.foody.holder;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.nguyenthanhthai.foody.R;
import com.example.nguyenthanhthai.foody.custommodel.District_;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

/**
 * Created by NguyenThanhThai on 4/2/2017.
 */

public class DistrictViewHolder extends GroupViewHolder {

    private TextView cityName;
    private TextView countStrees;

    public DistrictViewHolder(View itemView) {
        super(itemView);

        cityName = (TextView) itemView.findViewById(R.id.txtDistricName);
        countStrees = (TextView) itemView.findViewById(R.id.tvStreetCount);
    }

    public void setGroupName(ExpandableGroup group) {
        cityName.setText(group.getTitle());
        countStrees.setText(((District_) group).getCountStreet() + " stress");
    }

    public void setSelected() {
        cityName.setTextColor(Color.RED);
    }

    public void setDisableSelected() {
        cityName.setTextColor(Color.parseColor("#ff333333"));
    }
}