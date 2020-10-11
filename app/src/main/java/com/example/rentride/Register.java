package com.example.rentride;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    //Initialize variable
    EditText txtName,txtEmail,txtContact_No,txtPassword;
    Button btn;
    DatabaseReference dbRef;
    Registration reg;

    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //assign variable
        txtName = findViewById(R.id.Cus_Name);
        txtEmail = findViewById(R.id.Cus_email);
        txtContact_No = findViewById(R.id.Cus_Contact_No);
        txtPassword = findViewById(R.id.Cus_Password);
        btn = findViewById(R.id.reg_button);

        //Initialize validation style
        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);

        //Add validation for name
        awesomeValidation.addValidation( this,R.id.Cus_Name, RegexTemplate.NOT_EMPTY,R.string.invalid_name);

        //For email address
        awesomeValidation.addValidation( this,R.id.Cus_email, Patterns.EMAIL_ADDRESS, R.string.invalid_email);

        //For contact number
        awesomeValidation.addValidation( this,R.id.Cus_Contact_No, "[0-9]{1}[0-9]{9}$",R.string.invalid_mobile);

        //For password
        awesomeValidation.addValidation( this,R.id.Cus_Password, ".{6,}",R.string.invalid_password);



        reg = new Registration();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Register");

                try {
                    if (TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter your Name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtEmail.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter your Email", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtContact_No.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter your Contact No", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtPassword.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter the Password", Toast.LENGTH_SHORT).show();
                    else {

                        reg.setName(txtName.getText().toString().trim());
                        reg.setEmail(txtEmail.getText().toString().trim());
                        reg.setContact_No(Integer.parseInt(txtContact_No.getText().toString().trim()));
                        reg.setPassword(txtPassword.getText().toString().trim());

                        //dbRef.push().setValue(reg);
                        dbRef.child("Reg1").setValue(reg);
                        if (awesomeValidation.validate()){

                            //On success
                            Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Registration Failed.",Toast.LENGTH_SHORT).show();
                        }

                        clearControls();
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }


        });
    }
    private void clearControls(){
        txtName.setText("");
        txtEmail.setText("");
        txtContact_No.setText("");
        txtPassword.setText("");

    }
}


