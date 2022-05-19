package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.repositories.*;
import org.junit.jupiter.api.Test;
import repositories.ContractTestRepository;
import repositories.DamageTestRepository;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetAllReturnedUncheckedContracts
{
    @Test
    void getAllReturnedUncheckedContracts()
    {
        DamageTestRepository damageTestRepository = new DamageTestRepository();
        ContractTestRepository contractTestRepository = new ContractTestRepository();
        ContractDamageService contractDamageService = new ContractDamageService(damageTestRepository, contractTestRepository);

        String str = "2200-05-05";
        Date date = Date.valueOf(str);
        List<Contract> expectContracts = Arrays.asList(
                new Contract(1, "12", 12, 100, "her", "her", date, date, true, Contract.Damage.UNCHECKED),
                new Contract(2, "34", 13, 110, "her", "her", date, date, false, Contract.Damage.UNCHECKED)
        );


        List<Contract> actualContracts = contractDamageService.getAllReturnedUncheckedContracts();

        assertThat(actualContracts.size(), is(expectContracts.size()));

    }
}
