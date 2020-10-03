package com.example.rentride;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PastTrips {
    String tripID;
    String driverName;
    String phone;
    String dateTime;
    String pickupDis;
    String pickupDate;
    String dropoffDis;
    String dropoffDate;
    Long amount;
    ImageView locationImage;
    TextView rides;
    ImageView btnDelete;
    TextView completed;
    Button btnFeedback;
    ImageView picPickup;
    ImageView dotLine;
    ImageView picDropoff;
    TextView lkr;
    View divider;
    View divider2;
    View divider3;

    public PastTrips(String tripID, String driverName, String phone, String dateTime, String pickupDis,String pickupDate, String dropoffDis,String dropoffDate, Long amount, ImageView locationImage, TextView rides, ImageView btnDelete, TextView completed, Button btnFeedback, ImageView picPickup, ImageView dotLine, ImageView picDropoff, TextView lkr, View divider, View divider2, View divider3) {
        this.tripID = tripID;
        this.driverName = driverName;
        this.phone = phone;
        this.dateTime = dateTime;
        this.pickupDis = pickupDis;
        this.pickupDate = pickupDate;
        this.dropoffDis = dropoffDis;
        this.pickupDate = dropoffDate;
        this.amount = amount;
        this.locationImage = locationImage;
        this.rides = rides;
        this.btnDelete = btnDelete;
        this.completed = completed;
        this.btnFeedback = btnFeedback;
        this.picPickup = picPickup;
        this.dotLine = dotLine;
        this.picDropoff = picDropoff;
        this.lkr = lkr;
        this.divider = divider;
        this.divider2 = divider2;
        this.divider3 = divider3;
    }

    public String getTripID() {
        return tripID;
    }

    public void setTripID(String tripID) {
        this.tripID = tripID;
    }

    public PastTrips() {
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getDropoffDate() {
        return dropoffDate;
    }

    public void setDropoffDate(String dropoffDate) {
        this.dropoffDate = dropoffDate;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getPickupDis() {
        return pickupDis;
    }

    public void setPickupDis(String pickupDis) {
        this.pickupDis = pickupDis;
    }

    public String getDropoffDis() {
        return dropoffDis;
    }

    public void setDropoffDis(String dropoffDis) {
        this.dropoffDis = dropoffDis;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public ImageView getLocationImage() {
        return locationImage;
    }

    public void setLocationImage(ImageView locationImage) {
        this.locationImage = locationImage;
    }

    public TextView getRides() {
        return rides;
    }

    public void setRides(TextView rides) {
        this.rides = rides;
    }

    public ImageView getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(ImageView btnDelete) {
        this.btnDelete = btnDelete;
    }

    public TextView getCompleted() {
        return completed;
    }

    public void setCompleted(TextView completed) {
        this.completed = completed;
    }

    public Button getBtnFeedback() {
        return btnFeedback;
    }

    public void setBtnFeedback(Button btnFeedback) {
        this.btnFeedback = btnFeedback;
    }

    public ImageView getPicPickup() {
        return picPickup;
    }

    public void setPicPickup(ImageView picPickup) {
        this.picPickup = picPickup;
    }

    public ImageView getDotLine() {
        return dotLine;
    }

    public void setDotLine(ImageView dotLine) {
        this.dotLine = dotLine;
    }

    public ImageView getPicDropoff() {
        return picDropoff;
    }

    public void setPicDropoff(ImageView picDropoff) {
        this.picDropoff = picDropoff;
    }

    public TextView getLkr() {
        return lkr;
    }

    public void setLkr(TextView lkr) {
        this.lkr = lkr;
    }

    public View getDivider() {
        return divider;
    }

    public void setDivider(View divider) {
        this.divider = divider;
    }

    public View getDivider2() {
        return divider2;
    }

    public void setDivider2(View divider2) {
        this.divider2 = divider2;
    }

    public View getDivider3() {
        return divider3;
    }

    public void setDivider3(View divider3) {
        this.divider3 = divider3;
    }
}
