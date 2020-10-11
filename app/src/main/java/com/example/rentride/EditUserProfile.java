package com.example.rentride;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class EditUserProfile extends AppCompatActivity {

    EditText txtName, txtEmail, txtContact_No;
    DatabaseReference dbRef;
    Button but, regUpdate;
    Register Reg1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);

        txtName = findViewById(R.id.CustName);
        txtEmail = findViewById(R.id.CustEmail);
        txtContact_No = findViewById(R.id.CustNum);
        but = findViewById(R.id.save_btn);


        dbRef = FirebaseDatabase.getInstance().getReference().child("Register").child("Reg1");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    txtName.setText(snapshot.child("name").getValue().toString());
                    txtEmail.setText(snapshot.child("email").getValue().toString());
                    txtContact_No.setText(snapshot.child("contact_No").getValue().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        regUpdate = findViewById(R.id.save_btn);

        regUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbRef = FirebaseDatabase.getInstance().getReference();

                dbRef.child("Register").child("Reg1").child("name").setValue(txtName.getText().toString().trim());
                dbRef.child("Register").child("Reg1").child("email").setValue(txtEmail.getText().toString().trim());
                dbRef.child("Register").child("Reg1").child("contact_No").setValue(txtContact_No.getText().toString().trim());

                Toast.makeText(getApplicationContext(), "Details Updated Successfully",Toast.LENGTH_SHORT).show();

            }
        });
    }
}


