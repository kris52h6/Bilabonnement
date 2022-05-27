package com.example.bilabonnoment.models;

import java.sql.Date;

public class Contract {

    private int id;
    private String cprNum;
    private String vin;
    private double price;
    private String pickupPlace;
    private String returnPlace;
    private Date startDate;
    private Date endDate;
    private boolean isReturned;
    private Damage damage;

    public Contract(int id, String cprNum, String vin, double price, String pickupPlace, String returnPlace, Date startDate, Date endDate, boolean isReturned, Damage damage) {
        this.id = id;
        this.cprNum = cprNum;
        this.vin = vin;
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

    public void setId(int id) {
        this.id = id;
    }

    public String getCprNum() {
        return cprNum;
    }

    public void setCprNum(String cprNum) {
        this.cprNum = cprNum;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vinNo) {
        this.vin = vinNo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPickupPlace() {
        return pickupPlace;
    }

    public void setPickupPlace(String pickupPlace) {
        this.pickupPlace = pickupPlace;
    }

    public String getReturnPlace() {
        return returnPlace;
    }

    public void setReturnPlace(String returnPlace) {
        this.returnPlace = returnPlace;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", cprNum='" + cprNum + '\'' +
                ", vinNo=" + vin +
                ", price=" + price +
                ", pickupPlace='" + pickupPlace + '\'' +
                ", returnPlace='" + returnPlace + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isReturned=" + isReturned +
                ", damage=" + damage +
                '}';
    }

    /*@Override
    public String toString()
    {
        return "Contract{" +
                "id=" + id +
                ", cprNr='" + cprNr + '\'' +
                ", vin_no=" + vin_no +
                ", price=" + price +
                ", pickupPlace='" + pickupPlace + '\'' +
                ", returnPlace='" + returnPlace + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isReturned=" + isReturned +
                ", damage=" + damage +
                '}';
    }*/

    /*@Override
    public String toString() {
        return "Contract{" +
                "id=" + id;
    }*/

    public enum Damage {
        UNCHECKED,
        YES,
        NO
    }
}
