package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.repositories.interfaces.IContractRepository;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

public class ContractService {
    private final IContractRepository contractRepository;

    public ContractService(IContractRepository repo) {
        this.contractRepository = repo;
    }

    public Contract getSingleContractById(int contractId) {
        return contractRepository.getSingleById(contractId);
    }

    public void editContract(WebRequest dataFromForm) {
        Contract contract = contractRepository.createTempContractObj(dataFromForm);
        contractRepository.editContract(contract);
    }

    public String getCprNrFromContractId(int contractId) {
        return contractRepository.getCprNrFromContractId(contractId);
    }

    public List<Contract> getAllReturnedUncheckedContracts() {
        return contractRepository.getAllReturnedUncheckedContracts();
    }

    public List<Contract> getAllReturnedDamagedContracts() {
        return contractRepository.getAllReturnedDamagedContracts();
    }

    public int changeContractDamage(int contractId, String updatedDamageStatus) {
        return contractRepository.changeContractDamage(contractId, updatedDamageStatus);
    }

    public Contract getSingleById(int id) {
        return contractRepository.getSingleById(id);
    }

    public List<Contract> getAllEntities() {
        return contractRepository.getAllEntities();
    }

    public void createContract(WebRequest data) {
        Contract contract = contractRepository.createContract(data);
        create(contract);
    }

    public void create(Contract contract) {
        contractRepository.create(contract);
    }

    }
