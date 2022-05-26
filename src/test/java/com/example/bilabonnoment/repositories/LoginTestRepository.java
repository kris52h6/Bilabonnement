package com.example.bilabonnoment.repositories;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.models.User;
import com.example.bilabonnoment.repositories.interfaces.ILoginRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginTestRepository implements ILoginRepository {

    User user1 = new User(1,"bennybooster","dakkedak", "DAMAGE");
    User user2 = new User(2,"bananmorten","ostehaps", "CONTRACT");
    User user3 = new User(3,"karlossmartos","ikea123", "BUSINESS");
    User user4 = new User(4,"youngone","islagkage", "DAMAGE");

    List<User> users = Arrays.asList(
            new User(1,"bennybooster","dakkedak", "DAMAGE"),
            new User(2,"bananmorten","ostehaps", "CONTRACT"),
            new User(3,"karlossmartos","ikea123", "BUSINESS"),
            new User(4,"youngone","islagkage", "DAMAGE")
    );


    @Override
    public User authenticateUser(String username, String password) {
        for (User user : users) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
}
