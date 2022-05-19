package com.example.bilabonnoment.controllers;

import com.example.bilabonnoment.repositories.ContractRepository;
import com.example.bilabonnoment.repositories.CustomerRepository;
import com.example.bilabonnoment.repositories.DamageRepository;
import com.example.bilabonnoment.services.ContractDamageService;
import com.example.bilabonnoment.services.CustomerContractService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

@Controller
public class DamageController {
    private final DamageRepository damageRepository = new DamageRepository();
    private final ContractRepository contractRepository = new ContractRepository();
    private final CustomerRepository customerRepository = new CustomerRepository();


    @GetMapping("damageIndex")
    public String damageIndex(Model model){
        CustomerContractService customerContractService = new CustomerContractService(customerRepository, contractRepository);
        ContractDamageService contractDamageService = new ContractDamageService(damageRepository, contractRepository);

        model.addAttribute("uncheckedContracts",contractDamageService.getAllReturnedUncheckedContracts());
        model.addAttribute("damagedContracts", contractDamageService.getAllReturnedDamagedContracts());

        return "damageIndex";
    }

    @GetMapping("/damageReport")
    public String damageReport(@RequestParam int id, Model model) {
        ContractDamageService contractDamageService = new ContractDamageService(damageRepository, contractRepository);
        CustomerContractService customerContractService = new CustomerContractService(customerRepository, contractRepository);
        String cprNr = contractDamageService.getCprNrFromContractId(id);

        model.addAttribute("customer", customerContractService.getSingleCustomerByCpr(cprNr));
        model.addAttribute("contract", contractDamageService.contractWithDamage(id));
        return "damage-report";
    }

    @GetMapping("/damageForm")
    public String contractForm(@RequestParam int contractId){
        return "create-damage";
    }

    @PostMapping("/createDamage")
    public String createDamage(WebRequest dataFromForm) {
        String redirectId = dataFromForm.getParameter("contractId");
        damageRepository.createDamage(dataFromForm);
            return "redirect:/damageReport?id=" + redirectId;
    }

    @GetMapping("/deleteDamage")
    public String deleteDamage(@RequestParam int contractId, @RequestParam int damageId){
        damageRepository.deleteDamage(damageId);
        return "redirect:/damageReport?id=" + contractId;
    }

    @GetMapping("/editDamageForm")
        public String editForm(@RequestParam int damageId, Model model) {
        ContractDamageService contractDamageService = new ContractDamageService(damageRepository, contractRepository);
        model.addAttribute("damage", contractDamageService.getSingleDamageById(damageId));
        return "edit-damage";
    }


    @PostMapping("/editDamage")
        public String editDamage(WebRequest dataFromForm) {
        ContractDamageService contractDamageService = new ContractDamageService(damageRepository, contractRepository);
        String redirectId = dataFromForm.getParameter("contractId");
        contractDamageService.editDamage(dataFromForm);

        return "redirect:/damageReport?id=" + redirectId;
    }


}
