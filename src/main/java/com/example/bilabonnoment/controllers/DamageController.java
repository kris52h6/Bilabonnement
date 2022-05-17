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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class DamageController {
    private final DamageRepository damageRepository = new DamageRepository();
    private final ContractRepository contractRepository = new ContractRepository();
    private final CustomerRepository customerRepository = new CustomerRepository();

    @GetMapping("/damageReport")
    public String damageReport(@RequestParam int id, @RequestParam String cpr, Model model) {
        ContractDamageService contractDamageService = new ContractDamageService(damageRepository, contractRepository);
        CustomerContractService customerContractService = new CustomerContractService(customerRepository, contractRepository);
        /*model.addAttribute("contract", id);*/
        model.addAttribute("customer", customerContractService.getSingleCustomerByCpr(cpr));
        model.addAttribute("damages", contractDamageService.contractWithDamage(id));
        return "damageReport";
    }

}
