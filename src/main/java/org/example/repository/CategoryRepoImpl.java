package org.example.repository;

import org.example.entity.Brand;
import org.example.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRepoImpl implements CategoryRepo {
    private final Connection connection;

    public CategoryRepoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long insert(Category category) throws SQLException {
        String insert = "INSERT INTO category (name, description) VALUES (?, ?);";

        PreparedStatement preparedStatement = connection.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, category.getName());
        preparedStatement.setString(2, category.getDescription());
        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            category.setId(resultSet.getInt(1));
        }
        return category.getId();
    }

    @Override
    public void delete(Category category) throws SQLException {
        String delete = "DELETE FROM category WHERE name = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setString(1, category.getName());
        preparedStatement.execute();
    }

    @Override
    public void update(String name, Category category) throws SQLException {
        String update = "UPDATE category SET name = ?, description = ? WHERE name = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, category.getName());
        preparedStatement.setString(2, category.getDescription());
        preparedStatement.setString(3, name);
        preparedStatement.execute();
    }

    @Override
    public boolean existByName(String name) throws SQLException {
        String query = "SELECT EXISTS (SELECT name FROM category WHERE name = ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getBoolean(1);
    }

    @Override
    public Category loadByName(String name) throws SQLException {
        String load = "SELECT * FROM category WHERE name = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(load);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new Category(resultSet.getString(2),
                    resultSet.getString(3));
        } else
            return null;
    }
}
