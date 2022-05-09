package com.example.bilabonnoment.utility;

import java.sql.*;

public class DatabaseConnectionManager
{
    private static String url;
    private static String username;
    private static String password;
    private static Connection conn;

    private DatabaseConnectionManager(){}

    public static Connection getConnection()
    {
        if (conn != null)
        {
            return conn;
        }

        url = "bilabonnementkea.mysql.database.azure.com";
        username = "Gruppe9@bilabonnementkea";
        password = "Dat21dkea";

        try
        {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return conn;
    }
}
