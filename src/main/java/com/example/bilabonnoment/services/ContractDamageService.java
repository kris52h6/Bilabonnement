package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.models.Damage;
import com.example.bilabonnoment.repositories.ContractRepository;
import com.example.bilabonnoment.repositories.DamageRepository;
import com.example.bilabonnoment.repositories.interfaces.IContractRepository;
import com.example.bilabonnoment.repositories.interfaces.IDamageRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ContractDamageService {

    private final IDamageRepository damageRepository;
    private final IContractRepository contractRepository;

    public ContractDamageService(IDamageRepository damageRepository, IContractRepository contractRepository) {
        this.damageRepository = damageRepository;
        this.contractRepository = contractRepository;
    }

    public HashMap<Contract, ArrayList<Damage>> contractsFromDamageList(){

        HashMap<Contract, ArrayList<Damage>> ContractWithDamages = new HashMap<>();
        ArrayList<Damage> damagesToContract = new ArrayList<>();

        int currentContractId = -1;
        int previousContractId = -2;
        for (Damage damage : damageRepository.getAllEntities()) {
            currentContractId = damage.getContractId();

            if (currentContractId != previousContractId) {
                damagesToContract = getAllDamagesFromContract(currentContractId);
                ContractWithDamages.put(contractRepository.getSingleById(currentContractId), damagesToContract);
            }
            previousContractId = currentContractId;
        }
        return ContractWithDamages;
    }

    public HashMap<Contract, ArrayList<Damage>> contractWithDamage(int contractId){
        HashMap<Contract, ArrayList<Damage>> contractWithDamages = new HashMap<>();
        contractWithDamages.put(contractRepository.getSingleById(contractId), getAllDamagesFromContract(contractId));
        return contractWithDamages;
    }

    public ArrayList<Damage> getAllDamagesFromContract(int id) {
        return damageRepository.getAllDamagesFromContract(id);
    }














        public static void main(String[] args) {
        DamageRepository damageRepository = new DamageRepository();
        ContractRepository contractRepository = new ContractRepository();
        ContractDamageService contractDamageService = new ContractDamageService(damageRepository, contractRepository);

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
