package com.example.rentride;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class Car extends AppCompatActivity {

    private static final String TAG = "Car1";
    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        Log.d(TAG, "onCreate: started");
        initImageBitmaps();
    }
    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: started");

        mImageUrls.add("https://stat.overdrive.in/wp-content/uploads/2018/08/Honda-City-Edge-Edition-image.jpg");
        mNames.add("Honda   Rs.5500");
        mImageUrls.add("https://c.ndtvimg.com/2019-08/bqk1fr74_hyundai-creta-sports-edition_625x300_05_August_19.jpg");
        mNames.add("Hyundai  Rs.7600");
        mImageUrls.add("https://imgd.aeplcdn.com/600x337/cw/ec/37710/Maruti-Suzuki-Baleno-Right-Front-Three-Quarter-147420.jpg?wm=0&q=85");
        mNames.add("Maruti Suzuki  Rs.4800");
        mImageUrls.add("https://images.unsplash.com/photo-1592198084033-aade902d1aae?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjE3MzYxfQ&w=1000&q=80");
        mNames.add("Ferrari  Rs.9500");
        mImageUrls.add("https://www.carblogindia.com/wp-content/uploads/2019/07/Mercedes-E-Class.jpg");
        mNames.add("Mercedes Benz  Rs.10500");
        mImageUrls.add("https://65e81151f52e248c552b-fe74cd567ea2f1228f846834bd67571e.ssl.cf1.rackcdn.com/ldm-images/2020-Jaguar-XF-Narvik-Black-Color.png");
        mNames.add("Jaguar  Rs.6900 ");
        mImageUrls.add("https://cdn.wallpapersafari.com/45/33/lF7yKE.jpg");
        mNames.add("Range Rover  Rs.8500");
        mImageUrls.add("https://cdn.motor1.com/images/mgl/vEJmQ/s1/bmw-i8-m-rendering.jpg");
        mNames.add("BMW  Rs.9900");
        mImageUrls.add("https://www.drivespark.com/images/2020-03/2020-audi-a3-exterior-10.jpg");
        mNames.add("Audi  Rs.7800");
        mImageUrls.add("https://ic1.maxabout.us/autos/cars_india//L/2017/3/lamborghini-aventador-s.jpg");
        mNames.add("Lamborghini  Rs.11000");



        initRecyclerView();
    }
    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: started");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames,mImageUrls,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

