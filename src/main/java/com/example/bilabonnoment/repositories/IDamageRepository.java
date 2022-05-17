package com.example.bilabonnoment.repositories;

import com.example.bilabonnoment.models.Damage;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

public interface IDamageRepository extends IRepository<Damage> {

    public List<Damage> getAllDamagesFromContract(int id);

    public List<String> getAllDamagesFromContracts();

    public void createDamage(WebRequest data);

    public void deleteDamage(int damageId);



}

