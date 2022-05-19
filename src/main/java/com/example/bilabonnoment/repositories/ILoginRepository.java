package com.example.bilabonnoment.repositories;

import com.example.bilabonnoment.models.User;

public interface ILoginRepository {
    public User authenticateUser(String username, String password);
}
