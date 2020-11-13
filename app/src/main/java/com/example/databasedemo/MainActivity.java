package com.example.databasedemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    private static final int ADD_CAR_REQUEST_CODE=1;
//    private static final int edit_CAR_REQUEST_CODE=1;
//    public static final String car_key="car_key";
//    private static final int PERMISSION_REQ_CODEE=6;
//
//    MyDataBase myDataBase;
//    RecyclerView recyclerView;
//    FloatingActionButton fab;
//    Toolbar toolbar;
//    RecyclerViewAdapter recyclerViewAdapter;
//    DataBaseAccess dataBaseAccess;
      CardView cardView1,cardView2,cardView3,cardView4,cardView5,cardView6;
      GridLayout gridLayout;
      Fshowrooms fshowrooms;
      Fmaintenance fmaintenance;
      Toolbar toolbar;
      NavigationView navigationview;
      DrawerLayout drawerLayout;
      FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar_drawer);
        cardView1=findViewById(R.id.new_cars);
        cardView2=findViewById(R.id.car_showrooms);
        cardView3=findViewById(R.id.sellcar);
        cardView4=findViewById(R.id.importedcars);
        cardView5=findViewById(R.id.usedcars);
        cardView6=findViewById(R.id.maintenance);
        gridLayout=findViewById(R.id.main_grid);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationview=findViewById(R.id.navigation);
        setSupportActionBar(toolbar);
        firebaseAuth=FirebaseAuth.getInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        fmaintenance=new Fmaintenance();


        fshowrooms=new Fshowrooms();
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),MainActivity3.class);
                startActivity(intent);

            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_page,fshowrooms).addToBackStack(null).commit();
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                if (firebaseUser!=null){
                    Intent intent=new Intent(getBaseContext(),MainActivity2.class);
                    startActivity(intent);
                }else{
                    Intent intent1=new Intent(getBaseContext(),MainActivity5.class);
                    startActivity(intent1);
                }


            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),MainActivity3.class);
                startActivity(intent);
            }
        });
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),MainActivity3.class);
                startActivity(intent);
            }
        });
        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_page,fmaintenance).addToBackStack(null).commit();

            }
        });
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.menu_home:
                            drawerLayout.closeDrawers();
                            return true;
                        case R.id.menu_newcars:
                            Intent intent=new Intent(getBaseContext(),MainActivity3.class);
                            startActivity(intent);
                            drawerLayout.closeDrawers();
                            return  true;
                        case R.id.menu_sellcar:
                            Intent intent1=new Intent(getBaseContext(),MainActivity5.class);
                            startActivity(intent1);
                            drawerLayout.closeDrawers();
                            return  true;
                        case R.id.menu_car_showrooms:
                            getSupportFragmentManager().beginTransaction().replace(R.id.main_page,fshowrooms).addToBackStack(null).commit();
                            drawerLayout.closeDrawers();
                            return true;
                        case R.id.menu_importedcars:
                            Intent intent2=new Intent(getBaseContext(),MainActivity3.class);
                            drawerLayout.closeDrawers();
                            startActivity(intent2);
                            return  true;
                        case R.id.menu_usedcars:
                            Intent intent3=new Intent(getBaseContext(),MainActivity3.class);
                            drawerLayout.closeDrawers();
                            startActivity(intent3);
                            return  true;
                        case R.id.menu_maitaince:
                            getSupportFragmentManager().beginTransaction().replace(R.id.main_page,fmaintenance).addToBackStack(null).commit();
                            drawerLayout.closeDrawers();
                            return true;
                        case R.id.menu_sign_out:
                            FirebaseAuth.getInstance().signOut();

                            Toast.makeText(MainActivity.this, "you have been sign out", Toast.LENGTH_SHORT).show();
                            drawerLayout.closeDrawers();
                            return true;
                        case R.id.menu_exit:
                            finish();
                            drawerLayout.closeDrawers();
                            return true;







                    }
                return false;
            }
        });




        //setSingleEvent(gridLayout);




//        recyclerView=findViewById(R.id.);
//
//
//
//
//        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQ_CODEE);
//
//        }
//
//
//        recyclerView=findViewById(R.id.main_recyclerview);
//        fab=findViewById(R.id.main_fab);
//        toolbar=findViewById(R.id.main_toolbar);
//        setSupportActionBar(toolbar);
//
//
//        ArrayList<Car> cars=new ArrayList<>();
//        dataBaseAccess=DataBaseAccess.getInstance(this);
//        dataBaseAccess.open();
//        cars = dataBaseAccess.getAllCars();
//        dataBaseAccess.close();
//        recyclerViewAdapter=new RecyclerViewAdapter(this, cars, new onRvItemSelected() {
//            @Override
//            public void onItemClicked(int carId) {
//                Intent intent=new Intent(getBaseContext(),MainActivity2.class);
//                intent.putExtra(car_key,carId);
//                startActivityForResult(intent,edit_CAR_REQUEST_CODE);
//
//
//            }
//        });
//        recyclerView.setAdapter(recyclerViewAdapter);
//        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,2);
//        recyclerView.setLayoutManager( layoutManager);
//        recyclerView.setHasFixedSize(true);
//
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getBaseContext(),MainActivity2.class);
//                startActivityForResult(intent,ADD_CAR_REQUEST_CODE);
//            }
//        });
//
//
//
//
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu,menu);
//        SearchView searchView=(SearchView)menu.findItem(R.id.main_search).getActionView();
//        searchView.setSubmitButtonEnabled(true);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                dataBaseAccess.open();
//                ArrayList<Car> cars = dataBaseAccess.getcar(query);
//                dataBaseAccess.close();
//                recyclerViewAdapter.setCars(cars);
//                recyclerViewAdapter.notifyDataSetChanged();
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                dataBaseAccess.open();
//                ArrayList<Car> car =dataBaseAccess.getcar(newText);
//                dataBaseAccess.close();
//                recyclerViewAdapter.setCars(car);
//                recyclerViewAdapter.notifyDataSetChanged();
//                return false;
//            }
//        });
//
//        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
//            @Override
//            public boolean onClose() {
//                dataBaseAccess.open();
//                ArrayList<Car> cars=dataBaseAccess.getAllCars();
//                dataBaseAccess.close();
//                recyclerViewAdapter.setCars(cars);
//                recyclerViewAdapter.notifyDataSetChanged();
//                return false;
//            }
//        });
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==ADD_CAR_REQUEST_CODE ){
//            dataBaseAccess.open();
//            ArrayList<Car> cars= dataBaseAccess.getAllCars();
//            dataBaseAccess.close();
//            recyclerViewAdapter.setCars(cars);
//            recyclerViewAdapter.notifyDataSetChanged();
//
//
//        }
//    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode){
//            case PERMISSION_REQ_CODEE:
//                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
//
//                }else {
//
//                }
//        }
  } @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }



//    public void  setSingleEvent(GridLayout gridlayout){
//        for(int i=0;i<gridLayout.getChildCount();i++){
//            cardView=(CardView) gridLayout.getChildAt(i);
//            cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent=new Intent(getBaseContext(),MainActivity3.class);
//                    startActivity(intent);
//
//                }
//            });
//        }
//    }
}








//        ArrayList<Car> cars=new ArrayList<>();
//        DataBaseAccess databaseAccess = DataBaseAccess.getInstance(this);
//        databaseAccess.open();
//        cars = databaseAccess.getAllCars();
//        databaseAccess.close();



//       myDataBase=new MyDataBase( this);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String model= editText.getText().toString();
//                String color= editText1.getText().toString();
//                double dpl= Double.parseDouble(editText2.getText().toString());
//                Car car=new Car(model,color,dpl);
//                boolean res=myDataBase.insertcar(car);
//                if(res){
//                    Toast.makeText(MainActivity.this, "success insertion", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(MainActivity.this, "error insertion", Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        });
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            ArrayList<Car>  cars=myDataBase.getcar("2018");
//
//                Toast.makeText(MainActivity.this,  "cars count:"+cars.size(), Toast.LENGTH_SHORT).show();
//                for (Car car :cars){
//                    Log.d("car   "+car.getId(),car.getModel());
//                }
//
//            }
//        });
//        RecyclerViewAdapter adapter=new RecyclerViewAdapter(this,cars);
//        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,2);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();