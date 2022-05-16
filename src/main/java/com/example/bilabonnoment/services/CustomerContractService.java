package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.models.Customer;
import com.example.bilabonnoment.repositories.IRepository;

public class CustomerContractService {
    private final IRepository<Customer> customerRepo;
    private final IRepository<Contract> contractRepo;

    public CustomerContractService(IRepository<Customer> customerRepo, IRepository<Contract> contractRepo) {
        this.customerRepo = customerRepo;
        this.contractRepo = contractRepo;
    }


}
