package com.example.bilabonnoment.controllers;

import com.example.bilabonnoment.repositories.CarRepository;
import com.example.bilabonnoment.services.BusinessService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.net.http.HttpClient;

@Controller
public class BusinessController {

    //private final CarRepository carRepository = new CarRepository();

    private final CarRepository carRepository = new CarRepository();
    private final BusinessService businessService = new BusinessService();
    private final String AREA = "BUSINESS";

    @GetMapping("/businessDevelopment")
    public String getAllCars(Model model, HttpSession session) {
        if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals(AREA)) {
            model.addAttribute("cars", carRepository.getAllRentedCars());
            model.addAttribute("totalValue", businessService.totalValueOfAllRentedCars(carRepository.getAllRentedCars()));
            System.out.println(carRepository.getAllRentedCars());
            System.out.println(businessService.totalValueOfAllRentedCars(carRepository.getAllRentedCars()));
            return "business-templates/business-development";
        }
        return "access-error";
    }

}
