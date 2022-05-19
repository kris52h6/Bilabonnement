package com.example.bilabonnoment.services;

import com.example.bilabonnoment.repositories.ContractTestRepository;
import com.example.bilabonnoment.repositories.CustomerTestRepository;
import com.example.bilabonnoment.repositories.DamageTestRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetCprNrFromContractId
{

    @Test
    void getCprNrFromContractId()
    {
        DamageTestRepository damageTestRepository = new DamageTestRepository();
        ContractTestRepository contractTestRepository = new ContractTestRepository();
        ContractDamageService contractDamageService = new ContractDamageService(damageTestRepository, contractTestRepository);

        String expectedCpr1 = "12";
        String expectedCpr2 = null;

        String actualCpr1 = contractDamageService.getCprNrFromContractId(1);
        String actualCpr2 = contractDamageService.getCprNrFromContractId(5);

        assertEquals(expectedCpr1, actualCpr1);
        assertEquals(expectedCpr2, actualCpr2);

    }
}