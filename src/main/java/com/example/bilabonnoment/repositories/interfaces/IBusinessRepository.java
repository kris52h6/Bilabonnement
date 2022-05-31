package com.example.bilabonnoment.repositories.interfaces;

import com.example.bilabonnoment.models.Car;

import java.util.List;

public interface IBusinessRepository extends IRepository<Car> {
    List<Car> getAllRentedCars();

    Car getCarFromVinNo(String vin);

    void editCarLeasingStatus(int carId, boolean isLeased);

    List<Car> getAllAvailableCars();
    }
