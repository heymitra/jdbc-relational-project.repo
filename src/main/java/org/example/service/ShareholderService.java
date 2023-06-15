package org.example.service;

import java.sql.SQLException;

public interface ShareholderService {
    void add(String name, String phoneNumber, String nationalCode) throws SQLException;

    void removeById(long id) throws SQLException;

    void editByID(long id, String name, String phoneNumber, String nationalCode) throws SQLException;

    void findById(long id) throws SQLException;
}
