package com.example.bilabonnoment.models;

public class Damage {

    private int damage_id;
    private double damage_price;
    private String damage_description;
    private int report_id;

    public Damage(int damage_id, double damage_price, String damage_description, int report_id) {
        this.damage_id = damage_id;
        this.damage_price = damage_price;
        this.damage_description = damage_description;
        this.report_id = report_id;
    }

    public int getDamage_id() {
        return damage_id;
    }

    public void setDamage_id(int damage_id) {
        this.damage_id = damage_id;
    }

    public double getDamage_price() {
        return damage_price;
    }

    public void setDamage_price(double damage_price) {
        this.damage_price = damage_price;
    }

    public String getDamage_description() {
        return damage_description;
    }

    public void setDamage_description(String damage_description) {
        this.damage_description = damage_description;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    @Override
    public String toString() {
        return "Damage{" +
                "damage_id=" + damage_id +
                ", damage_price=" + damage_price +
                ", damage_description='" + damage_description + '\'' +
                ", report_id=" + report_id +
                '}';
    }
}
