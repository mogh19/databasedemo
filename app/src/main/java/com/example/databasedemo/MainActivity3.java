package com.example.databasedemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    private static final int ADD_CAR_REQUEST_CODE=1;
    private static final int edit_CAR_REQUEST_CODE=1;
    public static final String car_key="car_key";
    private static final int PERMISSION_REQ_CODEE=6;

    MyDataBase myDataBase;
    RecyclerView recyclerView;
    FloatingActionButton fab;
    Toolbar toolbar;
    RecyclerViewAdapter recyclerViewAdapter;
    DataBaseAccess dataBaseAccess;
    CardView cardView;
    MainActivity2 activity2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        activity2=new MainActivity2();


        recyclerView=findViewById(R.id.main_recyclerview);




        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQ_CODEE);

        }


        recyclerView=findViewById(R.id.main_recyclerview);
        fab=findViewById(R.id.main_fab);
        toolbar=findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);


        ArrayList<Car> cars=new ArrayList<>();
        dataBaseAccess=DataBaseAccess.getInstance(this);
        dataBaseAccess.open();
        cars = dataBaseAccess.getAllCars();
        dataBaseAccess.close();
        recyclerViewAdapter=new RecyclerViewAdapter(this, cars, new onRvItemSelected() {
            @Override
            public void onItemClicked(int carId) {
                Intent intent=new Intent(getBaseContext(),MainActivity2.class);
                intent.putExtra(car_key,carId);


                startActivityForResult(intent,edit_CAR_REQUEST_CODE);


            }
        });
        recyclerView.setAdapter(recyclerViewAdapter);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager( layoutManager);
        recyclerView.setHasFixedSize(true);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),MainActivity2.class);
                startActivityForResult(intent,ADD_CAR_REQUEST_CODE);
            }
        });






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        SearchView searchView=(SearchView)menu.findItem(R.id.main_search).getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dataBaseAccess.open();
                ArrayList<Car> cars = dataBaseAccess.getcar(query);
                dataBaseAccess.close();
                recyclerViewAdapter.setCars(cars);
                recyclerViewAdapter.notifyDataSetChanged();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                dataBaseAccess.open();
                ArrayList<Car> car =dataBaseAccess.getcar(newText);
                dataBaseAccess.close();
                recyclerViewAdapter.setCars(car);
                recyclerViewAdapter.notifyDataSetChanged();
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                dataBaseAccess.open();
                ArrayList<Car> cars=dataBaseAccess.getAllCars();
                dataBaseAccess.close();
                recyclerViewAdapter.setCars(cars);
                recyclerViewAdapter.notifyDataSetChanged();
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==ADD_CAR_REQUEST_CODE ){
            dataBaseAccess.open();
            ArrayList<Car> cars= dataBaseAccess.getAllCars();
            dataBaseAccess.close();
            recyclerViewAdapter.setCars(cars);
            recyclerViewAdapter.notifyDataSetChanged();


        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQ_CODEE:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){

                }else {

                }
        }
    }
}
