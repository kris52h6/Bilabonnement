package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Car;
import com.example.bilabonnoment.repositories.interfaces.IRepository;

import java.util.List;

public class BusinessService {
    
    public int totalValueOfAllRentedCars(List<Car> rentedCars){
        int total = 0;
        for (Car car : rentedCars){
            total += car.getValuePreTax();
        }
        return total;
    }
}
