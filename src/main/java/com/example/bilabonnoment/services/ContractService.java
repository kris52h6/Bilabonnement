package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.repositories.interfaces.IContractRepository;
import org.springframework.web.context.request.WebRequest;

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
}
