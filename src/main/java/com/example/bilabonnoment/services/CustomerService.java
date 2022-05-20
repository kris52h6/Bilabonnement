package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Customer;
import com.example.bilabonnoment.repositories.interfaces.ICustomerRepository;

import java.util.List;

public class CustomerService {
    private final ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllEntities() {
        return customerRepository.getAllEntities();
    }

    public Customer getSingleById(int id) {
        return customerRepository.getSingleById(id);
    }

    public Customer getCustomerFromCprNr(String cprNr) {
        return customerRepository.getCustomerFromCprNr(cprNr);
    }





        public Customer getSingleCustomerByCpr(String cprNr) {
        return customerRepository.getCustomerFromCprNr(cprNr);
    }


}
