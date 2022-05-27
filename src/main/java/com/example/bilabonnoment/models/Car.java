package com.example.bilabonnoment.models;

public class Car {
    private int id;
    private String vin;
    private String make;
    private String model;
    private String equipmentLevel;
    private double valuePreTax;
    private double RegistrationTax;
    private double co2Emission;
    private boolean is_leased;

    public Car(int id, String vin, String make, String model, String equipmentLevel, double valuePreTax, double registrationTax, double co2Emission, boolean is_leased) {
        this.id = id;
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.equipmentLevel = equipmentLevel;
        this.valuePreTax = valuePreTax;
        RegistrationTax = registrationTax;
        this.co2Emission = co2Emission;
        this.is_leased = is_leased;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vinNo) {
        this.vin = vinNo;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEquipmentLevel() {
        return equipmentLevel;
    }

    public void setEquipmentLevel(String equipmentLevel) {
        this.equipmentLevel = equipmentLevel;
    }

    public double getValuePreTax() {
        return valuePreTax;
    }

    public void setValuePreTax(double valuePreTax) {
        this.valuePreTax = valuePreTax;
    }

    public double getRegistrationTax() {
        return RegistrationTax;
    }

    public void setRegistrationTax(double registrationTax) {
        RegistrationTax = registrationTax;
    }

    public double getCo2Emission() {
        return co2Emission;
    }

    public void setCo2Emission(double co2Emission) {
        this.co2Emission = co2Emission;
    }

    public boolean getIs_leased() {
        return is_leased;
    }

    public void setIs_leased(boolean is_leased) {
        this.is_leased = is_leased;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", vin=" + vin +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", equipmentLevel='" + equipmentLevel + '\'' +
                ", valuePreTax=" + valuePreTax +
                ", RegistrationTax=" + RegistrationTax +
                ", co2Emission=" + co2Emission +
                ", is_leased=" + is_leased +
                '}';
    }
}
