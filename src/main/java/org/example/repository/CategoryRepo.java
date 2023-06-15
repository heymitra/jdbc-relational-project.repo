package org.example.repository;

import org.example.entity.Category;

import java.sql.SQLException;

public interface CategoryRepo {
    long insert(Category category) throws SQLException;

    int delete(long id) throws SQLException;

    int update(Category category) throws SQLException;

    boolean existByName(String name) throws SQLException;

    Category loadByName(String name) throws SQLException;

    Category loadById(long id) throws SQLException;

    Category[] loadAll() throws SQLException;

    boolean isEmpty() throws SQLException;
}

