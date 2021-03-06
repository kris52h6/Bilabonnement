package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Car;
import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.repositories.BusinessRepository;
import com.example.bilabonnoment.repositories.ContractRepository;
import com.example.bilabonnoment.repositories.interfaces.IBusinessRepository;
import com.example.bilabonnoment.repositories.interfaces.IContractRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Oliver
 */
public class BusinessContractService {
    private final IContractRepository contractRepository;
    private final IBusinessRepository businessRepository;


    public BusinessContractService(IContractRepository contractRepository, IBusinessRepository businessRepository) {
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
                Car car = businessRepository.getCarFromVinNo(endedContracts.getVin());
                businessRepository.editCarLeasingStatus(car.getId(), false);
            }else {
                isLeased = true;
                Car car = businessRepository.getCarFromVinNo(endedContracts.getVin());
                businessRepository.editCarLeasingStatus(car.getId(), true);
            }
        }
    }

}
