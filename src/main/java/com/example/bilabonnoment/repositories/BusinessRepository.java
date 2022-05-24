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
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM bilabonnement.car WHERE car_vin_no = '" + vinNo + "'");
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
            PreparedStatement pstmt = conn.prepareStatement("UPDATE bilabonnement.car SET car_is_leased = ? WHERE (car_id = " + carId + ");");
            pstmt.setBoolean(1, isLeased);
            pstmt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean create(Car entity) {
        return false;
        /*Connection conn = DatabaseConnectionManager.getConnection();

        boolean result = false;
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO bilabonnement.car (car_id, car_vin_no, car_make, car_model, car_equipment_level, car_value_pre_tax, car_registration_tax, car_co2_emission, car_is_leased) VALUES (?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1,car.getId());
            pstmt.setInt(2,car.getVinNo());
            pstmt.setString(3,car.getMake());
            pstmt.setString(4,car.getModel());
            pstmt.setString(5,car.getEquipmentLevel());
            pstmt.setDouble(6,car.getValuePreTax());
            pstmt.setDouble(7,car.getRegistrationTax());
            pstmt.setDouble(8,car.getCo2Emission());
            pstmt.setBoolean(9,car.getIs_leased());

            int row = pstmt.executeUpdate();
            result = true;
            // rows affected
            System.out.println(row); //1

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;*/
    }

}
