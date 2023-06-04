package org.example.config;

import java.sql.Connection;

public class DatabaseConnector {
    static private Connection connection;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            connection = new DatabaseConfig().getCreateConnection();
            return connection;
        }
    }
}
