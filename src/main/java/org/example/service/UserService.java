package org.example.service;

import java.sql.SQLException;

public interface UserService {

    long register(String name, String username, String email, String password) throws SQLException;

    void login(String username, String password) throws SQLException;

    void logout();

}
