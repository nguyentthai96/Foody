package com.example.nguyenthanhthai.foody.dao;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.orm.SugarApp;
import com.orm.util.ManifestHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by NguyenThanhThai on 3/29/2017.
 */

public class AppDatabaseConfig extends SugarApp {

    String dbPath;
    String dbName;

    @Override
    public final void onCreate() {
        super.onCreate();
        dbName = ManifestHelper.getDatabaseName(getApplicationContext());
        dbPath = Environment.getDataDirectory() + "/data/" + ManifestHelper.getDomainPackageName(getApplicationContext()) + "/databases/";
        initDB();
    }


    private void initDB() {
        try {
            if (!doesDatabaseExist(this, dbPath + dbName))
            {
                Context context = getApplicationContext();
                SQLiteDatabase db = context.openOrCreateDatabase(dbName, context.MODE_PRIVATE, null);
                db.close();

                CopyDataBaseFromAsset();
            }
        } catch (Exception e) {
            e.toString();
        }
    }

    private boolean doesDatabaseExist(ContextWrapper context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }

    /*
    * Copy database in assets folder
    */
    protected void CopyDataBaseFromAsset() throws IOException {

        InputStream dbInput = null;
        try {
            //Open your local db as the input stream
            dbInput = getApplicationContext().getAssets().open(dbName);
        } catch (FileNotFoundException e) {
            Log.d("FileNotFound", "Cannot found database in assets " + dbName);
            dbInput.close();
            return;
        }

        // Path to the just created empty db
        String outFileName = dbPath + dbName;
        OutputStream dbOutput = null;
        try {
            //Open the empty db as the output stream
            dbOutput = new FileOutputStream(outFileName);
        } catch (Exception e) {
            e.toString();
        }

        Log.d("CreateNewDataBaseAsset", "Create database from assets " + dbName + "...");

        try {  //transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dbInput.read(buffer)) > 0) {
                dbOutput.write(buffer, 0, length);
            }
        } finally { //Close the streams
            dbOutput.flush();
            dbOutput.close();
            dbInput.close();
        }
    }
}