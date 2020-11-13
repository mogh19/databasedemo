package com.example.databasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static com.example.databasedemo.MyDataBase.*;

public class DataBaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DataBaseAccess instance;


    private DataBaseAccess(Context context){
        this.openHelper = new MyDataBase(context);

    }
    public static DataBaseAccess getInstance(Context context){
        if (instance == null) {
            instance = new DataBaseAccess(context);
        }
        return instance;

    }
    public void open(){
        this.database = openHelper.getWritableDatabase();

    }
    public void close(){
        if (database != null) {
            this.database.close();
        }


    }
    //insert new element
    public boolean insertcar(Car car) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(clmn_model, car.getModel());
        contentValues.put(clmn_color, car.getColor());
        contentValues.put(clmn_dpl, car.getDpl());
        contentValues.put(clmn_image, car.getImage());
        contentValues.put(clmn_description, car.getDescription());


        try {
            database.insert(tablename, null, contentValues);
            return true;
        }
        catch (SQLiteException e){
            if (e.getMessage().contains("no such table")){
                Log.d("onCreate", "Creating table " + tablename + "because it doesn't exist!" );
                // create table
                // re-run query, etc.
                return false;
            }
            return false;
            //  return result != -1;
        }}
    // update information of an element
    public boolean updatecar(Car car) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(clmn_model, car.getModel());
        contentValues.put(clmn_color, car.getColor());
        contentValues.put(clmn_dpl, car.getDpl());
        contentValues.put(clmn_image, car.getImage());
        contentValues.put(clmn_description, car.getDescription());

        String [] args={car.getId()+""};
        int result = database.update(tablename,contentValues,"id=?",args);
        if(result==0)
            return false;
        return true;
    }
    //delete
    public boolean deletecar(Car car) {

        String [] args={car.getId()+""};
        int result = database.delete(tablename,"id=?",args);
        if(result==0)
            return false;
        return true;
    }

    // return nums of elements in a table
    public long getcarscount(){

        return  DatabaseUtils.queryNumEntries(database, tablename);
    }
    //method of get elemnts
    public ArrayList<Car> getAllCars(){
        ArrayList<Car> cars=new ArrayList<>();

        Cursor cursor= database.rawQuery(" SELECT * FROM " + tablename,null);
        if(cursor!=null && cursor.moveToFirst()){
            do {
                int id=cursor.getInt(cursor.getColumnIndex(clmn_id));
                String model=cursor.getString(cursor.getColumnIndex(clmn_model));
                String color=cursor.getString(cursor.getColumnIndex(clmn_color));
                double distace_per_littre =cursor.getDouble(cursor.getColumnIndex(clmn_dpl));
                String image=cursor.getString(cursor.getColumnIndex(clmn_image));
                String description=cursor.getString(cursor.getColumnIndex(clmn_description));

                Car car=new Car(id,model,color,distace_per_littre,image,description);
                cars.add(car);


            }while (cursor.moveToNext());
            cursor.close();
        }
        return cars;
    }
    public ArrayList<Car> getcar(String modelsearch){
        ArrayList<Car> cars=new ArrayList<>();

        Cursor cursor=database.rawQuery("SELECT * FROM " + tablename+"  WHERE "+ clmn_model+" LIKE ?",new String[]{modelsearch}
        );
        if(cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(clmn_id));
                String model = cursor.getString(cursor.getColumnIndex(clmn_model));
                String color = cursor.getString(cursor.getColumnIndex(clmn_color));
                double dpl = cursor.getDouble(cursor.getColumnIndex(clmn_dpl));
                String image=cursor.getString(cursor.getColumnIndex(clmn_image));
                String description=cursor.getString(cursor.getColumnIndex(clmn_description));
                Car car=new Car(id,model,color,dpl,image,description);
                cars.add(car);
            } while (cursor.moveToNext());
            cursor.close();

        }

        return cars;

    }
    public Car getCar(int carid){
        Cursor cursor= database.rawQuery(" SELECT * FROM " + tablename+" where "+clmn_id+"=?",new String[]{String.valueOf(carid)});
        if(cursor!=null && cursor.moveToFirst()){

                int id=cursor.getInt(cursor.getColumnIndex(clmn_id));
                String model=cursor.getString(cursor.getColumnIndex(clmn_model));
                String color=cursor.getString(cursor.getColumnIndex(clmn_color));
                double distace_per_littre =cursor.getDouble(cursor.getColumnIndex(clmn_dpl));
                String image=cursor.getString(cursor.getColumnIndex(clmn_image));
                String description=cursor.getString(cursor.getColumnIndex(clmn_description));
                Car car=new Car(id,model,color,distace_per_littre,image,description);

            cursor.close();
            return  car;
        }
        return null;
    }


}
