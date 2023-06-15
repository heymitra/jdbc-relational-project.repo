package org.example.repository;

import org.example.entity.Shareholder;

import java.sql.SQLException;

public interface ShareholderRepo {
    long insert(Shareholder shareholder) throws SQLException;

    int delete(long id) throws SQLException;

    int update(Shareholder shareholder) throws SQLException;

    Shareholder loadById(long id) throws SQLException;
}
