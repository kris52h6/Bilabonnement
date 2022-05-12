package com.example.bilabonnoment.models;

import java.sql.Date;

public class Contract {

    private int id;
    private String cprNr;
    private int vin_no;
    private double price;
    private String pickupPlace;
    private String returnPlace;
    private Date startDate;
    private Date endDate;
    private boolean isReturned;

    public enum Damage {
        UNCHECKED,
        YES,
        NO
    }

    public Damage damage;



    public Contract(int id, String cprNr, int vin_no, double price, String pickupPlace, String returnPlace, Date startDate, Date endDate, boolean isReturned, Damage damage) {
        this.id = id;
        this.cprNr = cprNr;
        this.vin_no = vin_no;
        this.price = price;
        this.pickupPlace = pickupPlace;
        this.returnPlace = returnPlace;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isReturned = isReturned;
        this.damage = damage;
    }

    public Damage getDamage() {
        return damage;
    }

    public void setDamage(Damage damage) {
        this.damage = damage;
    }

    public int getId() {
        return id;
    }

    public String getCprNr() {
        return cprNr;
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

    public void setCprNr(String cprNr) {
        this.cprNr = cprNr;
    }
}
