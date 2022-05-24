package com.example.bilabonnoment.repositories;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.repositories.interfaces.IContractRepository;
import com.example.bilabonnoment.utility.DatabaseConnectionManager;
import org.springframework.web.context.request.WebRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContractRepository implements IContractRepository {

    public static void main(String[] args) {
        ContractRepository contractRepository = new ContractRepository();
        String str = "2000-05-05";
        Date date = Date.valueOf(str);
        Contract editContract = new Contract(1, "123", "1415", 1000, "her", "her", date, date, true, Contract.Damage.YES);
        contractRepository.editContract(editContract);
    }

    @Override
    public List<Contract> getAllEntities() {
        Connection conn = DatabaseConnectionManager.getConnection();
        List<Contract> allContracts = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.contract");
            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                Contract temp = new Contract(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getDate(8),
                        rs.getBoolean(9),
                        Contract.Damage.valueOf(rs.getString(10))

                );
                allContracts.add(temp);
            }

        } catch (SQLException e) {
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return allContracts;
    }

    @Override
    public Contract getSingleById(int id) {
        Connection conn = DatabaseConnectionManager.getConnection();
        Contract temp = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.contract WHERE contract_id = " + id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                temp = new Contract(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getDate(8),
                        rs.getBoolean(9),
                        Contract.Damage.valueOf(rs.getString(10))
                );
            }

        } catch (SQLException e) {
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }

        return temp;
    }

    @Override
    public boolean create(Contract contract) {
        Connection conn = DatabaseConnectionManager.getConnection();
        boolean result = false;
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO bilabonnement.contract (customer_cpr_nr, vin_no, contract_price, car_pickup_place, car_return_place, contract_start_date, contract_end_date, is_returned, contract_damage) VALUES (?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1, contract.getCprNum());
            pstmt.setString(2, contract.getVinNo());
            pstmt.setDouble(3, contract.getPrice());
            pstmt.setString(4, contract.getPickupPlace());
            pstmt.setString(5, contract.getReturnPlace());
            pstmt.setDate(6, contract.getStartDate());
            pstmt.setDate(7, contract.getEndDate());
            pstmt.setBoolean(8, contract.isReturned());
            pstmt.setString(9, contract.getDamage().name());

            pstmt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Contract> getAllContractsFromCustomerCprNr(String cprNr) {
        Connection conn = DatabaseConnectionManager.getConnection();
        List<Contract> allContractsFromCustomer = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.contract WHERE customer_cpr_nr = '" + cprNr + "'");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Contract temp = new Contract(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getDate(8),
                        rs.getBoolean(9),
                        Contract.Damage.valueOf(rs.getString(10))
                );
                allContractsFromCustomer.add(temp);
            }

        } catch (SQLException e) {
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return allContractsFromCustomer;
    }

    @Override
    public String getCprNrFromContractId(int contractId) {
        Connection conn = DatabaseConnectionManager.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT customer_cpr_nr FROM bilabonnement.contract WHERE contract_id = " + contractId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }

        } catch (SQLException e) {
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Contract createContract(WebRequest data) {
        System.out.println(data.getParameter("contractStartDate"));

        return new Contract(
                -1,
                data.getParameter("customerCprNr"),
                data.getParameter("vinNo"),
                Double.parseDouble(Objects.requireNonNull(data.getParameter("contractPrice"))),
                data.getParameter("carPickupPlace"),
                data.getParameter("carReturnPlace"),
                Date.valueOf(Objects.requireNonNull(data.getParameter("contractStartDate"))),
                Date.valueOf(Objects.requireNonNull(data.getParameter("contractEndDate"))),
                false,
                Contract.Damage.UNCHECKED
        );
    }

    @Override
    public int changeContractDamage(int contractId, String updatedDamageStatus) {

        Contract.Damage DamageStatus = Contract.Damage.UNCHECKED;

        switch (updatedDamageStatus) {
            case "YES" -> DamageStatus = Contract.Damage.YES;
            case "NO" -> DamageStatus = Contract.Damage.NO;
        }


        Connection conn = DatabaseConnectionManager.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE bilabonnement.contract SET contract_damage = ? WHERE (contract_id = " + contractId + ");");
            pstmt.setString(1, DamageStatus.name());
            pstmt.executeUpdate();
            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public List<Contract> getAllReturnedUncheckedContracts() {
        Connection conn = DatabaseConnectionManager.getConnection();
        List<Contract> allContracts = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.contract WHERE is_returned = 1 AND contract_damage = 'UNCHECKED'");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Contract temp = new Contract(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getDate(8),
                        rs.getBoolean(9),
                        Contract.Damage.valueOf(rs.getString(10))

                );
                allContracts.add(temp);
            }

        } catch (SQLException e) {
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return allContracts;
    }

    @Override
    public List<Contract> getAllReturnedDamagedContracts() {
        Connection conn = DatabaseConnectionManager.getConnection();
        List<Contract> allContracts = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.contract WHERE is_returned = 1 AND contract_damage = 'YES'");
            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                Contract temp = new Contract(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getDate(8),
                        rs.getBoolean(9),
                        Contract.Damage.valueOf(rs.getString(10))

                );
                allContracts.add(temp);
            }

        } catch (SQLException e) {
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return allContracts;
    }

    @Override
    public boolean editContract(Contract contract) {

        Connection conn = DatabaseConnectionManager.getConnection();
        boolean result = false;
        int id = contract.getId();
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE bilabonnement.contract SET customer_cpr_nr = ?, vin_no = ?, contract_price = ?, car_pickup_place = ?, car_return_place = ?, contract_start_date = ?, contract_end_date = ?, is_returned = ?, contract_damage = ? WHERE (contract_id = " + id + ");");
            pstmt.setString(1, contract.getCprNum());
            pstmt.setString(2, contract.getVinNo());
            pstmt.setDouble(3, contract.getPrice());
            pstmt.setString(4, contract.getPickupPlace());
            pstmt.setString(5, contract.getReturnPlace());
            pstmt.setDate(6, contract.getStartDate());
            pstmt.setDate(7, contract.getEndDate());
            pstmt.setBoolean(8, contract.isReturned());
            pstmt.setString(9, contract.getDamage().name());

            pstmt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Contract createTempContractObj(WebRequest data) {
        Contract temp = new Contract(
                Integer.parseInt(Objects.requireNonNull(data.getParameter("contractId"))),
                data.getParameter("customerCprNr"),
                data.getParameter("vinNo"),
                Double.parseDouble(Objects.requireNonNull(data.getParameter("contractPrice"))),
                data.getParameter("carPickupPlace"),
                data.getParameter("carReturnPlace"),
                Date.valueOf(Objects.requireNonNull(data.getParameter("contractStartDate"))),
                Date.valueOf(Objects.requireNonNull(data.getParameter("contractEndDate"))),
                false,
                Contract.Damage.valueOf(data.getParameter("isDamaged"))

        );
        return temp;
    }
}
