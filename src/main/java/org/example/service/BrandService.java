package org.example.service;

import java.sql.SQLException;

public interface BrandService {
    void add(String name, String website, String description) throws SQLException;

    void removeById(long id) throws SQLException;

    void editById(long id, String name, String website, String description) throws SQLException;

    void findById(long id) throws SQLException;

    void showAll() throws SQLException;

    boolean isEmpty() throws SQLException;
}
