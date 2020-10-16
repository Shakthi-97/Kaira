package com.example.rentride;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ExtraFacility extends AppCompatActivity {

    //Initialize variable
    DatabaseReference dbRef;
    Calculate cal;

    Button submit_tot, edit_btn;
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

        cal = new Calculate();


        submit_tot.setOnClickListener(new View.OnClickListener() {
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

                Integer  finaltot = tot + totl;
                final_total.setText(Integer.toString(finaltot));


                dbRef = FirebaseDatabase.getInstance().getReference().child("ExtraFacility");

                try {
                    if (TextUtils.isEmpty(quantity.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter Quantity", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(quantity.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Please Enter Quantity", Toast.LENGTH_SHORT).show();
                    }else {
                        cal.setQty1(Integer.parseInt(quantity.getText().toString().trim()));
                        cal.setQty2(Integer.parseInt(qty.getText().toString().trim()));

                        dbRef.child("Calculate1").setValue(cal);
                        Toast.makeText(getApplicationContext(),"Submitted successfully",Toast.LENGTH_SHORT).show();
                        clearControls();


                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(ExtraFacility.this, Receipt.class);
                startActivity(intent);


            }


        });
    }


    private void clearControls(){
        quantity.setText("");
        qty.setText("");


    }
}

