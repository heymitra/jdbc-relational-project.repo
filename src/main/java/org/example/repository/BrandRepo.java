package org.example.repository;

import org.example.entity.Brand;

import java.sql.SQLException;

public interface BrandRepo {
    long insert(Brand brand) throws SQLException;

    int delete(long id) throws SQLException;

    int update(Brand brand) throws SQLException;

    boolean existByName(String name) throws SQLException;

    Brand loadByName(String name) throws SQLException;

    Brand loadById(long id) throws SQLException;

    Brand[] loadAll() throws SQLException;

    boolean isEmpty() throws SQLException;
}
