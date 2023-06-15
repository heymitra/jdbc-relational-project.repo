package org.example.repository;

import org.example.entity.Product;

import java.sql.SQLException;

public interface ProductRepo {
    long insert(Product product) throws SQLException;

    int delete(Long id) throws SQLException;

    void update(Long id, Product product) throws SQLException;

    boolean existById(Long id) throws SQLException;

    Product loadById(Long id) throws SQLException;
}
