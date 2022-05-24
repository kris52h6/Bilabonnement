<<<<<<< HEAD
package com.example.bilabonnoment.repositories.interfaces;public interface IBusinessRepository {
}
=======
package com.example.bilabonnoment.repositories.interfaces;

import com.example.bilabonnoment.models.Car;

import java.util.List;

public interface IBusinessRepository extends IRepository<Car>{
    public List<Car> getAllRentedCars();


    }
>>>>>>> f1bcdf532cfa7e81b774bed5dde89a59f02b6bda
