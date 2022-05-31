package com.example.bilabonnoment.repositories.interfaces;

import com.example.bilabonnoment.models.Damage;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

public interface IDamageRepository extends IRepository<Damage> {

    ArrayList<Damage> getAllDamagesFromContract(int contractId);

    List<String> getAllDamagesFromContracts();

    Damage createDamage(WebRequest data);

    boolean createTempDamageObj(WebRequest data);

    void deleteDamage(int damageId);

}

