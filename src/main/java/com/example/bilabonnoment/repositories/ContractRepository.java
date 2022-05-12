package com.example.bilabonnoment.repositories;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.utility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContractRepository implements  IRepository<Contract>{

    private final Connection conn = DatabaseConnectionManager.getConnection();


    @Override
    public List<Contract> getAllEntities() {
        List<Contract> allContracts = new ArrayList<>();
        try {
            //PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.contract JOIN bilabonnement.customer ON contract.customer_cpr_nr = customer.customer_cpr_nr");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.contract");
            ResultSet rs = pstmt.executeQuery();


            while(rs.next()){
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

        }catch(SQLException e){
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return allContracts;
    }

    @Override
    public Contract getSingleById(int id) {
            Contract temp = null;
            try {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.contract WHERE contract_id = " + id);
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()){
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

            }catch(SQLException e){
                System.out.println("Something wrong in statement");
                e.printStackTrace();
            }
            return temp;
    }

    @Override
    public boolean create(Contract contract) {
        boolean result = false;
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO bilabonnement.contract (customer_cpr_nr, vin_no, contract_price, car_pickup_place, car_return_place, contract_start_date, contract_end_date, is_returned, contract_damage) VALUES (?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1, contract.getCprNr());
            pstmt.setInt(2, contract.getVin_no());
            pstmt.setDouble(3, contract.getPrice());
            pstmt.setString(4, contract.getPickupPlace());
            pstmt.setString(5, contract.getReturnPlace());
            pstmt.setDate(6, contract.getStartDate());
            pstmt.setDate(7, contract.getEndDate());
            pstmt.setBoolean(8, contract.isReturned());

            pstmt.executeQuery();
            result = true;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
}
