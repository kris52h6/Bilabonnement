package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.models.Customer;
import com.example.bilabonnoment.models.Damage;
import com.example.bilabonnoment.repositories.ContractRepository;
import com.example.bilabonnoment.repositories.CustomerRepository;
import com.example.bilabonnoment.repositories.ICustomerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerContractService {
    public HashMap<Customer, ArrayList<Contract>> allCustomersContracts(){

        CustomerRepository customerRepository = new CustomerRepository();
        ContractRepository contractRepository = new ContractRepository();

        String previousCustomerCprNr = null;
        String currentCustomerCprNr = "";
        ArrayList<Contract> allContracts = new ArrayList<>();
        for (Customer customer : customerRepository.getAllEntities()) {
            currentCustomerCprNr = customer.getCPR();

            if (!currentCustomerCprNr.equals(previousCustomerCprNr)) {
                allContracts = (ArrayList<Contract>) contractRepository.getAllContractsFromCustomerCprNr(currentCustomerCprNr);
                allCustomersContracts().put(customer, allContracts);
                allContracts = new ArrayList<>();
            }
            previousCustomerCprNr = currentCustomerCprNr;
        }
        return allCustomersContracts();
    }


    public static void main(String[] args) {
        CustomerContractService customerContractService = new CustomerContractService();
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
