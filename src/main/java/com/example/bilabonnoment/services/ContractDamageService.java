package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.models.Damage;
import com.example.bilabonnoment.repositories.ContractRepository;
import com.example.bilabonnoment.repositories.DamageRepository;
import com.example.bilabonnoment.repositories.IContractRepository;
import com.example.bilabonnoment.repositories.IDamageRepository;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        ContractRepository contractRepository = new ContractRepository();
        HashMap<Contract, ArrayList<Damage>> contractWithDamages = new HashMap<>();
        contractWithDamages.put(contractRepository.getSingleById(contractId), getAllDamagesFromContract(contractId));
        return contractWithDamages;
    }

    public ArrayList<Damage> getAllDamagesFromContract(int id) {
        return damageRepository.getAllDamagesFromContract(id);
    }

    public Damage getSingleDamageById(int damageId) {
        return damageRepository.getSingleById(damageId);
    }

    public void editDamage(WebRequest dataFromForm) {
        damageRepository.createTempDamageObj(dataFromForm);
    }

    public String getCprNrFromContractId(int contractId) {
        return contractRepository.getCprNrFromContractId(contractId);
    }

    public List<Contract> getAllReturnedUncheckedContracts(){
        return contractRepository.getAllReturnedUncheckedContracts();
    }
    public List<Contract> getAllReturnedDamagedContracts() {
        return contractRepository.getAllReturnedDamagedContracts();
    }

    public int changeContractDamage(int contractId, String updatedDamageStatus) {
        return contractRepository.changeContractDamage(contractId, updatedDamageStatus);
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
