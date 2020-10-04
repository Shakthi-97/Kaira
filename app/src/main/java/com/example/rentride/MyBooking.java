package com.example.rentride;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class MyBooking extends AppCompatActivity {

    EditText txtpick,txtdrop,txtpickdate,txtpicktime,txtdropdate,txtdroptime;
    Button butCancel,butUpdate,next;
    Reservation reserve1;
    DatabaseReference dbRef;
    int mYear,mMonth,mDay, mHour,mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);

        txtpick = findViewById(R.id.Inputpick);
        txtdrop = findViewById(R.id.Inputdrop);
        txtpickdate = findViewById(R.id.Inputpickdate);
        txtpicktime = findViewById(R.id.Inputpicktime);
        txtdropdate = findViewById(R.id.Inputdropdate);
        txtdroptime = findViewById(R.id.Inputdroptime);
        next = (Button) findViewById(R.id.next);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Reservation").child("Res1");

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    txtpick.setText(snapshot.child("pickuplocation").getValue().toString());
                    txtdrop.setText(snapshot.child("dropofflocation").getValue().toString());
                    txtpickdate.setText(snapshot.child("pickupdate").getValue().toString());
                    txtpicktime.setText(snapshot.child("pickuptime").getValue().toString());
                    txtdropdate.setText(snapshot.child("returndate").getValue().toString());
                    txtdroptime.setText(snapshot.child("returntime").getValue().toString());

                }else{
                    Toast.makeText(getApplicationContext(),"No details to display",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        butUpdate = findViewById(R.id.Update);

        butUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbRef= FirebaseDatabase.getInstance().getReference();

                dbRef.child("Reservation").child("Res1").child("pickuplocation").setValue(txtpick.getText().toString().trim());
                dbRef.child("Reservation").child("Res1").child("dropofflocation").setValue(txtdrop.getText().toString().trim());
                dbRef.child("Reservation").child("Res1").child("pickupdate").setValue(txtpickdate.getText().toString().trim());
                dbRef.child("Reservation").child("Res1").child("pickuptime").setValue(txtpicktime.getText().toString().trim());
                dbRef.child("Reservation").child("Res1").child("returndate").setValue(txtdropdate.getText().toString().trim());
                dbRef.child("Reservation").child("Res1").child("returntime").setValue(txtdroptime.getText().toString().trim());


                Toast.makeText(getApplicationContext(),"Details Updated Succesfully",Toast.LENGTH_SHORT).show();


            }
        });


        butCancel = findViewById(R.id.Cancel);

        butCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAlertDialogue();

            }

            private void CreateAlertDialogue() {

                AlertDialog.Builder builder = new AlertDialog.Builder(MyBooking.this);
                builder.setMessage("Are you sure to cancel the booking?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        dbRef = FirebaseDatabase.getInstance().getReference().child("Reservation").child("Res1");
                        dbRef.removeValue();
                        clearControls();
                        Toast.makeText(getApplicationContext(),"Booking Details have been deleted",Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast.makeText(getApplicationContext(),"Continue",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create();
                builder.show();
            }
        });





        txtpickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar calendar = Calendar.getInstance();
                mYear= calendar.get(calendar.YEAR);
                mMonth= calendar.get(calendar.MONTH);
                mDay= calendar.get(calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog= new DatePickerDialog(MyBooking.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        txtpickdate.setText(i+"/"+(i1+1)+"/"+i2);
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
                        MyBooking.this,
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

                DatePickerDialog datePickerDialog= new DatePickerDialog(MyBooking.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        txtdropdate.setText(i+"/"+(i1+1)+"/"+i2);
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
                        MyBooking.this,
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

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyBooking.this, EditExtraFacility.class);
                startActivity(intent);
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