package com.example.databasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class MyDataBase extends SQLiteAssetHelper {
    public static final String name_data_base = "cars.db";
    public static final int version_db = 1;
    public static final String tablename = "car";
    public static final String clmn_id = "id";
    public static final String clmn_model = "model";
    public static final String clmn_color = "color";
    public static final String clmn_description="description";
    public static final String clmn_image="image";
    public static final String clmn_dpl = "distance_per_liter";

    public MyDataBase(Context context) {
        super(context, name_data_base, null, version_db);
    }

//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE "+tablename+" ("+clmn_id+" INTEGER PRIMARY KEY AUTOINCREMENT," +
//                ""+clmn_model+" TEXT, "+clmn_color+" TEXT, "+clmn_dpl+" REAL )");
////        db.execSQL("CREATE TABLE 'car' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'model' TEXT, 'color' TEXT, 'color' REAL)");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS "+tablename );
//        onCreate(db);
//
//    }


}
