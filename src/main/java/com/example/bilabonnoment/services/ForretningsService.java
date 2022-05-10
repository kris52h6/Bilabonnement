package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Car;
import com.example.bilabonnoment.repositories.CarRepository;
import com.example.bilabonnoment.repositories.IRepository;
import com.example.bilabonnoment.utility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ForretningsService {

    public int totalValueOfAllRentedCars(List<Car> rentedCars){
        int total = 0;
        for (Car car : rentedCars){
            total += car.getValuePreTax();
        }
        return total;
    }
}
