package repositories;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.repositories.interfaces.IContractRepository;
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
                    new Contract(2, "34", 13, 110, "her", "her", date, date, false, Contract.Damage.UNCHECKED),
                    new Contract(3, "56", 14, 90, "her", "her", date, date, true, Contract.Damage.NO),
                    new Contract(4, "78", 15, 120, "her", "her", date, date, false, Contract.Damage.NO),
                    new Contract(5, "90", 16, 115, "her", "her", date, date, true, Contract.Damage.YES),
                    new Contract(6, "123", 17, 105, "her", "her", date, date, false, Contract.Damage.UNCHECKED)
            )
    );

    public List<Contract> getContracts() {
        return allContracts;
    }

    @Override
    public List<Contract> getAllContractsFromCustomerCprNr(String cprNr) {
        return null;
    }

    @Override
    public String getCprNrFromContractId(int contractId) {
        String temp = null;
        for (Contract contract : allContracts) {
            if (contract.getId() == contractId) {
                temp = contract.getCprNum();
            }
        }
        return temp;
    }


    @Override
    public Contract createContract(WebRequest data) {
        return null;
    }

    @Override
    public int changeContractDamage(int contractId, String updatedDamageStatus) {
        return 0;
    }

    @Override
    public List<Contract> getAllReturnedUncheckedContracts() {
        List<Contract> uncheckedContracts = new ArrayList<>();
        for (Contract contract : allContracts) {
            if (contract.getDamage().equals(Contract.Damage.UNCHECKED)) {
                uncheckedContracts.add(contract);
            }
        }
        return uncheckedContracts;
    }

    @Override
    public List<Contract> getAllReturnedDamagedContracts() {
        return null;
    }

    @Override
    public boolean editContract(Contract contract) {
        return false;
    }

    @Override
    public Contract createTempContractObj(WebRequest data) {
        return null;
    }

    @Override
    public List<Contract> getAllEntities() {
        return null;
    }

    @Override
    public Contract getSingleById(int id) {
        Contract contract = null;
        for (Contract currentContract : allContracts) {
            if (currentContract.getId() == id) {
                contract = currentContract;
            }
        }
        return contract;
    }

    @Override
    public boolean create(Contract entity) {
        return false;
    }
}
