package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.models.Damage;
import org.junit.jupiter.api.Test;
import repositories.ContractTestRepository;
import repositories.DamageTestRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

class ContractsFromDamageList {

    @Test
    void contractsFromDamageList() {
        DamageTestRepository damageTestRepository = new DamageTestRepository();
        ContractTestRepository contractTestRepository = new ContractTestRepository();
        ContractDamageService contractDamageService = new ContractDamageService(damageTestRepository, contractTestRepository);

        String str = "2002-05-05";
        Date date = Date.valueOf(str);
        Damage damage1 = new Damage(1, 50, "tire", 5);
        Damage damage2 = new Damage(2, 70, "door handle", 5);
        Damage damage3 = new Damage(3, 80, "rear view mirror", 1);
        Damage damage4 = new Damage(4, 60, "engien", 2);
        Damage damage5 = new Damage(5, 40, "front door", 3);
        Damage damage6 = new Damage(6, 90, "trunk handle", 4);
        Damage damage7 = new Damage(7, 20, "door", 1);
        Damage damage8 = new Damage(8, 300, "passenger door ", 2);

        ArrayList<Damage> damages1 = new ArrayList<>();
        damages1.add(damage3);
        damages1.add(damage7);
        ArrayList<Damage> damages2 = new ArrayList<>();
        damages2.add(damage4);
        damages2.add(damage8);
        ArrayList<Damage> damages3 = new ArrayList<>();
        damages3.add(damage5);
        ArrayList<Damage> damages4 = new ArrayList<>();
        damages4.add(damage6);
        ArrayList<Damage> damages5 = new ArrayList<>();
        damages5.add(damage1);
        damages5.add(damage2);

        Contract contract1 = new Contract(1, "12", 12, 100, "her", "her", date, date, true, Contract.Damage.UNCHECKED);
        Contract contract2 = new Contract(2, "34", 13, 110, "her", "her", date, date, false, Contract.Damage.UNCHECKED);
        Contract contract3 = new Contract(3, "56", 14, 90, "her", "her", date, date, true, Contract.Damage.NO);
        Contract contract4 = new Contract(4, "78", 15, 120, "her", "her", date, date, false, Contract.Damage.NO);
        Contract contract5 = new Contract(5, "90", 16, 115, "her", "her", date, date, true, Contract.Damage.YES);

        Map<Contract, List<Damage>> expected = new HashMap<>();
        expected.put(contract1, damages1);
        expected.put(contract2, damages2);
        expected.put(contract3, damages3);
        expected.put(contract4, damages4);
        expected.put(contract5, damages5);

        Map<Contract, ArrayList<Damage>> actual = contractDamageService.contractsFromDamageList();

        assertThat(actual.size(), is(expected.size()));

    }
}
