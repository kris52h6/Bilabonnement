package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Car;
import com.example.bilabonnoment.models.Customer;
import com.example.bilabonnoment.repositories.BusinessRepository;
import com.example.bilabonnoment.repositories.CustomerRepository;

import java.util.List;

public class CustomerBusinessService {
    private final CustomerRepository customerRepository;
    private final BusinessRepository businessRepository;

    public CustomerBusinessService(CustomerRepository customerRepository, BusinessRepository businessRepository) {
        this.customerRepository = customerRepository;
        this.businessRepository = businessRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.getAllEntities();
    }

    public List<Car> getAllAvailableCars() {
        return businessRepository.getAllAvailableCars();
    }
}
