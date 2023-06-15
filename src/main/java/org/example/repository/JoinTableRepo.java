package org.example.repository;

import org.example.entity.JoinTable;

import java.sql.SQLException;

public interface JoinTableRepo {
    // insert
    void insert(JoinTable joinTable) throws SQLException;
    // delete
    int delete(JoinTable joinTable) throws SQLException;
    // edit
    int update(JoinTable oldJoinTable, long newBrandId) throws SQLException;
    // find all
    JoinTable[] loadAll() throws SQLException;
}
