package org.example.repository;

import org.example.entity.Category;

import java.sql.SQLException;

public interface CategoryRepo {
    long insert(Category category) throws SQLException;

    void delete(Category category) throws SQLException;

    void update(String name, Category category) throws SQLException;

    boolean existByName(String name) throws SQLException;

    Category loadByName(String name) throws SQLException;
}
