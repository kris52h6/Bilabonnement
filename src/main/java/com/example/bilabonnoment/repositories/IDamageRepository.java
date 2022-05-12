package com.example.bilabonnoment.repositories;

import com.example.bilabonnoment.models.Damage;

import java.util.List;

public interface IDamageRepository extends IRepository<Damage>{

    public List<Damage> getAllDamagesFromContract(int id);



}
