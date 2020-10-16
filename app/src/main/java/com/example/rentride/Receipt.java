package com.example.rentride;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Receipt extends AppCompatActivity {

    TextView carfee,extrafee,totalcash,pickup,dropoff,daysRs;
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

        Intent intent = getIntent();
        Double carFees = intent.getDoubleExtra(ExtraFacility.EXTRA_NUMBER1, 0 );
        Double extraFees = intent.getDoubleExtra(ExtraFacility.EXTRA_NUMBER2, 0 );
        carfee.setText(""+carFees);
        extrafee.setText(""+extraFees);



        Double no1 = Double.parseDouble(carfee.getText().toString());
        Double no2 = Double.parseDouble(extrafee.getText().toString());

        Double total = no1 + no2;
        totalcash.setText(Double.toString(total));

        Double pickupPay = total * 60/100;
        pickup.setText(Double.toString(pickupPay));

        Double dropoffPay = total - pickupPay;
        dropoff.setText(Double.toString(dropoffPay));




        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Receipt.this, CreateDriver.class);
                startActivity(intent);
            }
        });


    }
}