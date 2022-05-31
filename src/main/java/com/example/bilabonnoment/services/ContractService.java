package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.repositories.ContractRepository;
import com.example.bilabonnoment.repositories.interfaces.IContractRepository;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

/**
 * @author Kristian, Oliver
 */
public class ContractService {
    private final IContractRepository contractRepository;

    public ContractService(IContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public boolean editContract(WebRequest dataFromForm) {
        Contract contract = contractRepository.createTempContractObj(dataFromForm);
        if (contract != null) {
            contractRepository.editContract(contract);
            return true;
        }
        return false;
    }

    public String getCprNumFromContractId(int contractId) {
        return contractRepository.getCprNumFromContractId(contractId);
    }

    public List<Contract> getAllReturnedUncheckedContracts() {
        return contractRepository.getAllReturnedUncheckedContracts();
    }

    public List<Contract> getAllReturnedDamagedContracts() {
        return contractRepository.getAllReturnedDamagedContracts();
    }

    public void changeContractDamage(int contractId, String updatedDamageStatus) {
        contractRepository.changeContractDamage(contractId, updatedDamageStatus);
    }

    public Contract getSingleById(int id) {
        return contractRepository.getSingleById(id);
    }

    public List<Contract> getAllEntities() {
        return contractRepository.getAllEntities();
    }

    public boolean createContract(WebRequest data) {
        Contract contract = contractRepository.createContract(data);
        if (contract != null) {
            create(contract);
            return true;
        }
        return false;
    }

    public void create(Contract contract) {
        contractRepository.create(contract);
    }

}
