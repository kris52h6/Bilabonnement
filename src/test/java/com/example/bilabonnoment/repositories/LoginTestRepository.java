package com.example.bilabonnoment.repositories;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.models.User;
import com.example.bilabonnoment.repositories.interfaces.ILoginRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginTestRepository implements ILoginRepository {

    List<User> users = Arrays.asList(
            new User(1,"damageuser","damagepassword", "DAMAGE"),
            new User(2,"businessuser","businesspassword", "BUSINESS"),
            new User(3,"contractuser","contractpassword", "CONTRACT")
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
