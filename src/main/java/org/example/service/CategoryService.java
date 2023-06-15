package org.example.service;

import java.sql.SQLException;

public interface CategoryService {
    void add(String name, String description) throws SQLException;

    void removeById(long id) throws SQLException;

    void editById(long id, String name, String description) throws SQLException;

    void findById(long id) throws SQLException;

    void showAll() throws SQLException;

    boolean isEmpty() throws SQLException;
}
