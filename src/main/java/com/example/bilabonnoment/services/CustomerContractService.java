package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.models.Customer;
import com.example.bilabonnoment.repositories.ContractRepository;
import com.example.bilabonnoment.repositories.CustomerRepository;
import com.example.bilabonnoment.repositories.interfaces.IContractRepository;
import com.example.bilabonnoment.repositories.interfaces.ICustomerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerContractService {
    private final ICustomerRepository customerRepository;
    private final IContractRepository contractRepository;

    public CustomerContractService(ICustomerRepository customerRepository, IContractRepository contractRepository) {
        this.customerRepository = customerRepository;
        this.contractRepository = contractRepository;
    }

    public HashMap<Customer, ArrayList<Contract>> allCustomersContracts() {
        String previousCustomerCprNum = null;
        String currentCustomerCprNum;
        ArrayList<Contract> allContracts = new ArrayList<>();
        HashMap<Customer, ArrayList<Contract>> customersWithContracts = new HashMap<>();

        for (Customer customer : customerRepository.getAllEntities()) {

            currentCustomerCprNum = customer.getCprNum();

            if (!currentCustomerCprNum.equals(previousCustomerCprNum)) {
                allContracts = (ArrayList<Contract>) contractRepository.getAllContractsFromCustomerCprNum(currentCustomerCprNum);
                customersWithContracts.put(customer, allContracts);
                allContracts = new ArrayList<>();
            }

            previousCustomerCprNum = currentCustomerCprNum;
        }
        return customersWithContracts;
    }


}
