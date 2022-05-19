package com.example.bilabonnoment.services;

import com.example.bilabonnoment.repositories.*;
import org.junit.jupiter.api.Test;
import repositories.ContractTestRepository;
import repositories.CustomerTestRepository;

import static org.junit.jupiter.api.Assertions.*;

class contractsFromDamageListTest
{

    @Test
    void contractsFromDamageList()
    {
        CustomerTestRepository customerTestRepository = new CustomerTestRepository();
        ContractTestRepository contractTestRepository = new ContractTestRepository();
        CustomerContractService customerContractService = new CustomerContractService(customerTestRepository, contractTestRepository);

    }
}