package com.example.bilabonnoment.controllers;

import com.example.bilabonnoment.repositories.BusinessRepository;
import com.example.bilabonnoment.services.BusinessService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class BusinessController {
    private final BusinessRepository businessRepository = new BusinessRepository();
    private final BusinessService businessService = new BusinessService(businessRepository);
    private final String AREA = "BUSINESS";

    @GetMapping("/businessDevelopment")
    public String getAllCars(Model model, HttpSession session) {
        if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals(AREA)) {
            model.addAttribute("cars", businessService.getAllRentedCars());
            model.addAttribute("totalValue", businessService.totalValueOfAllRentedCars(businessService.getAllRentedCars()));
            return "business-templates/business-development";
        }
        return "access-error";
    }
}
