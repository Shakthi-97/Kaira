package com.example.rentride;

public class Complaint {

    private String name;
    private String mobile;
    private String email;

    private String address;
    private String date;
    private String complaint;

    public Complaint() {

    }


    public static boolean isValid(final String date) {
        return date != null;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String tMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }


    public static boolean isValid1(final String mobile) {
        return mobile != null;

    }
}
