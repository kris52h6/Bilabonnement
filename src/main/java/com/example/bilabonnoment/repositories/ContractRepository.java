package com.example.bilabonnoment.repositories;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.utility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ContractRepository implements  IRepository{
    @Override
    public List getAllEntities() {
        return null;
    }

    @Override
    public Contract getSingleById(int id) {
            Connection conn = DatabaseConnectionManager.getConnection();
            Contract temp = null;
            try {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.contract WHERE contract_id = " + 1);
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
                            rs.getBoolean(9)
                    );
                }

            }catch(SQLException e){
                System.out.println("Something wrong in statement");
                e.printStackTrace();
            }
            return temp;
        }

    @Override
    public boolean create(Object entity) {
        return false;
    }
}
