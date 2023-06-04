package org.example.config;

import org.example.util.Constant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    public Connection getCreateConnection() {
        try {
            Connection connection = DriverManager.getConnection(Constant.URL, Constant.USERNAME, Constant.PASSWORD);
            System.out.println("Connection created. ");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
