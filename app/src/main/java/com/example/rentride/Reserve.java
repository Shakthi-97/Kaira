package com.example.rentride;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class Reserve extends AppCompatActivity {

    EditText txtpick,txtdrop,txtpickdate,txtpicktime,txtdropdate,txtdroptime;
    int mYear,mMonth,mDay, mHour,mMinute, mStartYear, mStartMonth, mStartDay,mEndYear, mEndMonth, mEndDay;
    Button btn,btnday;
    DatabaseReference dbRef;
    Reservation reserve1;
    TextView result;
    Date startDate,date2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        txtpick = findViewById(R.id.Inputpick);
        txtdrop = findViewById(R.id.Inputdrop);
        txtpickdate = findViewById(R.id.Inputpickdate);
        txtpicktime = findViewById(R.id.Inputpicktime);
        txtdropdate = findViewById(R.id.Inputdropdate);
        txtdroptime = findViewById(R.id.Inputdroptime);
        btnday = (Button) findViewById(R.id.btndays);

        btn = findViewById(R.id.btnok);
        reserve1 = new Reservation();

        txtpickdate = findViewById(R.id.Inputpickdate);
        final Calendar calendar=Calendar.getInstance();


        btnday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



            }
        });


        txtpickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar calendar = Calendar.getInstance();
                mYear= calendar.get(calendar.YEAR);
                mMonth= calendar.get(calendar.MONTH);
                mDay= calendar.get(calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog= new DatePickerDialog(Reserve.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        txtpickdate.setText(i+"/"+i1+"/"+i2);
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();

            }
        });

        txtpicktime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        Reserve.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //Initialize hour and minute
                                mHour = hourOfDay;
                                mMinute = minute;
                                // Initialize calender

                                Calendar calendar = Calendar.getInstance();
                                // set hour and minute
                                calendar.set(0,0,0,mHour,mMinute);
                                // set selected time on text view
                                txtpicktime.setText(DateFormat.format("hh:mm aa",calendar));

                            }
                        },12,0,false

                );
                // Display previous selected time
                timePickerDialog.updateTime(mHour,mMinute);
                //show dialog
                timePickerDialog.show();

            }
        });

        txtdropdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar calendar = Calendar.getInstance();
                mYear= calendar.get(calendar.YEAR);
                mMonth= calendar.get(calendar.MONTH);
                mDay= calendar.get(calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog= new DatePickerDialog(Reserve.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        txtdropdate.setText(i+"/"+i1+"/"+i2);
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();

            }
        });

        txtdroptime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        Reserve.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //Initialize hour and minute
                                mHour = hourOfDay;
                                mMinute = minute;
                                // Initialize calender

                                Calendar calendar = Calendar.getInstance();
                                // set hour and minute
                                calendar.set(0,0,0,mHour,mMinute);
                                // set selected time on text view
                                txtdroptime.setText(DateFormat.format("hh:mm aa",calendar));

                            }
                        },12,0,false

                );
                // Display previous selected time
                timePickerDialog.updateTime(mHour,mMinute);
                //show dialog
                timePickerDialog.show();

            }
        });



        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                dbRef = FirebaseDatabase.getInstance().getReference().child("Reservation");

                try {
                    if (TextUtils.isEmpty(txtpick.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter a Pickup Location", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtdrop.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter a Drop-off Location", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtpickdate.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter a Pickup date", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtpicktime.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter a Pickup time", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtdropdate.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter a Drop-off date", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtdroptime.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter a Drop-off time", Toast.LENGTH_SHORT).show();

                    else {

                        reserve1.setPickuplocation(txtpick.getText().toString().trim());
                        reserve1.setDropofflocation(txtdrop.getText().toString().trim());
                        reserve1.setPickupdate(txtpickdate.getText().toString().trim());
                        reserve1.setPickuptime(txtpicktime.getText().toString().trim());
                        reserve1.setReturndate(txtdropdate.getText().toString().trim());
                        reserve1.setReturntime(txtdroptime.getText().toString().trim());

                       // dbRef.push().setValue(reserve1);
                        dbRef.child("Res1").setValue(reserve1);
                        Toast.makeText(getApplicationContext(), "Data Svaed Successfully", Toast.LENGTH_SHORT).show();
                        clearControls();

                        Intent intent = new Intent(Reserve.this, Car.class);
                        startActivity(intent);
                    }




                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }


            }
        });


    }



    private void clearControls() {
        txtpick.setText("");
        txtdrop.setText("");
        txtpickdate.setText("");
        txtpicktime.setText("");
        txtdropdate.setText("");
        txtdroptime.setText("");


    }


}


