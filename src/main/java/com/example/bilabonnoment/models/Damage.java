package com.example.bilabonnoment.models;

public class Damage {

    private int id;
    private double price;
    private String description;
    private int reportId;

    public Damage(int id, double price, String description, int reportId) {
        this.id = id;
        this.price = price;
        this.description = description;
        this.reportId = reportId;
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

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    @Override
    public String toString() {
        return "Damage{" +
                "id=" + id +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", reportId=" + reportId +
                '}';
    }
}
