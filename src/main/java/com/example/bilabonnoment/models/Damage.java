package com.example.bilabonnoment.models;

public class Damage {

    private int id;
    private double price;
    private String description;
    private int contractId;

    public Damage(int id, double price, String description, int reportId) {
        this.id = id;
        this.price = price;
        this.description = description;
        this.contractId = reportId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    @Override
    public String toString() {
        return "Damage{" +
                "damage_id=" + id +
                ", damage_price=" + price +
                ", damage_description='" + description + '\'' +
                ", report_id=" + contractId +
                '}';
    }
}
