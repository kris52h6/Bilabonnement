package com.example.bilabonnoment.repositories;

import com.example.bilabonnoment.models.User;
import com.example.bilabonnoment.repositories.interfaces.ILoginRepository;
import com.example.bilabonnoment.utility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepository implements ILoginRepository {
    public User authenticateUser(String username, String password) {
        Connection conn = DatabaseConnectionManager.getConnection();
        User user = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.user WHERE user_username = ? AND user_password = ?");
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                user = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
            }

        } catch (SQLException e) {
            System.out.println("username and password does not match");
            e.printStackTrace();
        }
        return user;
    }
}
