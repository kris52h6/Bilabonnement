package com.example.bilabonnoment.models;

public class Customer {

    private int id;
    private int CPR;
    private String firstname;
    private String lastname;

    public Customer(int id, int CPR, String firstname, String lastname) {
        this.id = id;
        this.CPR = CPR;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public int getCPR() {
        return CPR;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", CPR=" + CPR +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
