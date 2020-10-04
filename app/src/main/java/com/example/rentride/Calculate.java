package com.example.rentride;

public class Calculate {

    private int qty1;
    private int qty2;


    public Calculate() {
    }

    public int getQty1() {
        return qty1;
    }

    public void setQty1(int qty1) {
        this.qty1 = qty1;
    }

    public int getQty2() {
        return qty2;
    }

    public void setQty2(int qty2) {
        this.qty2 = qty2;
    }




    public Calculate(int qty1, int qty2, int seat_price, int gps_price, int tot_price) {
        this.qty1 = qty1;
        this.qty2 = qty2;



    }
}
