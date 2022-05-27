package com.example.bilabonnoment.controllers;

import com.example.bilabonnoment.repositories.LoginRepository;
import com.example.bilabonnoment.services.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class LoginController {

    private final LoginRepository loginRepository = new LoginRepository();
    private final LoginService loginService = new LoginService(loginRepository);


    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/authUser")
    public String authUser(WebRequest dataFromForm, HttpSession session) {
        String username = dataFromForm.getParameter("username");
        String password = dataFromForm.getParameter("password");

        ArrayList<String> userInfo = loginService.getLoggedInUserInfo(loginService.authenticateUser(username, password));

        // viser fejlside, hvis login ikke eksistere
        if (userInfo == null) {
            return "login-error";
        }

        int user_userId = Integer.parseInt(userInfo.get(0));
        String user_username = userInfo.get(1);
        String user_userRole = userInfo.get(2);

        session.setAttribute("userId", user_userId);
        session.setAttribute("username", user_username);
        session.setAttribute("userRole", user_userRole);

        //Tjek hvilke userrole brugeren har
        return loginService.reDirectUser(session);
    }

    @GetMapping("/logOut")
    public String logOut(HttpSession session) {
        if (session != null) {
            session.removeAttribute("userId");
            session.removeAttribute("username");
            session.removeAttribute("userRole");
        }

        return "redirect:/";
    }

    @GetMapping("/redirectToFrontPage")
    public String redirectToFrontPage(HttpSession session) {
        return loginService.reDirectUser(session);
    }
}
