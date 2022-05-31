package com.example.bilabonnoment.repositories.interfaces;

import java.util.List;

public interface IRepository<T> {

    //ReadAll
    List<T> getAllEntities();

    //ReadSingle
    T getSingleById(int id);

    //Create
    boolean create(T entity);

    //Update

    //Delete
}
