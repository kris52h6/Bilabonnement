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
        int contractId = Integer.parseInt(dataFromForm.getParameter("contractId"));
        Contract contract = contractRepository.getSingleById(contractId);
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

    }
