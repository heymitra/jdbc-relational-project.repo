package org.example;

import org.example.ui.Menu;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {

        while (!Menu.stop) {
            Menu.run();
        }

    }
}