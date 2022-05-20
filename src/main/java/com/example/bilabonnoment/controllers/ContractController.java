package com.example.bilabonnoment.controllers;

import com.example.bilabonnoment.services.ContractService;
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
        if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals(AREA)) {
            model.addAttribute("contracts", contractRepository.getSingleById(id));
            return "contract-templates/contract";
        }
        return "access-error";
    }

    @GetMapping("/allContracts")
    public String allContracts(Model model, HttpSession session){
        if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals(AREA)) {
            model.addAttribute("contracts", contractRepository.getAllEntities());
            return "contract-templates/all-contracts";
        }
        return "access-error";
    }

    @GetMapping("/contractForm")
    public String contractForm(HttpSession session){
        if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals(AREA)) {
            return "contract-templates/create-contract";
        }
        return "access-error";
    }

    @PostMapping("/createContract")
    public String createContract(WebRequest dataFromForm, HttpSession session) {
        if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals(AREA)) {
            contractRepository.createContract(dataFromForm);
            return "redirect:/allContracts";
        }
        return "access-error";
    }

    @GetMapping("/editContractForm")
    public String editContractForm(@RequestParam int contractId, Model model) {
        ContractService contractService = new ContractService(contractRepository);
        model.addAttribute("contract", contractService.getSingleContractById(contractId));
        return "contract-templates/edit-contract";
    }

    @PostMapping("/editContract")
    public String editContract(WebRequest dataFromForm) {
        ContractService contractService = new ContractService(contractRepository);
        String redirectId = dataFromForm.getParameter("contractId");
        contractService.editContract(dataFromForm);
        return "redirect:/contract?id=" + redirectId;
    }
}
