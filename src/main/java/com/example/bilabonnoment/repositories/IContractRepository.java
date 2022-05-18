package com.example.bilabonnoment.repositories;

import com.example.bilabonnoment.models.Contract;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

public interface IContractRepository extends IRepository<Contract>{
    public List<Contract> getAllContractsFromCustomerCprNr(String cprNr);
    public String getCprNrFromContractId(int contractId);
    public void createContract(WebRequest data);
}
