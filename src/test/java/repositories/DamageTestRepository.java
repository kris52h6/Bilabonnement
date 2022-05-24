package repositories;

import com.example.bilabonnoment.models.Damage;
import com.example.bilabonnoment.repositories.interfaces.IDamageRepository;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DamageTestRepository implements IDamageRepository {
    ArrayList<Damage> allDamages = new ArrayList<>(
            Arrays.asList(
                    new Damage(1, 50, "tire", 5),
                    new Damage(2, 70, "door handle", 5),
                    new Damage(3, 80, "rear view mirror", 1),
                    new Damage(4, 60, "engien", 2),
                    new Damage(5, 40, "front door", 3),
                    new Damage(6, 90, "trunk handle", 4),
                    new Damage(7, 20, "door", 1),
                    new Damage(8, 300, "passenger door ", 2)
            )
    );

    @Override
    public ArrayList<Damage> getAllDamagesFromContract(int contractId) {
        ArrayList<Damage> damages = new ArrayList<>();
        for (Damage damage : allDamages) {
            if (damage.getContractId() == contractId) {
                damages.add(damage);
            }
        }
        return damages;
    }

    @Override
    public List<String> getAllDamagesFromContracts() {
        return null;
    }

    @Override
    public Damage createDamage(WebRequest data) {
        return null;
    }

    @Override
    public void createTempDamageObj(WebRequest data) {

    }

    @Override
    public void deleteDamage(int damageId) {

    }

    @Override
    public List<Damage> getAllEntities() {
        return allDamages;
    }

    @Override
    public Damage getSingleById(int id) {
        return null;
    }

    @Override
    public boolean create(Damage entity) {
        return false;
    }
}
