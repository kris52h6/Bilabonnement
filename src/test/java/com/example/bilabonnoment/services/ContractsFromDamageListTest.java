package com.example.bilabonnoment.services;

import com.example.bilabonnoment.repositories.*;
import org.junit.jupiter.api.Test;
import repositories.ContractTestRepository;
import repositories.CustomerTestRepository;
import repositories.DamageTestRepository;

import static org.junit.jupiter.api.Assertions.*;

class ContractsFromDamageListTest
{

    @Test
    void contractsFromDamageList()
    {
        DamageTestRepository damageTestRepository = new DamageTestRepository();
        ContractTestRepository contractTestRepository = new ContractTestRepository();
        ContractDamageService contractDamageService = new ContractDamageService(damageTestRepository, contractTestRepository);


    }
}