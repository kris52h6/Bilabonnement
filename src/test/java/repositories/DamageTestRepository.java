package repositories;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.models.Damage;
import com.example.bilabonnoment.repositories.IDamageRepository;
import com.example.bilabonnoment.utility.DatabaseConnectionManager;
import org.springframework.web.context.request.WebRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DamageTestRepository implements IDamageRepository {
    ArrayList<Damage> allDamages = new ArrayList<>(
            Arrays.asList(
                 new Damage(1, 50, "tire", 1),
                 new Damage(2, 70, "door handle", 1),
                 new Damage(3, 80, "rear view mirror", 2),
                 new Damage(4, 60, "engien", 2),
                 new Damage(5, 40, "front door", 3),
                 new Damage(6, 90, "trunk handle", 3),
                 new Damage(7, 20, "door", 4),
                 new Damage(8, 300, "passenger door ", 4)
            )
    );

    @Override
    public List<Damage> getAllDamagesFromContract(int contractId)
    {
        return null;
    }

    @Override
    public List<String> getAllDamagesFromContracts()
    {
        return null;
    }

    @Override
    public void createDamage(WebRequest data)
    {

    }

    @Override
    public void createTempDamageObj(WebRequest data)
    {

    }

    @Override
    public void deleteDamage(int damageId)
    {

    }

    @Override
    public List<Damage> getAllEntities()
    {
        return allDamages;
    }

    @Override
    public Damage getSingleById(int id)
    {
        return null;
    }

    @Override
    public boolean create(Damage entity)
    {
        return false;
    }
}
