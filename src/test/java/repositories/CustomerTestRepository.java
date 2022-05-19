package repositories;

import com.example.bilabonnoment.models.Customer;
import com.example.bilabonnoment.repositories.ICustomerRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerTestRepository implements ICustomerRepository {
    ArrayList<Customer> allCustomers = new ArrayList<>(
            Arrays.asList(
                    new Customer(1,"12", "hans", "hansen"),
                    new Customer(2,"34", "thor", "sørnsen"),
                    new Customer(3,"56", "lars", "larsen"),
                    new Customer(4,"78", "per", "pedersen")
            )
    );

    @Override
    public Customer getCustomerFromCprNr(String cprNr)
    {
        return null;
    }

    @Override
    public List<Customer> getAllEntities()
    {
        return null;
    }

    @Override
    public Customer getSingleById(int id)
    {
        return null;
    }

    @Override
    public boolean create(Customer entity)
    {
        return false;
    }
}
