package com.example.bilabonnoment.repositories;

import com.example.bilabonnoment.models.Damage;
import com.example.bilabonnoment.utility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DamageRepository implements IDamageRepository {

    @Override
    public List<Damage> getAllEntities() {
        Connection conn = DatabaseConnectionManager.getConnection();
        List<Damage> allDamages = new ArrayList<>();
        try {

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.damage");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){;
            }

        }catch(SQLException e){
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return allDamages;
    }

    @Override
    public Damage getSingleById(int id) {
        Connection conn = DatabaseConnectionManager.getConnection();
        Damage temp = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.damage WHERE damage_id = " + id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                temp = new Damage(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getInt(4)
                );
            }

        }catch(SQLException e){
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return temp;
    }

    @Override
    public boolean create(Damage entity) {
        return false;
    }

    @Override
    public List<Damage> getAllDamagesFromContract(int contractId) {
        Connection conn = DatabaseConnectionManager.getConnection();
        List<Damage> allDamages = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.damage WHERE contract_id = " + contractId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Damage temp = new Damage(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getInt(4)
                );
                allDamages.add(temp);
            }

        }catch(SQLException e){
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return allDamages;
    }

    @Override
    public List<String> getAllDamagesFromContracts() {
        return null;
    }
}
