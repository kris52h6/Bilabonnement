package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Car;
import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.repositories.BusinessRepository;
import com.example.bilabonnoment.repositories.ContractRepository;

import java.util.ArrayList;
import java.util.List;

public class BusinessContractService {
    private final ContractRepository contractRepository;
    private final BusinessRepository businessRepository;


    public BusinessContractService(ContractRepository contractRepository, BusinessRepository businessRepository) {
        this.contractRepository = contractRepository;
        this.businessRepository = businessRepository;
    }

    public void updateLeasedStatus(){
        List<Contract> allContracts = contractRepository.getAllEntities();
        List<Car> allCars = businessRepository.getAllRentedCars();

        int contractId = -1;
        boolean isLeased = false;

        for (Contract endedContracts : allContracts){
            contractId = endedContracts.getId();
            if(endedContracts.isReturned()){
                Car car = businessRepository.getCarFromVinNo(endedContracts.getVinNo());
                businessRepository.editCarLeasingStatus(car.getId(), false);
            }else {
                isLeased = true;
                Car car = businessRepository.getCarFromVinNo(endedContracts.getVinNo());
                businessRepository.editCarLeasingStatus(car.getId(), true);
            }
        }
    }

}
