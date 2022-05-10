package com.example.bilabonnoment.models;

public class User
{
    private int id;
    private String user_username;
    private String user_password;

    public User(int id, String user_username, String user_password) {
        this.id = id;
        this.user_username = user_username;
        this.user_password = user_password;
    }

    public int getId() {
        return id;
    }

    public String getUser_username() {
        return user_username;
    }

    public String getUser_password() {
        return user_password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_username='" + user_username + '\'' +
                ", user_password='" + user_password + '\'' +
                '}';
    }
}
