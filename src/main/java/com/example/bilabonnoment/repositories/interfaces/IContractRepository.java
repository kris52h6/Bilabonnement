package com.example.bilabonnoment.repositories.interfaces;

import com.example.bilabonnoment.models.Contract;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

public interface IContractRepository extends IRepository<Contract> {
    List<Contract> getAllContractsFromCustomerCprNum(String cprNum);

    String getCprNumFromContractId(int contractId);

    Contract createContract(WebRequest data);

    int changeContractDamage(int contractId, String updatedDamageStatus);

    List<Contract> getAllReturnedUncheckedContracts();

    List<Contract> getAllReturnedDamagedContracts();

    boolean editContract(Contract contract);

    Contract createTempContractObj(WebRequest data);
}
