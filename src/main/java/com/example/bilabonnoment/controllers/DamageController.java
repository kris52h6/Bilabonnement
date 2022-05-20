package com.example.bilabonnoment.controllers;

import com.example.bilabonnoment.repositories.ContractRepository;
import com.example.bilabonnoment.repositories.CustomerRepository;
import com.example.bilabonnoment.repositories.DamageRepository;
import com.example.bilabonnoment.services.ContractDamageService;
import com.example.bilabonnoment.services.CustomerContractService;
import com.example.bilabonnoment.services.CustomerService;
import com.example.bilabonnoment.services.DamageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

@Controller
public class DamageController {
    private final DamageRepository damageRepository = new DamageRepository();
    private final ContractRepository contractRepository = new ContractRepository();
    private final CustomerRepository customerRepository = new CustomerRepository();
    private final String AREA = "DAMAGE";


    @GetMapping("/damageIndex")
    public String damageIndex(Model model, HttpSession session){
        if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals(AREA)) {
                CustomerContractService customerContractService = new CustomerContractService(customerRepository, contractRepository);
                ContractDamageService contractDamageService = new ContractDamageService(damageRepository, contractRepository);

                model.addAttribute("uncheckedContracts", contractDamageService.getAllReturnedUncheckedContracts());
                model.addAttribute("damagedContracts", contractDamageService.getAllReturnedDamagedContracts());

                return "damage-templates/damage-index";
            }
            return "access-error";
    }

    @GetMapping("/updateContractDamage")
    public String updatedContractDamage(@RequestParam int contractId, @RequestParam String updatedDamageStatus, HttpSession session) {
        if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals(AREA)) {
            if (session.getAttribute("userRole").equals(AREA)) {
                contractRepository.changeContractDamage(contractId, updatedDamageStatus);
                return "redirect:/damageIndex";
            }
        }
        return "access-error";
    }


    @GetMapping("/damageReport")
    public String damageReport(@RequestParam int id, Model model, HttpSession session) {
        if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals(AREA)) {
            ContractDamageService contractDamageService = new ContractDamageService(damageRepository, contractRepository);
            CustomerService customerService = new CustomerService(customerRepository);
            String cprNr = contractDamageService.getCprNrFromContractId(id);

            model.addAttribute("customer", customerService.getSingleCustomerByCpr(cprNr));
            model.addAttribute("contract", contractDamageService.contractWithDamage(id));
            return "damage-templates/damage-report";
        }
        return "access-error";
    }

    @GetMapping("/damageForm")
    public String contractForm(@RequestParam int contractId, HttpSession session){
        if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals(AREA)) {
            return "damage-templates/create-damage";
        }
        return "access-error";
    }

    @PostMapping("/createDamage")
    public String createDamage(WebRequest dataFromForm, HttpSession session) {
        if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals(AREA)) {

            DamageService damageService = new DamageService(damageRepository);
            String redirectId = dataFromForm.getParameter("contractId");
            damageService.createDamage(dataFromForm);
            return "redirect:/damageReport?id=" + redirectId;

        }
        return "access-error";
    }

    @GetMapping("/deleteDamage")
    public String deleteDamage(@RequestParam int contractId, @RequestParam int damageId, HttpSession session){
        if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals(AREA)) {
            DamageService damageService = new DamageService(damageRepository);
            damageService.deleteDamage(damageId);
            return "redirect:/damageReport?id=" + contractId;
        }
        return "access-error";
    }

    @GetMapping("/editDamageForm")
        public String editForm(@RequestParam int damageId, Model model, HttpSession session) {
        if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals(AREA)) {

            DamageService damageService = new DamageService(damageRepository);
            model.addAttribute("damage", damageService.getSingleDamageById(damageId));
            return "damage-templates/edit-damage";
        }
        return "access-error";
    }

    @PostMapping("/editDamage")
        public String editDamage(WebRequest dataFromForm, HttpSession session) {
        if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals(AREA)) {
            DamageService damageService = new DamageService(damageRepository);
            String redirectId = dataFromForm.getParameter("contractId");
            damageService.editDamage(dataFromForm);
            return "redirect:/damageReport?id=" + redirectId;
        }
        return "access-error";
    }
}
