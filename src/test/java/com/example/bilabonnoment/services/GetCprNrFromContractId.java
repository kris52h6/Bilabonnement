package com.example.bilabonnoment.services;

import org.junit.jupiter.api.Test;
import com.example.bilabonnoment.repositories.ContractTestRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * @author Alexander
 * */

class GetCprNrFromContractId {

    @Test
    void getCprNrFromContractId() {
        //arrange
        ContractTestRepository contractTestRepository = new ContractTestRepository();
        ContractService contractService = new ContractService(contractTestRepository);

        String expectedCpr1 = "12";
        String expectedCpr2 = null;

        //act
        String actualCpr1 = contractService.getCprNumFromContractId(1);
        String actualCpr2 = contractService.getCprNumFromContractId(100);

        //assert
        assertEquals(expectedCpr1, actualCpr1);
        assertEquals(expectedCpr2, actualCpr2);
    }
}

