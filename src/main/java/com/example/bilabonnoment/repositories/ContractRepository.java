package com.example.bilabonnoment.repositories;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.utility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContractRepository implements  IRepository{

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

        System.out.println(temp.getDamage());
            return temp;
    }

    @Override
    public boolean create(Object entity) {
        return false;
    }
}
