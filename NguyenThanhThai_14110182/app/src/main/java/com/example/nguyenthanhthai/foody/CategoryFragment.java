package com.example.nguyenthanhthai.foody;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyenthanhthai.foody.adapter.CategoryAdapter;
import com.example.nguyenthanhthai.foody.listener.CategoryListener;
import com.example.nguyenthanhthai.foody.listener.CustomRVItemTouchListener;
import com.example.nguyenthanhthai.foody.listener.RecyclerViewItemClickListener;
import com.example.nguyenthanhthai.foody.model.Category;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

public class CategoryFragment extends Fragment {

    private List<Category> categories;
    private RecyclerView recyclerView;
    private CategoryAdapter mAdapter;
    public int categoryIdSelected = 1;
    CategoryListener categoryListener;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private int categoryTypeIdSelected;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        editor = sharedPreferences.edit();
        categoryTypeIdSelected = sharedPreferences.getInt("CATEGORY_TYPE_ID_SELECTED_INDEX", 1);
        categoryIdSelected = sharedPreferences.getInt("CATEGORY_ID_SELECTED_INDEX", 1);

        Log.d("Category", "CATEGORY_TYPE_ID_SELECTED_INDEX shareprent" + categoryIdSelected);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        categories = Select.from(Category.class).where(Condition.prop("CATEGORY_TYPE_ID").eq(categoryTypeIdSelected)).list();
        mAdapter = new CategoryAdapter(categories, categoryIdSelected);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        DividerItemDecoration dividerItemDecoration =
//                new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.HORIZONTAL);
//        dividerItemDecoration.setDrawable(
//                ContextCompat.getDrawable(this.getContext(), R.drawable.line_divider));
        recyclerView.setAdapter(mAdapter);

        //Set event click item on RecyclerView
        recyclerView.addOnItemTouchListener(new CustomRVItemTouchListener(this.getContext(),
                recyclerView, new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                mAdapter.setChangeSelected(view, position, categoryIdSelected);
                categoryIdSelected = position;

                if (position != 0) {
                    //return what or where fragment
                    categoryListener.onItemClick(categories.get(position - 1));
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return rootView;
    }

    /*
    * Set Event category click item get to where and what fragment
    * */
    public void setDataItemTabSelect(CategoryListener categoryListener) {
        this.categoryListener = categoryListener;
    }
}