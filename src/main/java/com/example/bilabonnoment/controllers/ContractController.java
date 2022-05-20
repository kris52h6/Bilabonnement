package com.example.bilabonnoment.controllers;

import org.springframework.stereotype.Controller;
import com.example.bilabonnoment.repositories.ContractRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

@Controller
public class ContractController {

    private final ContractRepository contractRepository = new ContractRepository();
    private final String AREA = "CONTRACT";

    @GetMapping("/contract")
    public String getContract(@RequestParam int id, Model model, HttpSession session) {
        if(session.getAttribute("userRole").equals(AREA)) {
            model.addAttribute("contracts", contractRepository.getSingleById(id));
            return "contract-templates/contract";
        }
        return "redirect:/error";
    }

    @GetMapping("/allContracts")
    public String allContracts(Model model, HttpSession session){
        if(session.getAttribute("userRole").equals(AREA)) {
            model.addAttribute("contracts", contractRepository.getAllEntities());
            return "contract-templates/all-contracts";
        }
        return "redirect:/error";
    }

    @GetMapping("/contractForm")
    public String contractForm(HttpSession session){
        if(session.getAttribute("userRole").equals(AREA)) {
            return "contract-templates/create-contract";
        }
        return "redirect:/error";
    }

    @PostMapping("/createContract")
    public String createContract(WebRequest dataFromForm, HttpSession session) {
        if(session.getAttribute("userRole").equals(AREA)) {
            contractRepository.createContract(dataFromForm);
            return "redirect:/allContracts";
        }
        return "redirect:/error";
    }
}
