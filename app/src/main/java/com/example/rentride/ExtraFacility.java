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

    Button submit_tot;
    EditText quantity,qty;
    TextView amount, amt, total_price,total_added,final_total,carfee;

    public static final String EXTRA_NUMBER1 = "com.example.rentride.EXTRA_NUMBER1";
    public static final String EXTRA_NUMBER2 = "com.example.rentride.EXTRA_NUMBER2";

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
        carfee = findViewById(R.id.car_fee);

        cal = new Calculate();


        submit_tot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer No1 = Integer.parseInt(quantity.getText().toString());

                Double tot = No1 * 2000.0;
                total_price.setText(Double.toString(tot));

                Integer Num1 = Integer.parseInt(qty.getText().toString());

                Double totl = Num1 * 800.0;
                total_added.setText(Double.toString(totl));

                Double  finaltot = tot + totl;
                final_total.setText(Double.toString(finaltot));


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

                        Double car_fee = Double.parseDouble(carfee.getText().toString());
                        Double extra_fee = Double.parseDouble(final_total.getText().toString());

                        Intent intent = new Intent(ExtraFacility.this, Receipt.class);
                        intent.putExtra(EXTRA_NUMBER1, car_fee);
                        intent.putExtra(EXTRA_NUMBER2, extra_fee);
                        startActivity(intent);
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void clearControls(){
        quantity.setText("");
        qty.setText("");


    }
}

