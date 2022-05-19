package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.models.Customer;
import com.example.bilabonnoment.repositories.*;
import org.junit.jupiter.api.Test;
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
        Customer expectedCustomer2 = new Customer(2, "34", "thor", "sørnsen");


        //act
        Customer actualCustomer1 = customerContractService.getSingleCustomerByCpr("12");
        Customer actualCustomer2 = customerContractService.getSingleCustomerByCpr("34");

        //assert
        assertSame(expectedCustomer1,actualCustomer1);
        assertEquals(expectedCustomer1, actualCustomer1);
    }
}