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
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.contract WHERE contract_id = ?");
            pstmt.setInt(1, id);
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
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO bilabonnement.contract (customer_cpr_num, car_vin, contract_price, car_pickup_place, car_return_place, contract_start_date, contract_end_date, is_returned, contract_damage) VALUES (?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1, contract.getCprNum());
            pstmt.setString(2, contract.getVin());
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
    public List<Contract> getAllContractsFromCustomerCprNum(String cprNum) {
        Connection conn = DatabaseConnectionManager.getConnection();
        List<Contract> allContractsFromCustomer = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.contract WHERE customer_cpr_num = ?");
            pstmt.setString(1, cprNum);
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
    public String getCprNumFromContractId(int contractId) {
        Connection conn = DatabaseConnectionManager.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT customer_cpr_num FROM bilabonnement.contract WHERE contract_id = ?;");
            pstmt.setInt(1, contractId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
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
        try {
            return new Contract(
                    -1,
                    data.getParameter("customerCprNum"),
                    data.getParameter("vin"),
                    Double.parseDouble(Objects.requireNonNull(data.getParameter("contractPrice"))),
                    data.getParameter("carPickupPlace"),
                    data.getParameter("carReturnPlace"),
                    Date.valueOf(Objects.requireNonNull(data.getParameter("contractStartDate"))),
                    Date.valueOf(Objects.requireNonNull(data.getParameter("contractEndDate"))),
                    false,
                    Contract.Damage.UNCHECKED
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Contract createTempContractObj(WebRequest data) {
        return new Contract(
                Integer.parseInt(Objects.requireNonNull(data.getParameter("contractId"))),
                data.getParameter("customerCprNum"),
                data.getParameter("vin"),
                Double.parseDouble(Objects.requireNonNull(data.getParameter("contractPrice"))),
                data.getParameter("carPickupPlace"),
                data.getParameter("carReturnPlace"),
                Date.valueOf(Objects.requireNonNull(data.getParameter("contractStartDate"))),
                Date.valueOf(Objects.requireNonNull(data.getParameter("contractEndDate"))),
                false,
                Contract.Damage.valueOf(data.getParameter("isDamaged"))

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
            PreparedStatement pstmt = conn.prepareStatement("UPDATE bilabonnement.contract SET contract_damage = ? WHERE (contract_id = ?);");
            pstmt.setString(1, DamageStatus.name());
            pstmt.setInt(2,contractId);
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
        int contractId = contract.getId();
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE bilabonnement.contract SET customer_cpr_num = ?, car_vin = ?, contract_price = ?, car_pickup_place = ?, car_return_place = ?, contract_start_date = ?, contract_end_date = ?, is_returned = ?, contract_damage = ? WHERE (contract_id = ?);");
            pstmt.setString(1, contract.getCprNum());
            pstmt.setString(2, contract.getVin());
            pstmt.setDouble(3, contract.getPrice());
            pstmt.setString(4, contract.getPickupPlace());
            pstmt.setString(5, contract.getReturnPlace());
            pstmt.setDate(6, contract.getStartDate());
            pstmt.setDate(7, contract.getEndDate());
            pstmt.setBoolean(8, contract.isReturned());
            pstmt.setString(9, contract.getDamage().name());
            pstmt.setInt(10, contractId);

            pstmt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
