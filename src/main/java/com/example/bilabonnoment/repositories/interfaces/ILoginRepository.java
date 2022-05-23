package com.example.bilabonnoment.repositories.interfaces;

import com.example.bilabonnoment.models.User;

public interface ILoginRepository {
    public User authenticateUser(String username, String password);
}
