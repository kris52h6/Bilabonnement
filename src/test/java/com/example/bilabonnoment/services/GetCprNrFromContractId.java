
package com.example.bilabonnoment.services;

import com.example.bilabonnoment.repositories.*;
import org.junit.jupiter.api.Test;
import repositories.ContractTestRepository;
import repositories.DamageTestRepository;

import static org.junit.jupiter.api.Assertions.*;

class GetCprNrFromContractId
{

    @Test
    void getCprNrFromContractId()
    {
        ContractTestRepository contractTestRepository = new ContractTestRepository();
        ContractService contractService = new ContractService(contractTestRepository);

        String expectedCpr1 = "12";
        String expectedCpr2 = null;

        String actualCpr1 = contractService.getCprNrFromContractId(1);
        String actualCpr2 = contractService.getCprNrFromContractId(100);

        assertEquals(expectedCpr1, actualCpr1);
        assertEquals(expectedCpr2, actualCpr2);

    }
}

