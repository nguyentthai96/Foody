package com.example.nguyenthanhthai.foody;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nguyenthanhthai.foody.adapter.LastestRecyclerViewAdapter;
import com.example.nguyenthanhthai.foody.listener.CustomRVItemTouchListener;
import com.example.nguyenthanhthai.foody.listener.RecyclerViewItemClickListener;
import com.example.nguyenthanhthai.foody.model.Lastest;

import java.util.List;

public class LastestFragment extends Fragment {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private List<Lastest> lastestList;
    private RecyclerView recyclerView;
    private LastestRecyclerViewAdapter mAdapter;
    public int lastestSelectIndex;


    public LastestFragment(List<Lastest> lastestList, int lastestSelectIndex) {
        this.lastestList = lastestList;
        this.lastestSelectIndex = lastestSelectIndex;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        editor = sharedPreferences.edit();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_lastest, container, false);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        mAdapter = new LastestRecyclerViewAdapter(lastestList, lastestSelectIndex);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new CustomRVItemTouchListener(this.getContext(),
                recyclerView, new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                editor.putInt("LASTEST_SELECTED_INDEX", position);
                editor.commit();

                changeViewHolderSelector(view, position);
                lastestSelectIndex = position;
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        return rootView;
    }

    /*
    * Change Select Item, when click
    * */
    private void changeViewHolderSelector(View viewNew, int position) {
        try {
            View viewSelectOld = recyclerView.getChildAt(lastestSelectIndex);
            ((CheckBox) viewSelectOld.findViewById(R.id.chkLastestAddedToList)).setVisibility(View.GONE);
            ((TextView) viewSelectOld.findViewById(R.id.textItemListName))
                    .setTextColor(Color.parseColor("#ff555555"));
            ImageView imgIcon = (ImageView) viewSelectOld.findViewById(R.id.imgIcon);
            Context context = imgIcon.getContext();
            Glide.with(context)
                    .load(context.getResources().getIdentifier(
                            lastestList.get(lastestSelectIndex).getImageNameIcon(), "drawable", context.getPackageName()))
                    .into(imgIcon);
        } catch (Exception e) {
            Log.d("Exception", e.toString());
        }


        ((CheckBox) viewNew.findViewById(R.id.chkLastestAddedToList)).setVisibility(View.VISIBLE);
        ((TextView) viewNew.findViewById(R.id.textItemListName)).setTextColor(Color.parseColor("#ffcc0000"));
        ImageView imgIconNewSelect = (ImageView) viewNew.findViewById(R.id.imgIcon);

        Context context = imgIconNewSelect.getContext();
        Glide.with(context)
                .load(context.getResources().getIdentifier(
                        lastestList.get(position).getImageNameIconSelect(), "drawable", context.getPackageName()))
                .into(imgIconNewSelect);
    }

}