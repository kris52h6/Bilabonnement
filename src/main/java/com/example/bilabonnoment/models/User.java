package com.example.bilabonnoment.models;

public class User
{
    private int id;
    private String username;
    private String password;

    public User(int id, String user_username, String user_password) {
        this.id = id;
        this.username = user_username;
        this.password = user_password;
    }

    public int getId() {
        return id;
    }

    public String getUser_username() {
        return username;
    }

    public String getUser_password() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_username='" + username + '\'' +
                ", user_password='" + password + '\'' +
                '}';
    }
}
