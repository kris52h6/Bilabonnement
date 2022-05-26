package com.example.bilabonnoment.services;

import com.example.bilabonnoment.repositories.BusinessRepository;
import org.junit.jupiter.api.Test;

class TotalValueOfAllRentedCars {

    @Test
    void totalValueOfAllRentedCars() {
        BusinessRepository businessRepository = new BusinessRepository();
        BusinessService businessService = new BusinessService(businessRepository);


    }
}

