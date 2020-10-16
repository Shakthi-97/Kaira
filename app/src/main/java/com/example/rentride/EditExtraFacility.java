package com.example.rentride;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditExtraFacility extends AppCompatActivity {

    //Initialize variable
    DatabaseReference dbRef,delDBRef;
    Calculate cal;

    Button calUpdate,extra_next;
    EditText quantity,qty;
    TextView amount, amt, total_price,total_added,final_total;
    ImageView delete_seat, delete_gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_extra_facility);


        quantity = findViewById(R.id.qty_count);
        amount = findViewById(R.id.seat_amount);
        qty = findViewById(R.id.qty_num);
        amt = findViewById(R.id.gps_amount);
        total_price = findViewById(R.id.seat_price1);
        total_added = findViewById(R.id.gps_price);
        final_total = findViewById(R.id.tot_price);
        delete_seat = (ImageView) findViewById(R.id.delete_seat);
        delete_gps = (ImageView) findViewById(R.id.delete_gps);
        extra_next = (Button) findViewById(R.id.extra_next);



        dbRef = FirebaseDatabase.getInstance().getReference().child("ExtraFacility").child("Calculate1");

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    quantity.setText(snapshot.child("qty1").getValue().toString());
                    qty.setText(snapshot.child("qty2").getValue().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        calUpdate = findViewById(R.id.submit_tot1);

        calUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer No1 = Integer.parseInt(quantity.getText().toString());
                //Integer No2 = Integer.parseInt(amount.getText().toString());

                Integer tot = No1 * 2000;
                total_price.setText(Integer.toString(tot));

                Integer Num1 = Integer.parseInt(qty.getText().toString());
                //Integer Num2 = Integer.parseInt(amt.getText().toString());


                Integer totl = Num1 * 800;
                total_added.setText(Integer.toString(totl));

                Integer finaltot = tot + totl;
                final_total.setText(Integer.toString(finaltot));

                dbRef = FirebaseDatabase.getInstance().getReference();

                dbRef.child("ExtraFacility").child("Calculate1").child("qty1").setValue(quantity.getText().toString().trim());
                dbRef.child("ExtraFacility").child("Calculate1").child("qty2").setValue(qty.getText().toString().trim());


                Toast.makeText(getApplicationContext(), "Details Updated Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        delete_seat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAlertDialogue();

            }

            private void CreateAlertDialogue() {

                AlertDialog.Builder builder = new AlertDialog.Builder(EditExtraFacility.this);
                builder.setMessage("Are you sure want to remove this facility?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        delDBRef = FirebaseDatabase.getInstance().getReference().child("ExtraFacility").child("Calculate1").child("qty1");
                        delDBRef.removeValue();
                        quantity.setText("");
                        Toast.makeText(getApplicationContext(),"Child Toddler Seat have been removed",Toast.LENGTH_SHORT).show();
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

        delete_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAlertDialogue();

            }

            private void CreateAlertDialogue() {

                AlertDialog.Builder builder = new AlertDialog.Builder(EditExtraFacility.this);
                builder.setMessage("Are you sure want to remove this facility?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        delDBRef = FirebaseDatabase.getInstance().getReference().child("ExtraFacility").child("Calculate1").child("qty2");
                        delDBRef.removeValue();
                        qty.setText("");
                        Toast.makeText(getApplicationContext(),"GPS have been removed",Toast.LENGTH_SHORT).show();
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

        extra_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditExtraFacility.this, EditDriver.class);
                startActivity(intent);
            }
        });




    }
}