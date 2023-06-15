package org.example.service;

import java.sql.SQLException;

public interface ProductService {
    void add(String name, String creationDate, Long categoryId, Long brandId) throws SQLException;

    void removeById(Long id) throws SQLException;

    void editById(Long id, String name, String creationDate, Long categoryId, Long brandId) throws SQLException;

    void findById(Long id) throws SQLException;
}
