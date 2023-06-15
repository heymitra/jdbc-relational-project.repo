package org.example.repository.Impl;

import org.example.entity.Category;
import org.example.entity.User;
import org.example.repository.CategoryRepo;

import java.sql.*;

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
    public int delete(long id) throws SQLException {
        String delete = "DELETE FROM category WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setLong(1, id);
        return preparedStatement.executeUpdate();
    }

    @Override
    public int update(Category category) throws SQLException {
        String update = "UPDATE category SET name = ?, description = ? WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, category.getName());
        preparedStatement.setString(2, category.getDescription());
        preparedStatement.setLong(3, category.getId());
        return preparedStatement.executeUpdate();
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

        if (resultSet.next())
            return new Category(name, resultSet.getString(3));
        else
            return null;
    }

    @Override
    public Category loadById(long id) throws SQLException {
        String load = "SELECT * FROM category WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(load);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next())
            return new Category(id, resultSet.getString(2), resultSet.getString(3));
        else
            return null;
    }

    @Override
    public Category[] loadAll() throws SQLException {
        String findAll = "SELECT * FROM category";
        PreparedStatement preparedStatement = connection.prepareStatement(findAll,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.last();
        int len = resultSet.getRow();
        Category[] categories = new Category[len];
        resultSet.beforeFirst();
        for (int i = 0; i < len; i++) {
            if (resultSet.next()) {
                categories[i] = new Category(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
            }
        }
        return categories;
    }

    @Override
    public boolean isEmpty() {
        String query = "SELECT 1 FROM category LIMIT 1";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            return resultSet.next();
        } catch (SQLException e) {
            // Table does not exist or other SQL error occurred
            return false;
        }
    }

}
