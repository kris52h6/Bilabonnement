package com.example.bilabonnoment.controllers;

import com.example.bilabonnoment.models.Contract;
import org.springframework.stereotype.Controller;
import com.example.bilabonnoment.repositories.ContractRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

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



    @GetMapping("/createNewContract")
    public String creatNewContract(){
        ContractRepository contractRepository = new ContractRepository();
        Contract.Damage damage = Contract.Damage.YES;
        Date date = new Date(System.currentTimeMillis());
        Contract contract = new Contract(10, "123", 124, 10.0, "abc", "abc", date, date, true, damage);
        contractRepository.create(contract);
        return "ok";
    }
}
