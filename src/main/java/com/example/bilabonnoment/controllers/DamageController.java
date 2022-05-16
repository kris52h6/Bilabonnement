package com.example.bilabonnoment.controllers;

import com.example.bilabonnoment.models.Damage;
import com.example.bilabonnoment.repositories.DamageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DamageController {

    @GetMapping("DamageIndex")
    public String damageIndex(){
        DamageRepository damageRepository = new DamageRepository();
        List<Damage> allDamages = damageRepository.getAllEntities();
        return "damageIndex";
    }


}
