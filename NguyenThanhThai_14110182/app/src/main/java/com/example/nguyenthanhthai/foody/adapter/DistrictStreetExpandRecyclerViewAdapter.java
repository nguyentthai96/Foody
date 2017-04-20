package com.example.nguyenthanhthai.foody.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyenthanhthai.foody.R;
import com.example.nguyenthanhthai.foody.listener.DistrictStreetListener;
import com.example.nguyenthanhthai.foody.holder.DistrictViewHolder;
import com.example.nguyenthanhthai.foody.holder.StreetViewHolder;
import com.example.nguyenthanhthai.foody.custommodel.District_;
import com.example.nguyenthanhthai.foody.model.Street;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by NguyenThanhThai on 4/2/2017.
 */

public class DistrictStreetExpandRecyclerViewAdapter extends ExpandableRecyclerViewAdapter<DistrictViewHolder, StreetViewHolder> {

    private Activity activity;

    private RecyclerView.ViewHolder holderSelectedLast;

    public DistrictStreetExpandRecyclerViewAdapter(Activity activity, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.activity = activity;
    }

    @Override
    public DistrictViewHolder onCreateGroupViewHolder(final ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.new_district_item, parent, false);

        return new DistrictViewHolder(view);
    }

    @Override
    public StreetViewHolder onCreateChildViewHolder(ViewGroup parent, final int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.new_district_child_item, parent, false);

        return new StreetViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(final StreetViewHolder holder, int flatPosition, final ExpandableGroup group, int childIndex) {
        final Street street = ((District_) group).getItems().get(childIndex);
        holder.onBind(street, group);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holderSelectedLast != null) {
                    if (holderSelectedLast instanceof DistrictViewHolder) {
                        ((DistrictViewHolder) holderSelectedLast).setDisableSelected();
                    } else {
                        ((StreetViewHolder) holderSelectedLast).setDisableSelected();
                    }
                }
                holder.setSelected();
                holderSelectedLast = holder;
                mListener.onItemChildClick(street);
            }
        });
    }

    @Override
    public void onBindGroupViewHolder(final DistrictViewHolder holder, int flatPosition, final ExpandableGroup group) {
        holder.setGroupName(group);

        holder.itemView.findViewById(R.id.txtDistricName).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holderSelectedLast != null) {
                    if (holderSelectedLast instanceof DistrictViewHolder) {
                        ((DistrictViewHolder) holderSelectedLast).setDisableSelected();
                    } else {
                        ((StreetViewHolder) holderSelectedLast).setDisableSelected();
                    }
                }
                holder.setSelected();
                holderSelectedLast = holder;
                mListener.onItemParentClick(((District_) group));
            }
        });

    }

    private DistrictStreetListener mListener;

    public void setOnClickItemListener(DistrictStreetListener mListener) {
        this.mListener = mListener;
    }
}