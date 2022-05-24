package com.example.bilabonnoment.repositories.interfaces;

import com.example.bilabonnoment.models.Car;

import java.util.List;

public interface IBusinessRepository extends IRepository<Car> {
    public List<Car> getAllRentedCars();


}