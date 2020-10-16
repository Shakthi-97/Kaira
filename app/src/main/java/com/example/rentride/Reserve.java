package com.example.rentride;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Reserve extends AppCompatActivity {

    EditText txtpick,txtdrop,txtpicktime,txtdroptime;
    int mYear,mMonth,mDay, mHour,mMinute, mStartYear, mStartMonth, mStartDay,mEndYear, mEndMonth, mEndDay;
    Button txtpickdate,txtdropdate,btn,calculate;
    DatabaseReference dbRef;
    Reservation reserve1;
    TextView result;
    DatePickerDialog.OnDateSetListener dateSetListener1,dateSetListener2;

    public static final String EXTRA_NUMBER2 = "com.example.rentride.EXTRA_NUMBER1";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        txtpick = findViewById(R.id.Inputpick);
        txtdrop = findViewById(R.id.Inputdrop);
        txtpickdate = findViewById(R.id.txtpickdate);
        txtpicktime = findViewById(R.id.Inputpicktime);
        txtdropdate = findViewById(R.id.txtdropdate);
        txtdroptime = findViewById(R.id.Inputdroptime);
        calculate = (Button) findViewById(R.id.calculate);
        btn = findViewById(R.id.btnok);
        result = findViewById(R.id.result);
        reserve1 = new Reservation();

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = simpleDateFormat.format(Calendar.getInstance().getTime());
        txtdropdate.setText(date);


        txtpickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Reserve.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener1,year,month,day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new
                        ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });

        dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month +1;
                String date = day + "/" + month + "/" + year;
                txtpickdate.setText(date);
            }
        };

//


        txtdropdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Reserve.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener2,year,month,day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new
                        ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });

        dateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month +1;
                String date = day + "/" + month + "/" + year;
                txtdropdate.setText(date);
            }
        };


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sDate = txtpickdate.getText().toString();
                String eDate = txtdropdate.getText().toString();
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");

                try{
                    Date date1 = simpleDateFormat1.parse(sDate);
                    Date date2 = simpleDateFormat.parse(eDate);

                    long startDate = date1.getTime();
                    long endDate = date2.getTime();

                    if (startDate <= endDate) {
                        Period period = new Period(startDate, endDate, PeriodType.yearMonthDay());
                        int years = period.getYears();
                        int months = period.getMonths();
                        int days = period.getDays();

                        result.setText(Integer.toString(days));

                    } else {
                        Toast.makeText(getApplicationContext(),"Not a valid date",Toast.LENGTH_SHORT).show();


                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }





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


