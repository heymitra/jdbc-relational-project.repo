package org.example.repository;

import org.example.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepoImpl implements UserRepo {

    private final Connection connection;

    public UserRepoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long register(User user) throws SQLException {

        String insert = "INSERT INTO my_user (name, username, email, password) " +
                "VALUES (?, ?, ?, ?);";

        PreparedStatement preparedStatement = connection.prepareStatement(insert,
                PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getUserName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        if (resultSet.next()) {
            user.setId(resultSet.getInt(1));
        }
        return user.getId();
    }

    @Override
    public User login(User user) {
        return null;
    }

    @Override
    public boolean existByUsername(String username) throws SQLException {

        String query = "SELECT EXISTS (SELECT username FROM my_user WHERE username = ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getBoolean(1);
    }

    @Override
    public boolean existByEmail(String email) throws SQLException {

        String query = "SELECT EXISTS(SELECT email FROM my_user WHERE email = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getBoolean(1);
    }
}
