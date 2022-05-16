package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.models.Damage;
import com.example.bilabonnoment.repositories.ContractRepository;
import com.example.bilabonnoment.repositories.DamageRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContractDamageService {
    public HashMap<Contract, ArrayList<Damage>> contractsFromDamageList(){

        DamageRepository damageRepository = new DamageRepository();
        ContractRepository contractRepository = new ContractRepository();

        HashMap<Contract, ArrayList<Damage>> ContractWithDamages = new HashMap<>();
        ArrayList<Damage> damagesToContract = new ArrayList<>();

        int currentContractId = -1;
        int previousContractId = -2;
        for (Damage damage : damageRepository.getAllEntities()) {
            currentContractId = damage.getContractId();

            if (currentContractId != previousContractId) {
                damagesToContract = getAllDamagesFromContract(currentContractId);
                ContractWithDamages.put(contractRepository.getSingleById(currentContractId), damagesToContract);
                damagesToContract = new ArrayList<>();
            }

            previousContractId = currentContractId;

        }

        return ContractWithDamages;
    }

    public ArrayList<Damage> getAllDamagesFromContract(int id) {
        DamageRepository damageRepository = new DamageRepository();
        return (ArrayList<Damage>) damageRepository.getAllDamagesFromContract(id);
    }

    public static void main(String[] args) {
        ContractDamageService contractDamageService = new ContractDamageService();

        HashMap<Contract, ArrayList<Damage>> contractsWithDamages = contractDamageService.contractsFromDamageList();

        System.out.println(contractsWithDamages.size());

        // Iterating HashMap through for loop
        for (Map.Entry<Contract, ArrayList<Damage>> set :
                contractsWithDamages.entrySet()) {

            // Printing all elements of a Map
            String damages = "";
            for (Damage damage : set.getValue()){
                damages += damage.toString();
            }
            System.out.println(set.getKey().toString() + " Damages: "
                    + damages);
        }
    }
}
