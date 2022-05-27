package com.example.bilabonnoment.controllers;

import com.example.bilabonnoment.repositories.BusinessRepository;
import com.example.bilabonnoment.repositories.ContractRepository;
import com.example.bilabonnoment.repositories.CustomerRepository;
import com.example.bilabonnoment.services.ContractService;
import com.example.bilabonnoment.services.CustomerBusinessService;
import com.example.bilabonnoment.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

@Controller
public class ContractController {

    private final ContractRepository contractRepository = new ContractRepository();
    private final ContractService contractService = new ContractService(contractRepository);

    private final String AREA = "CONTRACT";

    @GetMapping("/contract")
    public String getContract(@RequestParam int id, Model model, HttpSession session) {
        if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals(AREA)) {
            model.addAttribute("contracts", contractService.getSingleById(id));
            return "contract-templates/contract";
        }
        return "access-error";
    }

    @GetMapping("/allContracts")
    public String allContracts(Model model, HttpSession session) {
        if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals(AREA)) {
            model.addAttribute("contracts", contractService.getAllEntities());
            return "contract-templates/all-contracts";
        }
        return "access-error";
    }

    @GetMapping("/contractForm")
    public String contractForm(Model model, HttpSession session) {
        if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals(AREA)) {
            CustomerRepository customerRepository = new CustomerRepository();
            BusinessRepository businessRepository = new BusinessRepository();
            CustomerBusinessService customerBusinessService = new CustomerBusinessService(customerRepository, businessRepository);
            model.addAttribute("customers", customerBusinessService.getAllCustomers());
            model.addAttribute("cars", customerBusinessService.getAllAvailableCars());
            return "contract-templates/create-contract";
        }
        return "access-error";
    }

    @PostMapping("/createContract")
    public String createContract(WebRequest dataFromForm, HttpSession session) {
        if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals(AREA)) {
            boolean createResult = contractService.createContract(dataFromForm);
            if (createResult) {
                return "redirect:/allContracts";
            } else {
                return "common-error";
            }
        }
        return "access-error";
    }

    @GetMapping("/editContractForm")
    public String editContractForm(@RequestParam int contractId, Model model, HttpSession session) {
        if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals(AREA)) {
            model.addAttribute("contract", contractService.getSingleById(contractId));
            return "contract-templates/edit-contract";
        }
        return "access-error";
    }

    @PostMapping("/editContract")
    public String editContract(WebRequest dataFromForm, HttpSession session) {
        if (session.getAttribute("userRole") != null && session.getAttribute("userRole").equals(AREA)) {
            String redirectId = dataFromForm.getParameter("contractId");
            boolean editResult = contractService.editContract(dataFromForm);
            if (editResult) {
                return "redirect:/contract?id=" + redirectId;
            } else {
                return "common-error";
            }
        }
        return "access-error";
    }
}
