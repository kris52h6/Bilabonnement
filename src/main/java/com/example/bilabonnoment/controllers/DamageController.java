package com.example.bilabonnoment.controllers;

import com.example.bilabonnoment.models.Customer;
import com.example.bilabonnoment.models.Damage;
import com.example.bilabonnoment.repositories.ContractRepository;
import com.example.bilabonnoment.repositories.CustomerRepository;
import com.example.bilabonnoment.repositories.DamageRepository;
import com.example.bilabonnoment.repositories.IDamageRepository;
import com.example.bilabonnoment.services.ContractCustomerService;
import com.example.bilabonnoment.services.ContractDamageService;
import com.example.bilabonnoment.services.CustomerContractService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class DamageController {
    private final DamageRepository damageRepository = new DamageRepository();
    private final ContractRepository contractRepository = new ContractRepository();
    private final CustomerRepository customerRepository = new CustomerRepository();

    @GetMapping("/damageReport")
    public String damageReport(@RequestParam int id, Model model) {
        ContractDamageService contractDamageService = new ContractDamageService(damageRepository, contractRepository);
        CustomerContractService customerContractService = new CustomerContractService(customerRepository, contractRepository);
        String cprNr = contractDamageService.getCprNrFromContractId(id);
        model.addAttribute("customer", customerContractService.getSingleCustomerByCpr(cprNr));
        /*model.addAttribute("damages",contractDamageService.getAllDamagesFromContract(id));*/
        model.addAttribute("contract", contractDamageService.contractWithDamage(id));
        return "damageReport";
    }

    @GetMapping("/damageForm")
    public String contractForm(@RequestParam int contractId){
        return "create-damage";
    }

    @PostMapping("/createDamage")
    public String createDamage(WebRequest dataFromForm) {
        damageRepository.createDamage(dataFromForm);
        return "redirect:/allContracts";
    }

    @GetMapping("/deleteDamage")
    public String deleteDamage(@RequestParam int contractId, int damageId){
        System.out.println("JEg er her");
        damageRepository.deleteDamage(damageId);
        return "redirect:/damageReport?id=" + contractId;
    }

}
