package com.example.bilabonnoment.repositories;

import com.example.bilabonnoment.models.Contract;
import com.example.bilabonnoment.models.Damage;
import com.example.bilabonnoment.utility.DatabaseConnectionManager;
import org.springframework.web.context.request.WebRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DamageRepository implements IDamageRepository {

    @Override
    public List<Damage> getAllEntities() {
        Connection conn = DatabaseConnectionManager.getConnection();
        List<Damage> allDamages = new ArrayList<>();
        try {

            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.damage ORDER BY contract_id");
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
        Connection conn = DatabaseConnectionManager.getConnection();
        boolean result = false;
        try
        {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO bilabonnement.damage (damage_price, damage_description, contract_id) VALUES (?,?,?)");
            pstmt.setDouble(1, entity.getPrice());
            pstmt.setString(2, entity.getDescription());
            pstmt.setInt(3, entity.getContractId());

            pstmt.executeUpdate();
            result = true;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return result;
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

    @Override
    public void createDamage(WebRequest data) {
        Damage damage = new Damage(
                -1,
                Double.parseDouble(Objects.requireNonNull(data.getParameter("damagePrice"))),
                data.getParameter("damageDescription"),
                Integer.parseInt(Objects.requireNonNull(data.getParameter("contractId")))

        );
        create(damage);
    }

    public boolean editDamage(Damage damage){
        Connection conn = DatabaseConnectionManager.getConnection();
        boolean result = false;
        int id = damage.getId();
        try{
            PreparedStatement pstmt = conn.prepareStatement("UPDATE bilabonnement.damage SET damage_price = ?, damage_description = ?, contract_id = ? WHERE (damage_id = " + id + ");");
            pstmt.setDouble(1, damage.getPrice());
            pstmt.setString(2, damage.getDescription());
            pstmt.setInt(3, damage.getContractId());

            pstmt.executeUpdate();
            result = true;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }
}
