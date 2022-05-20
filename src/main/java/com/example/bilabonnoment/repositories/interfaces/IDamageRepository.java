package com.example.bilabonnoment.repositories.interfaces;

import com.example.bilabonnoment.models.Damage;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

public interface IDamageRepository extends IRepository<Damage> {

    public List<Damage> getAllDamagesFromContract(int contractId);

    public List<String> getAllDamagesFromContracts();

    public void createDamage(WebRequest data);

    public void createTempDamageObj(WebRequest data);

    public void deleteDamage(int damageId);



}

