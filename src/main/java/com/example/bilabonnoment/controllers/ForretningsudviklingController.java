package com.example.bilabonnoment.controllers;

import com.example.bilabonnoment.repositories.CarRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForretningsudviklingController {

    private final CarRepository carRepository = new CarRepository();

    @GetMapping("/forretningsudvikling")
    public String getAllCars(Model model) {
        model.addAttribute("cars", carRepository.getAllRentedCars());
        System.out.println(carRepository.getAllRentedCars());
        return "forretningsudvikling";
    }
}
