package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Damage;
import com.example.bilabonnoment.repositories.interfaces.IDamageRepository;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;

public class DamageService {
    private final IDamageRepository damageRepository;


    public DamageService(IDamageRepository damageRepository) {
        this.damageRepository = damageRepository;
    }


    public ArrayList<Damage> getAllDamagesFromContract(int id) {
        return damageRepository.getAllDamagesFromContract(id);
    }

    public Damage getSingleDamageById(int damageId) {
        return damageRepository.getSingleById(damageId);
    }

    public void editDamage(WebRequest dataFromForm) {
        damageRepository.createTempDamageObj(dataFromForm);
    }

    public void createDamage(WebRequest data) {
        Damage damage = damageRepository.createDamage(data);
        create(damage);
    }

    public void create(Damage damage) {
        damageRepository.create(damage);
    }

    public void deleteDamage(int damageId) {
        damageRepository.deleteDamage(damageId);
    }

}
