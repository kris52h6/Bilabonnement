package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Customer;
import com.example.bilabonnoment.repositories.*;
import org.junit.jupiter.api.Test;
import repositories.ContractTestRepository;
import repositories.CustomerTestRepository;

import static org.junit.jupiter.api.Assertions.*;

class GetSingleCustomerByCpr
{

    @Test
    void getSingleCustomerByCpr()
    {
        //arrange
        CustomerTestRepository customerTestRepository = new CustomerTestRepository();
        ContractTestRepository contractTestRepository = new ContractTestRepository();
        CustomerContractService customerContractService = new CustomerContractService(customerTestRepository, contractTestRepository);

        Customer expectedCustomer1 = new Customer(1, "12", "hans", "hansen");


        //act
        Customer actualCustomer1 = customerContractService.getSingleCustomerByCpr("12");
        Customer nonExistentCustomer2 = customerContractService.getSingleCustomerByCpr("3");

        //assert
        assertEquals(expectedCustomer1.getId(), actualCustomer1.getId());
        assertNull(nonExistentCustomer2);
    }
}
