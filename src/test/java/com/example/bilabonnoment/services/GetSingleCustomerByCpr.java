package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Customer;
import org.junit.jupiter.api.Test;
import com.example.bilabonnoment.repositories.CustomerTestRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GetSingleCustomerByCpr {

    @Test
    void getSingleCustomerByCpr() {
        //arrange
        CustomerTestRepository customerTestRepository = new CustomerTestRepository();
        CustomerService customerService = new CustomerService(customerTestRepository);

        Customer expectedCustomer1 = new Customer(1, "12", "hans", "hansen");

        //act
        Customer actualCustomer1 = customerService.getCustomerFromCprNum("12");
        Customer nonExistentCustomer2 = customerService.getCustomerFromCprNum("3");

        //assert
        assertEquals(expectedCustomer1.getId(), actualCustomer1.getId());
        assertNull(nonExistentCustomer2);
    }
}
