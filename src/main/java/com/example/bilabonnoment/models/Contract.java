package com.example.bilabonnoment.models;

import java.sql.Date;

public class Contract {
    private int id;
    private int user_id;
    private int vin_no;
    private double price;
    private String pickupPlace;
    private String returnPlace;
    private Date startDate;
    private Date endDate;
    boolean isReturned;


    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getVin_no() {
        return vin_no;
    }

    public double getPrice() {
        return price;
    }

    public String getPickupPlace() {
        return pickupPlace;
    }

    public String getReturnPlace() {
        return returnPlace;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setVin_no(int vin_no) {
        this.vin_no = vin_no;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPickupPlace(String pickupPlace) {
        this.pickupPlace = pickupPlace;
    }

    public void setReturnPlace(String returnPlace) {
        this.returnPlace = returnPlace;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", vin_no=" + vin_no +
                ", price=" + price +
                ", pickupPlace='" + pickupPlace + '\'' +
                ", returnPlace='" + returnPlace + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isReturned=" + isReturned +
                '}';
    }
}
