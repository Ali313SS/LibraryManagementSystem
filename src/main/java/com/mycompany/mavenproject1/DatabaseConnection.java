/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author 20101
 */
public class DatabaseConnection {
   static String url = "jdbc:sqlserver://DESKTOP-VNM1EDO:1433;databaseName=NDB";
   static  String username = "sa";
   static String password = "AliRagab313";

    // Remove static connection to create a new connection each time
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
    public static void main(String [] a)
    {
        
        System.out.println("Fsfdfsd");
        try (Connection connection = DatabaseConnection.getConnection()){
            if (connection != null){
                System.out.println("success connect to mysql");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            System.out.println("Fail to connect to mysql");
        }
    }
}