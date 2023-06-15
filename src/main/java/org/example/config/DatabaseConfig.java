package org.example.config;

import org.example.ui.ConsoleColor;
import org.example.util.Constant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    public Connection getCreateConnection() {
        try {
            Connection connection = DriverManager.getConnection(Constant.URL, Constant.USERNAME, Constant.PASSWORD);
            System.out.println(ConsoleColor.GREEN_BOLD + "Connection created. " + ConsoleColor.RESET);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
