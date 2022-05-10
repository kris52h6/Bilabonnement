package com.example.bilabonnoment.repositories;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.models.Customer;
import com.example.bilabonnoment.utility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerRepository implements ICustomerRepository{

    private final Connection conn = DatabaseConnectionManager.getConnection();

    @Override
    public List getAllEntities() {
        return null;
    }

    @Override
    public Customer getSingleById(int id) {
        Customer temp = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.customer WHERE customer_id = " + id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                temp = new Customer(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
            }

        }catch(SQLException e){
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return temp;
    }


    @Override
    public boolean create(Customer entity) {
        return false;
    }

    @Override
    public Customer getCustomerFromCprNr(String cprNr) {
        Customer temp = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.customer WHERE customer_cpr_nr = " + cprNr);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                temp = new Customer(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
            }

        }catch(SQLException e){
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return temp;
    }
}
