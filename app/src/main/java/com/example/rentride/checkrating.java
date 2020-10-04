package com.example.rentride;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class checkrating extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.complaints.MESSAGE";
    TextView rateCount,showRating;
    EditText review;

    float rateValue;
    String temp;
    Button submit;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkrating);

        rateCount=findViewById(R.id.rateCount);
        ratingBar = (RatingBar) findViewById(R.id.ratingBarr);
        submit=(Button) findViewById(R.id.submit);
        review=findViewById(R.id.review);
        showRating=findViewById(R.id.showRating);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(checkrating.this,"Stars:" +(int ) v , Toast.LENGTH_SHORT).show();

                rateValue=ratingBar.getRating();

                if(rateValue<=1 && rateValue>0)
                    rateCount.setText("Bad" +rateValue+"/5.0");
                else if(rateValue<=2 && rateValue>1)
                    rateCount.setText("Average" +rateValue+"/5.0");
                else if(rateValue<=3 && rateValue>2)
                    rateCount.setText("Good" +rateValue+"/5.0");
                else if(rateValue<=4 && rateValue>3)
                    rateCount.setText("VeryGood" +rateValue+"/5.0");
                else
                    rateCount.setText("Excellent" +rateValue+"/5.0");
            }
        });




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(checkrating.this, "Stars:" + (int)ratingBar.getRating(), Toast.LENGTH_SHORT ).show();
                temp=rateCount.getText().toString();
                showRating.setText("Your Rating for Us \n" +temp +"\n" +review.getText());
                review.setText("");
                ratingBar.setRating(0);
                rateCount.setText("");
            }
        });
    }
}