package com.example.rentride;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

public class crudcomplaint extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.card";


    EditText txtName,txtMobile,txtEmail,txtDate,txtAdd,txtComplaint;
    Button btnSave,btnShow,btnUpdate,btnDelete;
    DatabaseReference n_dbRef;
    Complaint ctd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crudcomplaint);

        txtName =findViewById(R.id.nName);
        txtMobile=findViewById(R.id.nMobile);
        txtEmail=findViewById(R.id.nEmail);
        txtDate=findViewById(R.id.nDate);
        txtAdd=findViewById(R.id.nAddress);
        txtComplaint=findViewById(R.id.nComplaint);

        btnSave=findViewById(R.id.nBtnSave);


        ctd= new Complaint();
    }
}