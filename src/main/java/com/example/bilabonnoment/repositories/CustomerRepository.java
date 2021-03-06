package com.example.bilabonnoment.repositories;

import com.example.bilabonnoment.models.Customer;
import com.example.bilabonnoment.repositories.interfaces.ICustomerRepository;
import com.example.bilabonnoment.utility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kristian, Oliver
 */
public class CustomerRepository implements ICustomerRepository {
    @Override
    public List<Customer> getAllEntities() {
        Connection conn = DatabaseConnectionManager.getConnection();
        List<Customer> allContracts = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.customer");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Customer temp = new Customer(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
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
    public Customer getSingleById(int id) {
        Connection conn = DatabaseConnectionManager.getConnection();
        Customer temp = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.customer WHERE customer_id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                temp = new Customer(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
            }

        } catch (SQLException e) {
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
    public Customer getCustomerFromCprNum(String cprNum) {
        Connection conn = DatabaseConnectionManager.getConnection();
        Customer temp = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.customer WHERE customer_cpr_num = ?;");
            pstmt.setString(1,cprNum);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                temp = new Customer(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
            }

        } catch (SQLException e) {
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }

        return temp;
    }
}
