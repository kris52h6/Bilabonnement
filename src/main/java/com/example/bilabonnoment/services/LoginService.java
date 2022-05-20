package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.User;
import com.example.bilabonnoment.repositories.interfaces.ILoginRepository;

import javax.servlet.http.HttpSession;

public class LoginService {
    private final ILoginRepository loginRepository;

    public LoginService(ILoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public User authenticateUser(String username, String password) {
        return loginRepository.authenticateUser(username, password);
    }

    public HttpSession createUserSession(User user){
        HttpSession session = null;
        if(user != null){
            session.setAttribute("userId",user.getId());
            session.setAttribute("loggedInUsername",user.getUsername());
            session.setAttribute("userRole", user.getRole());
        }
        return session;
    }
}
