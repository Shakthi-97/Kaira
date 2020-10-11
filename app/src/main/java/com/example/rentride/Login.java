package com.example.rentride;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText email,password;
    Button logBtn, regbtn;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email=findViewById(R.id.logemail);
        password=findViewById(R.id.logpassword);
        fAuth=FirebaseAuth.getInstance();

        logBtn=findViewById(R.id.logbtn);
        regbtn = findViewById(R.id.regbtn);


        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String Email=email.getText().toString().trim();
                String  Password=password.getText().toString().trim();

                if(TextUtils.isEmpty(Email)){
                    email.setError("Please enter the email..");
                    return;
                }
                if(TextUtils.isEmpty(Password)){
                    password.setError("Please enter the password");
                    return;
                }

                fAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Login Unsuccessful", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

    }
}



