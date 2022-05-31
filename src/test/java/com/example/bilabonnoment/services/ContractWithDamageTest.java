package com.example.bilabonnoment.services;

import com.example.bilabonnoment.repositories.ContractTestRepository;
import com.example.bilabonnoment.repositories.DamageTestRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Kristian
 */
class ContractWithDamageTest {
    DamageTestRepository damageTestRepository = new DamageTestRepository();
    ContractTestRepository contractTestRepository = new ContractTestRepository();
    ContractDamageService contractDamageService = new ContractDamageService(damageTestRepository, contractTestRepository);

    @Test
    void getAllDamagesFromContract() {
        // Arrange
        int expectedSizeContract1 = 2;
        int expectedSizeContract6 = 0;
        int expectedNonexistentContract = 0;

        // Act
        int actualSizeContract1 = contractDamageService.getAllDamagesFromContract(1).size();
        int actualSizeContract6 = contractDamageService.getAllDamagesFromContract(6).size();
        int actualNonExistentContract = contractDamageService.getAllDamagesFromContract(10).size();

        // Assert
        assertEquals(expectedSizeContract1, actualSizeContract1);
        assertEquals(expectedSizeContract6, actualSizeContract6);
        assertEquals(expectedNonexistentContract, actualNonExistentContract);
    }

}