package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Car;
import com.example.bilabonnoment.repositories.BusinessRepository;

import java.util.List;

public class BusinessService {

    private final BusinessRepository businessRepository;

    public BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    public int totalValueOfAllRentedCars(List<Car> rentedCars) {
        int total = 0;
        for (Car car : rentedCars) {
            total += car.getValuePreTax();
        }
        return total;
    }


    public List<Car> getAllRentedCars() {
        return businessRepository.getAllRentedCars();
    }
}
