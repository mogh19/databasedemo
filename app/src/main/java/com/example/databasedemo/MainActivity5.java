package com.example.databasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity5 extends AppCompatActivity {

    TextView register,forgot_password,log_in;
    EditText editText_email,editText_password;
    ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        register=findViewById(R.id.register);
        mAuth = FirebaseAuth.getInstance();

        forgot_password=findViewById(R.id.forgot_pass);
        editText_email=findViewById(R.id.editText_email);
        editText_password=findViewById(R.id.editText_pass);
        log_in=findViewById(R.id.log_in);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),MainActivity4.class);
                startActivity(intent);
            }
        });
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserLogIn();
            }
        });
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),ResetPassword.class);
                startActivity(intent);
            }
        });



    }

    private void UserLogIn() {
        String email=editText_email.getText().toString().trim();
        String password=editText_password.getText().toString().trim();
        if (email.isEmpty()){
            editText_email.setError("Email is required");
            editText_email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editText_email.setError("Pleasw enter a valid email");
            editText_email.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editText_password.setError("password is required");
            editText_password.requestFocus();
            return;

        }
        if (password.length()<6){
             editText_password.setError("minimum password length must be 6 characters!");
             editText_password.requestFocus();
             return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                    if (user.isEmailVerified()){
                        Intent intent=new Intent(getBaseContext(),MainActivity2.class);
                        startActivity(intent);
                        finish();
                        
                    }else {
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity5.this, "check your email to varify", Toast.LENGTH_SHORT).show();

                    }

                }else {
                    Toast.makeText(MainActivity5.this, "failed to log in,try again", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

}