package com.example.bilabonnoment.controllers;

import org.springframework.stereotype.Controller;
import com.example.bilabonnoment.repositories.ContractRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContractController {

    private final ContractRepository contractRepository = new ContractRepository();

    @GetMapping("/contract")
    public String getContract(@RequestParam int id, Model model) {
        model.addAttribute("contracts", contractRepository.getSingleById(id));
        System.out.println(contractRepository.getSingleById(id));
        return "contract";
    }

    @GetMapping("/allContracts")
    public String allContracts(Model model){
        model.addAttribute("contracts", contractRepository.getAllEntities());
        return "allContracts";
    }
}
