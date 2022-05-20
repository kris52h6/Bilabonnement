package com.example.bilabonnoment.repositories.interfaces;

import com.example.bilabonnoment.models.Customer;

public interface ICustomerRepository extends IRepository<Customer>{
    public Customer getCustomerFromCprNr(String cprNr);
}
