package com.example.bilabonnoment.models;

import java.sql.Date;

public class Contract {

    private int id;
    private String cprNum;
    private int vinNo;
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

    private Damage damage;

    public Contract(int id, String cprNum, int vinNo, double price, String pickupPlace, String returnPlace, Date startDate, Date endDate, boolean isReturned, Damage damage) {
        this.id = id;
        this.cprNum = cprNum;
        this.vinNo = vinNo;
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

    public String getCprNum() {
        return cprNum;
    }

    public int getVinNo() {
        return vinNo;
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


    public void setVinNo(int vinNo) {
        this.vinNo = vinNo;
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

    public void setCprNum(String cprNum) {
        this.cprNum = cprNum;
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

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", cprNum='" + cprNum + '\'' +
                ", vinNo=" + vinNo +
                ", price=" + price +
                ", pickupPlace='" + pickupPlace + '\'' +
                ", returnPlace='" + returnPlace + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", isReturned=" + isReturned +
                ", damage=" + damage +
                '}';
    }
}
