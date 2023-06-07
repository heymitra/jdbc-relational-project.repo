package org.example.service;

import java.sql.SQLException;

public interface CategoryService {
    void create(String name, String description) throws SQLException;

    void remove(String name) throws SQLException;

    void edit(String oldName, String name, String description) throws SQLException;

    void show(String name) throws SQLException;
}
