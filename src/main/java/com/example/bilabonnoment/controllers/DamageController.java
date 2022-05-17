package com.example.bilabonnoment.controllers;

import com.example.bilabonnoment.models.Damage;
import com.example.bilabonnoment.repositories.DamageRepository;
import com.example.bilabonnoment.services.ContractCustomerService;
import com.example.bilabonnoment.services.ContractDamageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class DamageController {

    @GetMapping("DamageIndex")
    public String damageIndex(){
        DamageRepository damageRepository = new DamageRepository();
        List<Damage> allDamages = damageRepository.getAllEntities();
        return "damageIndex";
    }

    @GetMapping("/damageReport")
    public String damageReport(@RequestParam int id, Model model) {
        ContractDamageService contractDamageService = new ContractDamageService();
        /*model.addAttribute("contract", id);*/
        model.addAttribute("damages", contractDamageService.contractWithDamage(id));
        return "damageReport";
    }


}
