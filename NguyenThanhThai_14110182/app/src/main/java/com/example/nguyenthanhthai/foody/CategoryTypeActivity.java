package com.example.nguyenthanhthai.foody;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.nguyenthanhthai.foody.adapter.CategoryTypeAdapter;
import com.example.nguyenthanhthai.foody.adapter.FoodRecyclerAdapter;
import com.example.nguyenthanhthai.foody.model.Category;
import com.example.nguyenthanhthai.foody.model.CategoryType;

import java.util.List;

public class CategoryTypeActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_type);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.TEXT_CATEGORY));

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.list);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        CategoryTypeAdapter mAdapter = new CategoryTypeAdapter(CategoryType.listAll(CategoryType.class));
        recyclerView.setAdapter(mAdapter);


        // Envent click CategoryType set SharedPreferences,
        // after Category get CategoryTypeId from sharePreferences when MainActivity call Event
        //      listenChangeCategoryWhere.changeCategoryType.categoryTypeIdResult(categoryTypeIdSelected);
        //      listenChangeCategoryWhat.changeCategoryType.categoryTypeIdResult(categoryTypeIdSelected);
        mAdapter.setOnCLickItemEventAdapter(new CategoryTypeAdapter.OnClickLisener() {
           @Override
           public void onClickItem(CategoryType categoryType) {
               Intent intent=getIntent();
               intent.putExtra("CategoryTypeIdSelect",categoryType.getId());
               setResult(33,intent);

               editor.putInt("CATEGORY_TYPE_ID_SELECTED", categoryType.getId().intValue());
               editor.putInt("CATEGORY_ID_SELECTED_INDEX", 0);
               editor.commit();

               finish();
           }
       });
    }
}
