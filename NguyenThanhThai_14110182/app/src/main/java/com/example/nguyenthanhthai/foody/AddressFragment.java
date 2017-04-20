package com.example.nguyenthanhthai.foody;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nguyenthanhthai.foody.adapter.DistrictStreetExpandRecyclerViewAdapter;
import com.example.nguyenthanhthai.foody.listener.DistrictStreetListener;
import com.example.nguyenthanhthai.foody.model.District;
import com.example.nguyenthanhthai.foody.custommodel.District_;
import com.example.nguyenthanhthai.foody.model.Street;
import com.orm.query.Condition;
import com.orm.query.Select;
import com.thoughtbot.expandablerecyclerview.listeners.GroupExpandCollapseListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

public class AddressFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<District_> district_s;
    private DistrictStreetExpandRecyclerViewAdapter adapter;

    private ExpandableGroup expandedGroup;
    private Integer cityId = 1;
    DistrictStreetListener districtStreetListener;
    TextView txtCityName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_address, container, false);

        txtCityName = (TextView) view.findViewById(R.id.txtCityName);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        district_s=new ArrayList<>();
        setData();
        adapter = new DistrictStreetExpandRecyclerViewAdapter(this.getActivity(), district_s);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickItemListener(new DistrictStreetListener() {
            @Override
            public void onItemChildClick(Street street) {
                districtStreetListener.onItemChildClick(street);
                txtCityName.setTextColor(Color.parseColor("#ff333333"));
                //Toast.makeText(getContext(), street.getName() + street.getDistrict().getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemParentClick(District_ district) {
                districtStreetListener.onItemParentClick(district);
                txtCityName.setTextColor(Color.parseColor("#ff333333"));
                //Toast.makeText(getContext(), district.getNameDistrict(), Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnGroupExpandCollapseListener(new GroupExpandCollapseListener() {
            @Override
            public void onGroupExpanded(ExpandableGroup group) {
                if (expandedGroup != null) {
                    adapter.toggleGroup(expandedGroup);
                }
                expandedGroup = group;
            }

            @Override
            public void onGroupCollapsed(ExpandableGroup group) {

            }
        });

        return view;
    }

    public void getDataTab(DistrictStreetListener districtStreetListener) {
        this.districtStreetListener = districtStreetListener;
    }


    /*
    * Load data first time for recyclerView
    * */
    private void setData() {
        List<District> districtList = Select.from(District.class).where(Condition.prop("CITYID").eq(cityId)).limit("10").list();
        for (District dis : districtList) {
            district_s.add(new District_(dis.getId().intValue(), dis.getName(), dis.getStreets()));
        }
    }
}