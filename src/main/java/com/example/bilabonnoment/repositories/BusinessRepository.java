package com.example.bilabonnoment.repositories;

import com.example.bilabonnoment.models.Car;
import com.example.bilabonnoment.repositories.interfaces.IBusinessRepository;
import com.example.bilabonnoment.utility.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessRepository implements IBusinessRepository {

    @Override
    public List<Car> getAllEntities() {
        Connection conn = DatabaseConnectionManager.getConnection();
        List<Car> allCars = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.car");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Car temp = new Car(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getDouble(8),
                        rs.getBoolean(9)
                );
                allCars.add(temp);
            }

        } catch (SQLException e) {
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return allCars;
    }

    public List<Car> getAllRentedCars() {
        Connection conn = DatabaseConnectionManager.getConnection();
        List<Car> allRentedCars = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.car WHERE car_is_leased = 1");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Car temp = new Car(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getDouble(8),
                        rs.getBoolean(9)
                );
                allRentedCars.add(temp);
            }

        } catch (SQLException e) {
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return allRentedCars;
    }

    public List<Car> getAllAvailableCars() {
        Connection conn = DatabaseConnectionManager.getConnection();
        List<Car> allAvailableCars = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.car WHERE car_is_leased = 0");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Car temp = new Car(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getDouble(8),
                        rs.getBoolean(9)
                );
                allAvailableCars.add(temp);
            }

        } catch (SQLException e) {
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return allAvailableCars;
    }


    @Override
    public Car getSingleById(int id) {
        Connection conn = DatabaseConnectionManager.getConnection();
        Car temp = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.car WHERE car_id = " + id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                temp = new Car(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getDouble(8),
                        rs.getBoolean(9)
                );
            }

        } catch (SQLException e) {
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return temp;
    }


    @Override
    public Car getCarFromVinNo(String vinNo) {
        Connection conn = DatabaseConnectionManager.getConnection();
        Car temp = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.car WHERE car_vin = ?");
            pstmt.setString(1,vinNo);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                temp = new Car(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getDouble(8),
                        rs.getBoolean(9)
                );
            }

        } catch (SQLException e) {
            System.out.println("Something wrong in statement");
            e.printStackTrace();
        }
        return temp;
    }

    @Override
    public void editCarLeasingStatus(int carId, boolean isLeased) {
        Connection conn = DatabaseConnectionManager.getConnection();
        boolean result = false;
        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE bilabonnement.car SET car_is_leased = ? WHERE (car_id = ?);");
            pstmt.setBoolean(1, isLeased);
            pstmt.setInt(2, carId);
            pstmt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean create(Car entity) {
        return false;
    }

}
