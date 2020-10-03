package com.example.rentride;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditDriver extends AppCompatActivity {
    EditText firstName, lastName, email, phone, licenseNum, country;
    DatabaseReference driverDbRef;
    Button driverUpdate,next;
    Driver driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_driver);

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        licenseNum = (EditText) findViewById(R.id.licenseNum);
        country = (EditText) findViewById(R.id.country);
        next = (Button) findViewById(R.id.next);

        driverDbRef = FirebaseDatabase.getInstance().getReference().child("Driver").child("Driver1");

        driverDbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    firstName.setText(snapshot.child("firstName").getValue().toString());
                    lastName.setText(snapshot.child("lastName").getValue().toString());
                    email.setText(snapshot.child("email").getValue().toString());
                    phone.setText(snapshot.child("phone").getValue().toString());
                    licenseNum.setText(snapshot.child("licenseNum").getValue().toString());
                    country.setText(snapshot.child("country").getValue().toString());
                }else {
                    Toast.makeText(getApplicationContext(), "No Source to display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        driverUpdate = findViewById(R.id.driverSave);

        driverUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                driverDbRef = FirebaseDatabase.getInstance().getReference();

                driverDbRef.child("Driver").child("Driver1").child("firstName").setValue(firstName.getText().toString().trim());
                driverDbRef.child("Driver").child("Driver1").child("lastName").setValue(lastName.getText().toString().trim());
                driverDbRef.child("Driver").child("Driver1").child("email").setValue(email.getText().toString().trim());
                driverDbRef.child("Driver").child("Driver1").child("phone").setValue(phone.getText().toString().trim());
                driverDbRef.child("Driver").child("Driver1").child("licenseNum").setValue(licenseNum.getText().toString().trim());
                driverDbRef.child("Driver").child("Driver1").child("country").setValue(country.getText().toString().trim());

                Toast.makeText(getApplicationContext(),"Data updated successfully",Toast.LENGTH_SHORT).show();

            }
        });

//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(EditDriver.this,)
//            }
//        });


    }
}