package com.example.bilabonnoment.models;

public class Customer {

    private int id;
    private String cprNum;
    private String firstname;
    private String lastname;

    public Customer(int id, String cprNum, String firstname, String lastname) {
        this.id = id;
        this.cprNum = cprNum;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public String getCPR() {
        return cprNum;
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
                ", cprNum=" + cprNum +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
