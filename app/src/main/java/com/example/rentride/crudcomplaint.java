package com.example.rentride;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n_dbRef = FirebaseDatabase.getInstance().getReference().child("Complaint");

                try {
                    if (TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter a Name ", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtEmail.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter an Email", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtDate.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter a Date ", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtAdd.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter an Address ", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtComplaint.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter a Complaint ", Toast.LENGTH_SHORT).show();

                    else {
                        ctd.setName(txtName.getText().toString().trim());
                        ctd.setMobile(txtMobile.getText().toString().trim());
                        ctd.setEmail(txtEmail.getText().toString().trim());
                        ctd.setDate(txtDate.getText().toString().trim());
                        ctd.setAddress(txtAdd.getText().toString().trim());
                        ctd.setComplaint(txtComplaint.getText().toString().trim());

                        //insert into the database!!!
                        // n_dbRef.push().setValue(ctd);
                        n_dbRef.child("Ctd1").setValue(ctd);

                        Toast.makeText(getApplicationContext(), "Complaints Saved Successfully", Toast.LENGTH_SHORT).show();
                        n_clearControls();

                    }
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void n_clearControls(){
        txtName.setText("");
        txtMobile.setText("");
        txtEmail.setText("");
        txtDate.setText("");
        txtAdd.setText("");
        txtComplaint.setText("");

    }

}