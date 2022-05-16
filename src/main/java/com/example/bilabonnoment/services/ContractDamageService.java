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

        int previousContractId = -1;
        for (Damage damage : damageRepository.getAllEntities()){
            int currentContractId = damage.getContractId();

            if(currentContractId == previousContractId){
                damagesToContract.add(damage);
            }else if(previousContractId == -1){
                damagesToContract.add(damage);
            } else {
                ContractWithDamages.put(contractRepository.getSingleById(currentContractId), damagesToContract);
                damagesToContract.clear();
            }
            previousContractId = currentContractId;
        }



/*
        int currentContractId = -1;
        for (Damage damage : damageRepository.getAllEntities()){
            currentContractId = damage.getContractId();
            if(currentContractId == damage.getContractId()){
                damagesToContract.add(damage);
            }else if (currentContractId != -1){
                ContractWithDamages.put(contractRepository.getSingleById(currentContractId), damagesToContract);
                damagesToContract.clear();
            }
        }*/
        return ContractWithDamages;
    }

    public static void main(String[] args) {
        ContractDamageService contractDamageService = new ContractDamageService();

        HashMap<Contract, ArrayList<Damage>> contractsWithDamages = contractDamageService.contractsFromDamageList();

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
