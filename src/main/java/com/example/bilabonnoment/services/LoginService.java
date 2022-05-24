package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.User;
import com.example.bilabonnoment.repositories.interfaces.ILoginRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class LoginService {
    private final ILoginRepository loginRepository;

    public LoginService(ILoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public User authenticateUser(String username, String password) {
        return loginRepository.authenticateUser(username, password);
    }

    public ArrayList<String> getLoggedInUserInfo(User user) {
        ArrayList<String> userInfo = new ArrayList<>();
        if (user != null) {
            /*
            session.setAttribute("userId",user.getId());
            session.setAttribute("loggedInUsername",user.getUsername());
            session.setAttribute("userRole", user.getRole());
             */

            userInfo.add(Integer.toString(user.getId()));
            userInfo.add(user.getUsername());
            userInfo.add(user.getRole());
        }
        //return session;
        return userInfo;
    }


    public String reDirectUser(HttpSession session) {
        String userRole = (String) session.getAttribute("userRole");
        System.out.println(userRole);

        String pageRedirect = null;

        switch (userRole) {
            case "BUSINESS" -> pageRedirect = "redirect:/businessDevelopment";
            case "CONTRACT" -> pageRedirect = "redirect:/allContracts";
            case "DAMAGE" -> pageRedirect = "redirect:/damageIndex";
        }
        ;

        return pageRedirect;
    }
}
