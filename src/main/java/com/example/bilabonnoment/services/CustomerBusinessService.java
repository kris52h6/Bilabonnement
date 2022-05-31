package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Car;
import com.example.bilabonnoment.models.Customer;
import com.example.bilabonnoment.repositories.BusinessRepository;
import com.example.bilabonnoment.repositories.CustomerRepository;
import com.example.bilabonnoment.repositories.interfaces.IBusinessRepository;
import com.example.bilabonnoment.repositories.interfaces.ICustomerRepository;

import java.util.List;

public class CustomerBusinessService {
    private final ICustomerRepository customerRepository;
    private final IBusinessRepository businessRepository;

    public CustomerBusinessService(ICustomerRepository customerRepository, IBusinessRepository businessRepository) {
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
