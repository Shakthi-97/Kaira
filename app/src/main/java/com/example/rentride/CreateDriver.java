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

public class CreateDriver extends AppCompatActivity {

    EditText firstName, lastName, email, phone, licenseNum, country;
    Button driverBook;
    DatabaseReference driverDbRef;
    Driver driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_driver);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        licenseNum = findViewById(R.id.licenseNum);
        country = findViewById(R.id.country);

        driverBook = findViewById(R.id.driverBook);

        driver = new Driver();

        driverBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                driverDbRef = FirebaseDatabase.getInstance().getReference().child("Driver");
                try {
                    if(TextUtils.isEmpty(firstName.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please enter your FirstName", Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(email.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please enter your Email", Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(phone.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please enter your Phone number", Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(licenseNum.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please enter your License number", Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(country.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please enter your Country", Toast.LENGTH_SHORT).show();
                    }else{
                        driver.setFirstName(firstName.getText().toString().trim());
                        driver.setLastName(lastName.getText().toString().trim());
                        driver.setEmail(email.getText().toString().trim());
                        driver.setPhone(Integer.parseInt(phone.getText().toString().trim()));
                        driver.setLicenseNum(licenseNum.getText().toString().trim());
                        driver.setCountry(country.getText().toString().trim());

                        driverDbRef.push().setValue(driver);
                        driverDbRef.child("Driver1").setValue(driver);

                        Toast.makeText(getApplicationContext(), "Data saved Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();

                    }
                }catch (NumberFormatException e){
                    e.printStackTrace();
                }
            }
        });


    }

    private void clearControls(){
        firstName.setText("");
        lastName.setText("");
        email.setText("");
        phone.setText("");
        licenseNum.setText("");
        country.setText("");

    }

}