package com.example.bilabonnoment.controllers;

import com.example.bilabonnoment.models.Car;
import com.example.bilabonnoment.repositories.CarRepository;
import com.example.bilabonnoment.repositories.IRepository;
import com.example.bilabonnoment.services.ForretningsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForretningsudviklingController {

    //private final CarRepository carRepository = new CarRepository();

    private final CarRepository carRepository = new CarRepository();
    private final ForretningsService forretningsService = new ForretningsService();

    @GetMapping("/forretningsudvikling")
    public String getAllCars(Model model) {
        model.addAttribute("cars", carRepository.getAllRentedCars());
        model.addAttribute("totalValue", forretningsService.totalValueOfAllRentedCars(carRepository.getAllRentedCars()));
        System.out.println(carRepository.getAllRentedCars());
        return "forretningsudvikling";
    }
}
