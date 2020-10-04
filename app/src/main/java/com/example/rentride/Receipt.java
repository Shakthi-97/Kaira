package com.example.rentride;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Receipt extends AppCompatActivity {

    TextView carfee,extrafee,totalcash,pickup,dropoff;
    Button book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        carfee = (TextView) findViewById(R.id.carfee);
        extrafee = (TextView) findViewById(R.id.extrafee);
        totalcash = (TextView) findViewById(R.id.total);
        pickup = (TextView) findViewById(R.id.pickup);
        dropoff = (TextView) findViewById(R.id.dropoff);
        book = (Button) findViewById(R.id.book);

        Float no1 = Float.parseFloat(carfee.getText().toString());
        Float no2 = Float.parseFloat(extrafee.getText().toString());

        Float total = no1 + no2;
        totalcash.setText(Float.toString(total));

        Float pickupPay = total * 60/100;
        pickup.setText(Float.toString(pickupPay));

        Float dropoffPay = total - pickupPay;
        dropoff.setText(Float.toString(dropoffPay));

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Receipt.this, CreateDriver.class);
                startActivity(intent);
            }
        });


    }
}