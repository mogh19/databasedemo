package com.example.databasedemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity2 extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    public static final int  ADD_CAR_RESULT= 2;
    public static final int EDIT_CAR_RESULT = 3;
    TextInputEditText editText_modle,editText_color,editText_dpl,editText_description;
    Toolbar toolbar;
    ImageView imageView,imageView2;
    int CarId=-1;
    MenuItem item,item1,item2;

    DataBaseAccess dataBaseAccess;

    Uri imageUri;
    private static final int PERMISSION_REQ_CODE=5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQ_CODE);

        }



        editText_modle=findViewById(R.id.et_details_model);
        editText_color=findViewById(R.id.et_details_color);
        editText_dpl=findViewById(R.id.et_details_dpl);
        editText_description=findViewById(R.id.et_details_description);
        toolbar=findViewById(R.id.toolbar_details);
        imageView=findViewById(R.id.image_details);
        imageView2=findViewById(R.id.car_image_view);
        setSupportActionBar(toolbar);
        Intent intent=getIntent();
        CarId =intent.getIntExtra(MainActivity3.car_key,-1);
        dataBaseAccess= DataBaseAccess.getInstance(this);

        if(CarId==-1){ //operation of add car deatails
            enablefields();
            clearfields();

        }else{
            //operation of edit car deatails
            disablefields();
            dataBaseAccess.open();
            Car car=dataBaseAccess.getCar(CarId);
            dataBaseAccess.close();
            if(car!=null){
                fillcartofields(car);
            }
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent1,PICK_IMAGE_REQUEST);

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu,menu);
        MenuItem save=menu.findItem(R.id.details_menu_save);
        MenuItem edit=menu.findItem(R.id.details_menu_edit);
        MenuItem delete=menu.findItem(R.id.details_menu_delete);
        if(CarId==-1){
            //operation of add car deatails
            save.setVisible(true);
            edit.setVisible(false);
            delete.setVisible(false);

        }else{
            //operation of edit car deatails
            save.setVisible(false);
            edit.setVisible(false);
            delete.setVisible(false);

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String model,color,description,image="";
        double dpl;
        switch (item.getItemId()){
            case R.id.details_menu_save:
                model=editText_modle.getText().toString();
                color=editText_color.getText().toString();
                description=editText_description.getText().toString();
                if(imageUri!=null)
                    image=imageUri.toString();
                String dpltest=editText_dpl.getText().toString();
                dpl=Double.parseDouble(dpltest);

                Car car=new Car (CarId,model,color,dpl,image,description);
                boolean res;
                dataBaseAccess.open();
                if(CarId==-1){
                    res=dataBaseAccess.insertcar(car);
                    if(res){
                        Toast.makeText(this, "car added", Toast.LENGTH_SHORT).show();
                        setResult(ADD_CAR_RESULT,null);
                        finish();
                    }else {
                        if (model.isEmpty()){
                            editText_modle.setError("Model is required");
                            editText_modle.requestFocus();

                        }
                        if (color.isEmpty()) {
                            editText_color.setError("Color is reqiured");
                            editText_color.requestFocus();

                        }
                        if (description.isEmpty()){
                            editText_description.setError("Description is required");
                            editText_description.requestFocus();

                        }
                        if (image.isEmpty()){
                            Toast.makeText(this, "please download a photo", Toast.LENGTH_SHORT).show();

                        }
                        if (dpltest.isEmpty()){
                            editText_dpl.setError("distance per liter is required");
                            editText_dpl.requestFocus();
                            
                        }
                    }
                }else {
                    res=dataBaseAccess.updatecar(car);
                    if(res){


                        Toast.makeText(this, "car edited", Toast.LENGTH_SHORT).show();
                        setResult(EDIT_CAR_RESULT,null);
                        finish();
                    }

                }
                dataBaseAccess.close();

                return true;
            case R.id.details_menu_edit:
                enablefields();
                MenuItem save=toolbar.getMenu().findItem(R.id.details_menu_save);
                MenuItem edit=toolbar.getMenu().findItem(R.id.details_menu_edit);
                MenuItem delete=toolbar.getMenu().findItem(R.id.details_menu_delete);
                delete.setVisible(false);
                save.setVisible(true);
                edit.setVisible(false);

                return true;
            case R.id.details_menu_delete:
                car=new Car (CarId,null,null,0.0,null,null);
                dataBaseAccess.open();
                    res=dataBaseAccess.deletecar(car);
                dataBaseAccess.close();
                if(res){
                    Toast.makeText(this, "car deleted", Toast.LENGTH_SHORT).show();
                    setResult(EDIT_CAR_RESULT,null);
                    finish();
                    }
                return true;
        }
        return false;
    }
    private void fillcartofields(Car car){

            if(car.getImage()!=null&&car.getImage()!=""){
                imageView.setImageURI(Uri.parse(car.getImage()));}
            editText_modle.setText(car.getModel());
            editText_color.setText(car.getColor());
            editText_dpl.setText(car.getDpl()+"");
            editText_description.setText(car.getDescription());


       }

        private void enablefields() {
            imageView.setEnabled(true);
            editText_modle.setEnabled(true);
            editText_color.setEnabled(true);
            editText_dpl.setEnabled(true);
            editText_description.setEnabled(true);


        }
        private void disablefields(){
            imageView.setEnabled(false);
            editText_modle.setEnabled(false);
            editText_color.setEnabled(false);
            editText_dpl.setEnabled(false);
            editText_description.setEnabled(false);


        }
        private void clearfields(){
            imageView.setImageURI(null);
            editText_modle.setText("");
            editText_color.setText("");
            editText_dpl.setText("");
            editText_description.setText("");


        }
    public void disable(){
        item.setVisible(false);
        item1.setVisible(false);
        item2.setVisible(false);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST&&resultCode==RESULT_OK){
            if(data!=null){
                imageUri=data.getData();
                imageView.setImageURI(imageUri);

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQ_CODE:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){

                }else {

                }
        }
    }
}