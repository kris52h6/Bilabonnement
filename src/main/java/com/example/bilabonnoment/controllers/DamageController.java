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

                return "damageIndex";
            }
            return "access-error";
    }

    @GetMapping("/updateContractDamage")
    public String updatedContractDamage(@RequestParam int contractId, @RequestParam String updatedDamageStatus, HttpSession session) {
        if(session.getAttribute("userRole").equals(AREA)) {
            if (session.getAttribute("userRole").equals(AREA)) {
                contractRepository.changeContractDamage(contractId, updatedDamageStatus);
                return "redirect:/damageIndex";
            }
        }
        return "redirect:/access-error";
    }


    @GetMapping("/damageReport")
    public String damageReport(@RequestParam int id, Model model, HttpSession session) {
        if(session.getAttribute("userRole").equals(AREA)) {
            ContractDamageService contractDamageService = new ContractDamageService(damageRepository, contractRepository);
            CustomerContractService customerContractService = new CustomerContractService(customerRepository, contractRepository);
            String cprNr = contractDamageService.getCprNrFromContractId(id);

            model.addAttribute("customer", customerContractService.getSingleCustomerByCpr(cprNr));
            model.addAttribute("contract", contractDamageService.contractWithDamage(id));
            return "damage-report";
        }
        return "redirect:/access-error";
    }

    @GetMapping("/damageForm")
    public String contractForm(@RequestParam int contractId, HttpSession session){
        if(session.getAttribute("userRole").equals(AREA)) {
            return "create-damage";
        }
        return "redirect:/access-error";
    }

    @PostMapping("/createDamage")
    public String createDamage(WebRequest dataFromForm, HttpSession session) {
        if(session.getAttribute("userRole").equals(AREA)) {
            String redirectId = dataFromForm.getParameter("contractId");
            damageRepository.createDamage(dataFromForm);
            return "redirect:/damageReport?id=" + redirectId;
        }
        return "redirect:/access-error";
    }

    @GetMapping("/deleteDamage")
    public String deleteDamage(@RequestParam int contractId, @RequestParam int damageId, HttpSession session){
        if(session.getAttribute("userRole").equals(AREA)) {
            damageRepository.deleteDamage(damageId);
            return "redirect:/damageReport?id=" + contractId;
        }
        return "redirect:/access-error";
    }

    @GetMapping("/editDamageForm")
        public String editForm(@RequestParam int damageId, Model model, HttpSession session) {
        if(session.getAttribute("userRole").equals(AREA)) {
            ContractDamageService contractDamageService = new ContractDamageService(damageRepository, contractRepository);
            model.addAttribute("damage", contractDamageService.getSingleDamageById(damageId));
            return "edit-damage";
        }
        return "redirect:/access-error";
    }


    @PostMapping("/editDamage")
        public String editDamage(WebRequest dataFromForm, HttpSession session) {
        if(session.getAttribute("userRole").equals(AREA)) {
            ContractDamageService contractDamageService = new ContractDamageService(damageRepository, contractRepository);
            String redirectId = dataFromForm.getParameter("contractId");
            contractDamageService.editDamage(dataFromForm);
            return "redirect:/damageReport?id=" + redirectId;
        }
        return "redirect:/access-error";
    }
}
