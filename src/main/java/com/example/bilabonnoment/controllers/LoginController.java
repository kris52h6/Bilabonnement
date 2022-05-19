package com.example.bilabonnoment.controllers;

import com.example.bilabonnoment.models.User;
import com.example.bilabonnoment.repositories.LoginRepository;
import com.example.bilabonnoment.services.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

public class LoginController {

    private final LoginRepository loginRepository = new LoginRepository();


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/authUser")
    public String authUser(WebRequest dataFromForm, HttpSession session)
    {
        LoginService loginService = new LoginService(loginRepository);
        String username = dataFromForm.getParameter("username");
        String password = dataFromForm.getParameter("password");

        HttpSession userSession = loginService.createUserSession(loginService.authenticateUser(username, password));

        //Tjek hvilke userrole brugeren har
        //Tillad adgang til sider der tilhører rolle.

        return "TEMP";
    }
}
