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

    public HashMap<Customer, ArrayList<Contract>> allCustomersContracts(){
        String previousCustomerCprNr = null;
        String currentCustomerCprNr;
        ArrayList<Contract> allContracts = new ArrayList<>();
        HashMap<Customer, ArrayList<Contract>> customersWithContracts = new HashMap<>();

        for (Customer customer : customerRepository.getAllEntities()) {

            currentCustomerCprNr = customer.getCPR();

            if (!currentCustomerCprNr.equals(previousCustomerCprNr)) {
                allContracts = (ArrayList<Contract>) contractRepository.getAllContractsFromCustomerCprNr(currentCustomerCprNr);
                customersWithContracts.put(customer, allContracts);
                allContracts = new ArrayList<>();
            }

            previousCustomerCprNr = currentCustomerCprNr;
        }
        return customersWithContracts;
    }

    public Customer getSingleCustomerByCpr(String cprNr) {
        return customerRepository.getCustomerFromCprNr(cprNr);
    }


    public static void main(String[] args) {

        CustomerRepository customerRepository = new CustomerRepository();
        ContractRepository contractRepository = new ContractRepository();

        CustomerContractService customerContractService = new CustomerContractService(customerRepository, contractRepository);
        HashMap<Customer, ArrayList<Contract>> allCustomersContracts = customerContractService.allCustomersContracts();


        // Iterating HashMap through for loop
        for (Map.Entry<Customer, ArrayList<Contract>> set :
                allCustomersContracts.entrySet()) {

            // Printing all elements of a Map
            String contracts = "";
            for (Contract contract : set.getValue()){
                contracts += contract.toString();
            }
            System.out.println(set.getKey().toString() + " Contracts: "
                    + contracts);
        }
    }


}
