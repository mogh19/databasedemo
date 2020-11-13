package com.example.databasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {
    EditText editText_email;
    Button reset;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        editText_email=findViewById(R.id.email2);
        reset=findViewById(R.id.reset);

        mAuth = FirebaseAuth.getInstance();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }
    private void resetPassword(){
        String email=editText_email.getText().toString().trim();
        if (email.isEmpty()){
            editText_email.setError("Email is required");
            editText_email.requestFocus();
            return;
        }if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editText_email.setError("Enter a valid email");
            editText_email.requestFocus();
            return;
        }

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ResetPassword.this, "check your email to reset password", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ResetPassword.this, "somthing went wrong,try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}