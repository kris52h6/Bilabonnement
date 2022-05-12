package com.example.bilabonnoment.controllers;

import com.example.bilabonnoment.repositories.CarRepository;
import com.example.bilabonnoment.services.BusinessService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BusinessController {

    //private final CarRepository carRepository = new CarRepository();

    private final CarRepository carRepository = new CarRepository();
    private final BusinessService businessService = new BusinessService();

    @GetMapping("/businessDevelopment")
    public String getAllCars(Model model) {
        model.addAttribute("cars", carRepository.getAllRentedCars());
        model.addAttribute("totalValue", businessService.totalValueOfAllRentedCars(carRepository.getAllRentedCars()));
        System.out.println(carRepository.getAllRentedCars());
        System.out.println(businessService.totalValueOfAllRentedCars(carRepository.getAllRentedCars()));
        return "businessDevelopment";
    }
}
