package com.example.bilabonnoment.controllers;

import com.example.bilabonnoment.repositories.DamageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DamageController {

    @GetMapping("DamageIndex")
    public String damageIndex(){
        DamageRepository damageRepository = new DamageRepository();



        return "damageIndex";
    }


}
