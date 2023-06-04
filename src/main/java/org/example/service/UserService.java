package org.example.service;

import org.example.entity.User;

import java.sql.SQLException;

public interface UserService {

    long register(String name, String username, String email, String password) throws SQLException;

    User login(String userName, String password);
}
