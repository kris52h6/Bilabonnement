package com.example.bilabonnoment.services;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.repositories.interfaces.IContractRepository;
import org.junit.jupiter.api.Test;
import com.example.bilabonnoment.repositories.ContractTestRepository;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

class GetAllReturnedUncheckedContracts {
    @Test
    void getAllReturnedUncheckedContracts() {
        //arrange
        IContractRepository contractTestRepository = new ContractTestRepository();
        ContractService contractService = new ContractService(contractTestRepository);

        String str = "2200-05-05";
        Date date = Date.valueOf(str);
        List<Contract> expectContracts = Arrays.asList(
                new Contract(1, "12", "12", 100, "her", "her", date, date, true, Contract.Damage.UNCHECKED),
                new Contract(2, "34", "13", 110, "her", "her", date, date, false, Contract.Damage.UNCHECKED),
                new Contract(6, "123", "17", 105, "her", "her", date, date, false, Contract.Damage.UNCHECKED)
        );

        //act
        List<Contract> actualContracts = contractService.getAllReturnedUncheckedContracts();

        //assert
        assertThat(actualContracts.size(), is(expectContracts.size()));

    }
}
