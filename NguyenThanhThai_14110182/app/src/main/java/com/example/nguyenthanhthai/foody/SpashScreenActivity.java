package com.example.nguyenthanhthai.foody;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.nguyenthanhthai.foody.custommodel.District_;
import com.example.nguyenthanhthai.foody.model.Category;
import com.example.nguyenthanhthai.foody.model.District;
import com.example.nguyenthanhthai.foody.model.Restaurant;
import com.kennyc.bottomsheet.BottomSheet;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;

public class SpashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash_screen);
        getSupportActionBar().hide();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new PrefetchData().execute(this);
    }

    private class PrefetchData extends AsyncTask<Context, Void, Context> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Context doInBackground(Context... arg0) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return arg0[0];
        }

        @Override
        protected void onPostExecute(Context result) {
            super.onPostExecute(result);
            Intent intent = new Intent(result, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
