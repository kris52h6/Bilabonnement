package repositories;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.repositories.IContractRepository;
import org.springframework.web.context.request.WebRequest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContractTestRepository implements IContractRepository {
    String str = "2200-05-05";
    Date date = Date.valueOf(str);
    ArrayList<Contract> allContracts = new ArrayList<Contract>(
            Arrays.asList(
                    new Contract(1, "12", 12, 100, "her", "her", date, date, true, Contract.Damage.UNCHECKED),
                    new Contract(2, "34", 13, 110, "her", "her", date, date, false, Contract.Damage.YES),
                    new Contract(3, "56", 14, 90, "her", "her", date, date, true, Contract.Damage.NO),
                    new Contract(4, "78", 15, 105, "her", "her", date, date, false, Contract.Damage.YES)
            )
    );

    @Override
    public List<Contract> getAllContractsFromCustomerCprNr(String cprNr)
    {
        return null;
    }

    @Override
    public String getCprNrFromContractId(int contractId)
    {
        String temp = null;
        for (Contract contract : allContracts){
            if (contract.getId() == contractId){
                temp = contract.getCprNum();
            }
        }
        return temp;
    }


    @Override
    public void createContract(WebRequest data)
    {

    }

    @Override
    public int changeContractDamage(int contractId, String updatedDamageStatus) {
        return 0;
    }

    @Override
    public List<Contract> getAllReturnedUncheckedContracts() {

        return null;
    }

    @Override
    public List<Contract> getAllReturnedDamagedContracts() {
        return null;
    }

    @Override
    public List<Contract> getAllEntities()
    {
        return null;
    }

    @Override
    public Contract getSingleById(int id)
    {
        return null;
    }

    @Override
    public boolean create(Contract entity)
    {
        return false;
    }
}
