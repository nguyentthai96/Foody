package com.example.nguyenthanhthai.foody;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nguyenthanhthai.foody.model.CategoryType;
import com.kennyc.bottomsheet.BottomSheet;
import com.kennyc.bottomsheet.BottomSheetListener;


public class MainActivity extends AppCompatActivity implements BottomSheetListener {
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    TextView tvWhatToDo, tvWhereToGo;
    View viewActionBar;
    ImageView categoryTypeImage;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    ListenChangeCategory listenChangeCategoryWhere, listenChangeCategoryWhat;

    private int categoryTypeIdSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //iniDataTable();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        categoryTypeIdSelected = sharedPreferences.getInt("CATEGORY_TYPE_ID_SELECTED", 1);

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        viewActionBar = layoutInflater.inflate(R.layout.home_city_bar_style_black, null);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(viewActionBar,
                new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        listenChangeCategoryWhere = new MainActivity.ListenChangeCategory();
        listenChangeCategoryWhat = new MainActivity.ListenChangeCategory();

        categoryTypeImage = (ImageView) viewActionBar.findViewById(R.id.icDomain);
        Context context=categoryTypeImage.getContext();
        Glide.with(context)
                .load(context.getResources().getIdentifier(CategoryType.findById(CategoryType.class, (Long.parseLong("" + categoryTypeIdSelected))).getImageBackgroud(), "drawable",  context.getPackageName()))
                .into(categoryTypeImage);

        tvWhatToDo = (TextView) findViewById(R.id.tvWhatToDo);
        tvWhereToGo = (TextView) findViewById(R.id.tvWhereToGo);

        mSectionsPagerAdapter = new MainActivity.SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        addEvents();
    }

    private void addEvents() {
        //Change color button foood and places
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        changeSwipe(tvWhereToGo, tvWhatToDo, true);
                        break;
                    case 1:
                        changeSwipe(tvWhatToDo, tvWhereToGo, false);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //Click Places show WhereToGoPlacesFragment
        tvWhereToGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);
            }
        });

        //Click Food show WhatToDoFoodFragment
        tvWhatToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(1);
            }
        });

        //Menu action botton
        viewActionBar.findViewById(R.id.plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BottomSheet.Builder(viewActionBar.getContext())
                        .setSheet(R.menu.bottom_sheet)
                        .show();
            }
        });


        //Foody icon action
        categoryTypeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(viewActionBar.getContext(), CategoryTypeActivity.class);
                startActivityForResult(intent, 99);
            }
        });
    }

    /**
    * Chage status view tab button Foood and  Places
    */
    private void changeSwipe(TextView tvTurnOn, TextView tvTurnOff, boolean onLeft) {
        tvTurnOn.setBackgroundResource(android.R.color.transparent);
        tvTurnOn.setTextColor(Color.parseColor("#111111"));
        if (onLeft) {
            tvTurnOff.setBackgroundResource(R.drawable.dialog_round_corner_left_boder_bg_2);
        } else {
            tvTurnOff.setBackgroundResource(R.drawable.dialog_round_corner_right_boder_bg_2);
        }
        tvTurnOff.setTextColor(Color.parseColor("#ffffffff"));
    }


    @Override
    public void onSheetShown(@NonNull BottomSheet bottomSheet) {

    }

    //Click on bottom Dialog for Plus icon
    @Override
    public void onSheetItemSelected(@NonNull BottomSheet bottomSheet, MenuItem menuItem) {
        Toast.makeText(getApplicationContext(), menuItem.getTitle() + " Clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSheetDismissed(@NonNull BottomSheet bottomSheet, @DismissEvent int i) {

    }


    //Add Fragment Where and What
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new WhereToGoPlacesFragment(listenChangeCategoryWhere, categoryTypeIdSelected);
                case 1:
                    return new WhatToDoFoodFragment(listenChangeCategoryWhat, categoryTypeIdSelected);
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //check if the request code is same as what is passed  here it is 2
        if (requestCode == 99 && resultCode == 33) {
            categoryTypeIdSelected = ((Long) data.getExtras().get("CategoryTypeIdSelect")).intValue();
            listenChangeCategoryWhere.changeCategoryType.categoryTypeIdResult(categoryTypeIdSelected);
            listenChangeCategoryWhat.changeCategoryType.categoryTypeIdResult(categoryTypeIdSelected);
            Context context=categoryTypeImage.getContext();
            Glide.with(context)
                    .load(context.getResources().getIdentifier(CategoryType.findById(CategoryType.class, (Long.parseLong("" + categoryTypeIdSelected))).getImageBackgroud(), "drawable",  context.getPackageName()))
                    .into(categoryTypeImage);
            editor.putInt("CATEGORY_TYPE_ID_SELECTED_INDEX", categoryTypeIdSelected);
        }
    }

    /**
    * Listener event tap change in CategoryTypeActivity, get categoryType and Override select data Category Tab
    */
    public static class ListenChangeCategory {

        ChangeCategoryType changeCategoryType;

        public void setOnClickItem(ChangeCategoryType changeCategoryType) {
            this.changeCategoryType = changeCategoryType;
        }

        //Delegate Listener click change categoryTypeItem
        public interface ChangeCategoryType {
            public void categoryTypeIdResult(CategoryType categoryType);

            public void categoryTypeIdResult(Integer categoryTypeId);
        }
    }


    //    private void iniDataTable() {
//        List<Restaurant> restaurants = Restaurant.listAll(Restaurant.class);
//        for (Restaurant restaurant : restaurants) {
//            Random random = new Random();
//
//            int ram = (random.nextInt(1001)) % 4;
//            switch (ram) {
//                case 0:
//                    restaurant.setMethodOrder(1);
//                    break;
//                case 1:
//                    restaurant.setMethodOrder(2);
//                    if (((random.nextInt(1001)) % 4) % 2 == 1) {
//                        switch ((random.nextInt(1001)) % 4) {
//                            case 2:
//                                restaurant.setMethodOrder(restaurant.getMethodOrder() * 3);
//                                break;
//                            case 3:
//                                restaurant.setMethodOrder(restaurant.getMethodOrder() * 5);
//                                break;
//                            case 4:
//                                restaurant.setMethodOrder(restaurant.getMethodOrder() * 7);
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                    break;
//                case 2:
//                    restaurant.setMethodOrder(3);
//                    if (((random.nextInt(1001)) % 4) % 2 == 1) {
//                        switch ((random.nextInt(1001)) % 4) {
//                            case 1:
//                                restaurant.setMethodOrder(restaurant.getMethodOrder() * 2);
//                                break;
//                            case 3:
//                                restaurant.setMethodOrder(restaurant.getMethodOrder() * 5);
//                                break;
//                            case 4:
//                                restaurant.setMethodOrder(restaurant.getMethodOrder() * 7);
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                    break;
//                case 3:
//                    restaurant.setMethodOrder(5);
//                    if (((random.nextInt(1001)) % 4) % 2 == 1) {
//                        switch ((random.nextInt(1001)) % 4) {
//                            case 1:
//                                restaurant.setMethodOrder(restaurant.getMethodOrder() * 2);
//                                break;
//                            case 3:
//                                restaurant.setMethodOrder(restaurant.getMethodOrder() * 3);
//                                break;
//                            case 4:
//                                restaurant.setMethodOrder(restaurant.getMethodOrder() * 7);
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                    break;
//                case 4:
//                    restaurant.setMethodOrder(7);
//                    if (((random.nextInt(1001)) % 4) % 2 == 1) {
//                        switch ((random.nextInt(1001)) % 4) {
//                            case 1:
//                                restaurant.setMethodOrder(restaurant.getMethodOrder() * 2);
//                                break;
//                            case 3:
//                                restaurant.setMethodOrder(restaurant.getMethodOrder() * 3);
//                                break;
//                            case 4:
//                                restaurant.setMethodOrder(restaurant.getMethodOrder() * 5);
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                    break;
//                default:
//                    restaurant.setMethodOrder(restaurant.getMethodOrder() * 1);
//            }
//
//            Integer disid = restaurant.getDistrictid();
//            String street = restaurant.getAddress();
//            int length = street.indexOf(", " + District.findById(District.class, disid).getName());
//
//            String streSub = "";
//            try {
//                streSub = street.substring(0, length);
//                if (streSub.lastIndexOf(", P") > 0 && streSub.lastIndexOf(", Phạm Văn") < 0) {
//                    streSub = street.substring(0, streSub.lastIndexOf(", P"));
//                }
//
//                int[] a = {streSub.lastIndexOf("0"), streSub.lastIndexOf("1"), streSub.lastIndexOf("2"), streSub.lastIndexOf("3")
//                        , streSub.lastIndexOf("4"), streSub.lastIndexOf("5"), streSub.lastIndexOf("6")
//                        , streSub.lastIndexOf("7"), streSub.lastIndexOf("8"), streSub.lastIndexOf("9")};
//
//                Arrays.sort(a);
//                int start = a[a.length - 1];
//                if (start == -1) start = 0;
//                int end = streSub.lastIndexOf(", ");
//
//                if (end < start || start + 3 > end) end = -1;
//
//                if (end < 0) {
//                    streSub = streSub.substring(start, streSub.length());
//                    streSub = streSub.substring(streSub.indexOf(' '), streSub.length());
//                } else {
//                    streSub = streSub.substring(start, end);
//                    streSub = streSub.substring(streSub.indexOf(' '), streSub.length());
//                }
//
//            } catch (Exception e) {
//                try {
//                    if (street.indexOf("Đường Số ") > -1) {
//                        streSub = street.substring(street.indexOf("Đường Số "), street.length());
//                        if (streSub.indexOf(", ") > -1) {
//                            streSub = streSub.substring(0, streSub.indexOf(", "));
//                        }
//                    } else if (street.indexOf("Đường ") > -1) {
//                        streSub = street.substring(street.indexOf("Đường "), street.length());
//                        if (streSub.indexOf(", ") > -1) {
//                            streSub = street.substring(0, streSub.indexOf(", "));
//                        }
//                    } else if (street.indexOf("Quốc Lộ ") > -1) {
//                        streSub = street.substring(street.indexOf("Quốc Lộ "), street.length());
//                        if (streSub.indexOf(", ") > -1) {
//                            streSub = street.substring(0, streSub.indexOf(", "));
//                        }
//                    } else if (street.indexOf(", KP. ") > -1) {
//                        streSub = street.substring(0, street.indexOf(", KP. "));
//                        if (streSub.indexOf(", ") > -1) {
//                            streSub = street.substring(streSub.indexOf(", "), streSub.length());
//                        }
//                    } else if (street.indexOf("Cách Mạng Tháng 8") > -1) {
//                        streSub = "Cách Mạng Tháng 8";
//                    } else {
//                        e.toString();
//                        if (streSub.length() < 2) streSub = street.substring(0, length);
//                    }
//                } catch (Exception a) {
//                    if (length>0)
//                        streSub = street.substring(0, length);
//                }
//            }
//            Street streNew;
//            streNew = new Street(streSub, disid);
//            if (Street.find(Street.class, "NAME=? and DISTRICTID =?", streSub, disid.toString()).size() < 1) {
//                streNew.save();
//                restaurant.setStreetid(streNew.getId().intValue());
//            } else {
//                restaurant.setStreetid(Street.find(Street.class, "NAME=? and DISTRICTID =?", streSub, disid.toString()).get(0).getId().intValue());
//            }
//            restaurant.save();
//        }
//    }
}
