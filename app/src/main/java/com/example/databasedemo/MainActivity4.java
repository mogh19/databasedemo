package com.example.databasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity4 extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextView banner;
    EditText editTextFullName,editTextAge,editTextEmail,editTextPassword;
    ProgressBar progressBar;
    Button registernow;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        mAuth = FirebaseAuth.getInstance();
        banner=findViewById(R.id.banner1);
        editTextFullName=findViewById(R.id.fullname);
        editTextAge=findViewById(R.id.age);
        editTextEmail=findViewById(R.id.email1);
        editTextPassword=findViewById(R.id.password1);
        registernow=findViewById(R.id.registenow);


        registernow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();

            }
        });







    }

    private void registerUser() {
        final String full_name=editTextFullName.getText().toString().trim();
        final String age=editTextAge.getText().toString().trim();
        final String email=editTextEmail.getText().toString().trim();
        String pass=editTextPassword.getText().toString().trim();
        if (full_name.isEmpty()){
            editTextFullName.setError("Full Name is Required");
            editTextFullName.requestFocus();
            return;
        }
        if (age.isEmpty()){
            editTextAge.setError("Age is Required");
            editTextAge.requestFocus();
            return;
        }
        if (email.isEmpty()){
            editTextEmail.setError("Email is Required");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("please provide a valid email");
            editTextEmail.requestFocus();
            return;
        }
        if (pass.isEmpty()){
            editTextPassword.setError("password is required");
            editTextPassword.requestFocus();
            return;
        }
        if (pass.length()<6){
            editTextPassword.setError("minimum password length must be 6 characters!");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User user=new User(full_name,age,email);
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){

                                Toast.makeText(MainActivity4.this, "user has been registered successfully", Toast.LENGTH_LONG).show();

                                Intent intent=new Intent(getBaseContext(),MainActivity2.class);
                                startActivity(intent);


                                finish();


                            }else {
                                Toast.makeText(MainActivity4.this, "failed to register,try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }else{
                    Toast.makeText(MainActivity4.this, "failed to register,try again", Toast.LENGTH_SHORT).show();

                }

            }
        });




    }

}