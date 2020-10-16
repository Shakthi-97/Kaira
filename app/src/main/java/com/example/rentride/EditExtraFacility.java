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
    DatabaseReference dbRef;
    Calculate cal;

    Button submit_tot,calUpdate,delet_btn,delete_but;
    EditText quantity,qty;
    TextView amount, amt, total_price,total_added,final_total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_facility);


        submit_tot = findViewById(R.id.submit_tot);
        quantity = findViewById(R.id.qty_count);
        amount = findViewById(R.id.seat_amount);
        qty = findViewById(R.id.qty_num);
        amt = findViewById(R.id.gps_amount);
        total_price = findViewById(R.id.seat_price);
        total_added = findViewById(R.id.gps_price);
        final_total = findViewById(R.id.tot_price);



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

        calUpdate = findViewById(R.id.submit_tot);

        calUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer No1 = Integer.parseInt(quantity.getText().toString());
                Integer No2 = Integer.parseInt(amount.getText().toString());

                Integer tot = No1 * 2000;
                total_price.setText(Integer.toString(tot));

                Integer Num1 = Integer.parseInt(qty.getText().toString());
                Integer Num2 = Integer.parseInt(amt.getText().toString());


                Integer totl = Num1 * 800;
                total_added.setText(Integer.toString(totl));

                Integer finaltot = tot + totl;
                final_total.setText(Integer.toString(finaltot));

                dbRef = FirebaseDatabase.getInstance().getReference();

                dbRef.child("ExtraFacility").child("Calculate1").child("qty1").setValue(quantity.getText().toString().trim());
                dbRef.child("ExtraFacility").child("Calculate1").child("qty2").setValue(qty.getText().toString().trim());



                Toast.makeText(getApplicationContext(), "Details Updated Successfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(EditExtraFacility.this, EditDriver.class);
                startActivity(intent);

            }
        });


    }
}