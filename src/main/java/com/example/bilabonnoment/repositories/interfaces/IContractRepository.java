package com.example.bilabonnoment.repositories.interfaces;

import com.example.bilabonnoment.models.Contract;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

public interface IContractRepository extends IRepository<Contract>{
    public List<Contract> getAllContractsFromCustomerCprNr(String cprNr);
    public String getCprNrFromContractId(int contractId);
    public void createContract(WebRequest data);
    public int changeContractDamage(int contractId, String updatedDamageStatus);
    public List<Contract> getAllReturnedUncheckedContracts();
    public List<Contract> getAllReturnedDamagedContracts();
    public boolean editContract(Contract contract);
    public Contract createTempContractObj(WebRequest data);
}
