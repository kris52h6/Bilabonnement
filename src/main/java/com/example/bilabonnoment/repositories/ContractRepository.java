package com.example.bilabonnoment.repositories;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.utility.DatabaseConnectionManager;
import org.springframework.web.context.request.WebRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContractRepository implements  IContractRepository {

    @Override
    public List<Contract> getAllEntities() {
        Connection conn = DatabaseConnectionManager.getConnection();
        List<Contract> allContracts = new ArrayList<>();
        try {
            //PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.contract JOIN bilabonnement.customer ON contract.customer_cpr_nr = customer.customer_cpr_nr");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.contract");
            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                Contract temp = new Contract(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
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
                        rs.getInt(3),
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
            pstmt.setInt(2, contract.getVinNo());
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
                        rs.getInt(3),
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
    public void createContract(WebRequest data) {
        System.out.println(data.getParameter("contractStartDate"));

        Contract contract = new Contract(
                -1,
                data.getParameter("customerCprNr"),
                Integer.parseInt(Objects.requireNonNull(data.getParameter("vinNo"))),
                Double.parseDouble(Objects.requireNonNull(data.getParameter("contractPrice"))),
                data.getParameter("carPickupPlace"),
                data.getParameter("carReturnPlace"),
                Date.valueOf(data.getParameter("contractStartDate")),
                Date.valueOf(data.getParameter("contractEndDate")),
                false,
                Contract.Damage.UNCHECKED
        );

        create(contract);
    }


    public boolean editContract(Contract contract){
        Connection conn = DatabaseConnectionManager.getConnection();
        boolean result = false;
        int id = contract.getId();
        try{
            PreparedStatement pstmt = conn.prepareStatement("UPDATE bilabonnement.contract SET customer_cpr_nr = ?, vin_no = ?, contract_price = ?, car_pickup_place = ?, car_return_place = ?, contract_start_date = ?, contract_end_date = ?, is_returned = ?, contract_damage = ? WHERE (contract_id = " + id + ");");
            pstmt.setString(1, contract.getCprNum());
            pstmt.setInt(2, contract.getVinNo());
            pstmt.setDouble(3, contract.getPrice());
            pstmt.setString(4, contract.getPickupPlace());
            pstmt.setString(5, contract.getReturnPlace());
            pstmt.setDate(6, contract.getStartDate());
            pstmt.setDate(7, contract.getEndDate());
            pstmt.setBoolean(8, contract.isReturned());
            pstmt.setString(9, contract.getDamage().name());

            pstmt.executeUpdate();
            result = true;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args)
    {
        ContractRepository contractRepository = new ContractRepository();
        String str = "2000-05-05";
        Date date = Date.valueOf(str);
        Contract editContract = new Contract(1, "123", 1415, 1000, "her", "her", date, date, true, Contract.Damage.YES);
        contractRepository.editContract(editContract);
    }
}
