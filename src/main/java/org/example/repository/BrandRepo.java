package org.example.repository;

import org.example.entity.Brand;

import java.sql.SQLException;

public interface BrandRepo {
    long insert(Brand brand) throws SQLException;

    void delete(Brand brand) throws SQLException;

    void update(String name, Brand brand) throws SQLException;

    boolean existByName(String name) throws SQLException;

    Brand loadByName(String Name) throws SQLException;
}
