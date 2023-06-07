package org.example.service;

import org.example.entity.Brand;

import java.sql.SQLException;

public interface BrandService {
    void create(String name, String website, String description) throws SQLException;

    void remove(String name) throws SQLException;

    void edit(String oldName, String name, String website, String description) throws SQLException;

    void show(String name) throws SQLException;
}
