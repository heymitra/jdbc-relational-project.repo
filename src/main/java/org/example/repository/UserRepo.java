package org.example.repository;

import org.example.entity.User;

import java.sql.SQLException;

public interface UserRepo {

    long register(User user) throws SQLException;

    User login(User user) throws SQLException;

    boolean existByUsername(String username) throws SQLException;

    boolean existByEmail(String email) throws SQLException;
}
