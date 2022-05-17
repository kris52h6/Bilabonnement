package com.example.bilabonnoment.controllers;

import com.example.bilabonnoment.models.Contract;
import org.springframework.stereotype.Controller;
import com.example.bilabonnoment.repositories.ContractRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import java.sql.Date;

@Controller
public class ContractController {

    private final ContractRepository contractRepository = new ContractRepository();

    @GetMapping("/contract")
    public String getContract(@RequestParam int id, Model model) {
        model.addAttribute("contracts", contractRepository.getSingleById(id));
        return "contract";
    }

    @GetMapping("/allContracts")
    public String allContracts(Model model){
        model.addAttribute("contracts", contractRepository.getAllEntities());
        return "allContracts";
    }

    @GetMapping("/contractForm")
    public String contractForm(){
        return "create-contract";
    }

    @PostMapping("/createContract")
    public String createContract(WebRequest dataFromForm) {
        contractRepository.createContract(dataFromForm);
        return "redirect:/allContracts";
    }




}
