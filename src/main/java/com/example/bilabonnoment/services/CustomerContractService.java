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

    CustomerRepository customerRepository = new CustomerRepository();
    ContractRepository contractRepository = new ContractRepository();

    List<Contract> allContracts = new ArrayList<>();
    List<Customer> allCustomers = new ArrayList<>();

    public HashMap<Customer, ArrayList<Contract>> allCustomersContracts(){
        /*customerRepository.getAllEntities();
        String previousCustomerCprNr = null;
        String currentCustomerCprNr = "";

        for (Customer customer : allCustomers) {
            currentCustomerCprNr = customer.getCPR();

            if (!currentCustomerCprNr.equals(previousCustomerCprNr)) {
                allContracts = contractRepository.getAllContractsFromCustomerCprNr(currentCustomerCprNr);
                //allContracts = new ArrayList<>();
                allContracts.add(contractRepository.getAllContractsFromCustomerCprNr(currentCustomerCprNr))
            }

            previousCustomerCprNr = currentCustomerCprNr;
            allCustomersContracts().put(customer, (ArrayList<Contract>) allContracts);
        }
        return allCustomersContracts();*/
        return null;
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
