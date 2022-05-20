package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Car;
import com.example.bilabonnoment.repositories.CarRepository;
import com.example.bilabonnoment.repositories.interfaces.IRepository;

import java.util.List;

public class BusinessService {

    /*private final IRepository<Car> carRepository;

    public BusinessService(IRepository<> carRepository){
        this.carRepository = carRepository;
    }*/

    public int totalValueOfAllRentedCars(List<Car> rentedCars){
        //List rentedCars = carRepository.getAllRentedCars();
        int total = 0;
        for (Car car : rentedCars){
            total += car.getValuePreTax();
        }
        return total;
    }
}
