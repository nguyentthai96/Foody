package com.example.nguyenthanhthai.foody;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.nguyenthanhthai.foody.adapter.FoodRecyclerAdapter;
import com.example.nguyenthanhthai.foody.adapter.HeaderRecyclerViewAdapter;
import com.example.nguyenthanhthai.foody.adapter.ViewPagerAdapter;
import com.example.nguyenthanhthai.foody.listener.CategoryListener;
import com.example.nguyenthanhthai.foody.listener.DistrictStreetListener;
import com.example.nguyenthanhthai.foody.custommodel.HeaderItem;
import com.example.nguyenthanhthai.foody.customview.RecyclerViewHeader;
import com.example.nguyenthanhthai.foody.model.Category;
import com.example.nguyenthanhthai.foody.custommodel.District_;
import com.example.nguyenthanhthai.foody.model.CategoryType;
import com.example.nguyenthanhthai.foody.model.Lastest;
import com.example.nguyenthanhthai.foody.model.Restaurant;
import com.example.nguyenthanhthai.foody.model.Street;

import java.util.ArrayList;
import java.util.List;

public class WhatToDoFoodFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Restaurant>  restaurants;
    private RecyclerView recyclerView;
    private FoodRecyclerAdapter mAdapter;
    Button buttonCancel;
    int categoryTypeIdSelected;

    private CategoryFragment categoryFrag;
    private AddressFragment addresFrag;
    MainActivity.ListenChangeCategory changeCategoryType;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public WhatToDoFoodFragment(MainActivity.ListenChangeCategory changeCategoryType,
                                int categoryTypeIdSelected) {
        this.changeCategoryType = changeCategoryType;
        this.categoryTypeIdSelected = categoryTypeIdSelected;
        restaurants= new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        editor = sharedPreferences.edit();

        View rootView = inflater.inflate(R.layout.fragment_what_to_do, container, false);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        viewPager.setHorizontalScrollBarEnabled(false);
        setupViewPager();

        buttonCancel = (Button) rootView.findViewById(R.id.cancelAction);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setVisibility(View.GONE);
                buttonCancel.setVisibility(View.GONE);
            }
        });

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // called when tab selected
                viewPager.setVisibility(View.VISIBLE);
                buttonCancel.setVisibility(View.VISIBLE);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // called when tab unselected
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // called when a tab is reselected
                if (viewPager.getVisibility() == View.VISIBLE) {
                    viewPager.setVisibility(View.GONE);
                    buttonCancel.setVisibility(View.GONE);
                } else {
                    viewPager.setVisibility(View.VISIBLE);
                    buttonCancel.setVisibility(View.VISIBLE);
                }
            }
        });


        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        restaurants.clear();
        restaurants.addAll(0, Restaurant.find(Restaurant.class,
                "CATEGORYTYPEID=?", categoryTypeIdSelected+""));
        mAdapter = new FoodRecyclerAdapter(restaurants);
        recyclerView.setAdapter(mAdapter);


        RecyclerViewHeader recyclerViewHeader = (RecyclerViewHeader) rootView.findViewById(R.id.headRecyclerView);
        recyclerViewHeader.attachToRecyclerView(recyclerView);

        ViewFlipper viewFlipper = (ViewFlipper) rootView.findViewById(R.id.viewflipper);


        RecyclerView recyclerViewChild = (RecyclerView) rootView.findViewById(R.id.recyclerViewChild);
        recyclerViewChild.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        recyclerViewChild.setItemAnimator(new DefaultItemAnimator());
        HeaderRecyclerViewAdapter mAdapterHeader = new HeaderRecyclerViewAdapter(HeaderItem.setDataHeader());
        recyclerViewChild.setAdapter(mAdapterHeader);

        addEvents();

        return rootView;
    }

    /*
    * Add event for controls, update status for tab
    */
    private void addEvents() {
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setVisibility(View.GONE);
                buttonCancel.setVisibility(View.GONE);
            }
        });

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // called when tab selected
                viewPager.setVisibility(View.VISIBLE);
                buttonCancel.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // called when tab unselected
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // called when a tab is reselected
                if (viewPager.getVisibility() == View.VISIBLE) {
                    viewPager.setVisibility(View.GONE);
                    buttonCancel.setVisibility(View.GONE);
                } else {
                    viewPager.setVisibility(View.VISIBLE);
                    buttonCancel.setVisibility(View.VISIBLE);
                }
            }
        });

        changeCategoryType.setOnClickItem(new MainActivity.ListenChangeCategory.ChangeCategoryType() {
            @Override
            public void categoryTypeIdResult(CategoryType categoryType) {

            }

            @Override
            public void categoryTypeIdResult(Integer categoryTypeId) {
                restaurants.clear();
                restaurants.addAll(0, Restaurant.find(Restaurant.class,
                        "CATEGORYTYPEID=?", categoryTypeId.toString()));
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * Add name tabwidget
     * */
    private void setupTabIcons() {
        TextView tabOne = (TextView) LayoutInflater.from(this.getContext()).inflate(R.layout.custom_tab, null);
        tabOne.setText("Lastest");
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this.getContext()).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Category");
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this.getContext()).inflate(R.layout.custom_tab, null);
        tabThree.setText("HCM");
        tabLayout.getTabAt(2).setCustomView(tabThree);
    }

    /**
     * Add three tab Lastest, Category, Address
     * */
    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new LastestFragment(Lastest.getListLastestWhat(),
                sharedPreferences.getInt("LASTEST_SELECTED_INDEX", 0)), "Lastest");

        categoryFrag = new CategoryFragment();
        adapter.addFrag(categoryFrag, "Category");
        addresFrag = new AddressFragment();
        adapter.addFrag(addresFrag, "HCM");
        viewPager.setAdapter(adapter);

        categoryFrag.setDataItemTabSelect(new CategoryListener() {
            @Override
            public void onItemClick(Category category) {
                viewPager.setVisibility(View.GONE);
                buttonCancel.setVisibility(View.GONE);
                restaurants.clear();
                restaurants.addAll(Restaurant.find(Restaurant.class,
                        "CATEGORYID=?", category.getId().toString()));
                mAdapter.notifyDataSetChanged();
            }
        });

        addresFrag.getDataTab(new DistrictStreetListener() {
            @Override
            public void onItemChildClick(Street street) {
                viewPager.setVisibility(View.GONE);
                buttonCancel.setVisibility(View.GONE);
                restaurants.clear();
                restaurants.addAll(Restaurant.find(Restaurant.class,
                        "STREETID=? and DISTRICTID=?", street.getId().toString(), street.getDistrictid().toString()));
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onItemParentClick(District_ district) {
                viewPager.setVisibility(View.GONE);
                buttonCancel.setVisibility(View.GONE);
                restaurants.clear();
                restaurants.addAll(Restaurant.find(Restaurant.class,
                        "DISTRICTID=?", district.getId().toString()));
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}