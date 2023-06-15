package org.example.repository.Impl;

import org.example.entity.Brand;
import org.example.repository.BrandRepo;

import java.sql.*;

public class BrandRepoImpl implements BrandRepo {
    private final Connection connection;

    public BrandRepoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long insert(Brand brand) throws SQLException {
        String insert = "INSERT INTO brand (name, website, description) VALUES (?, ?, ?);";

        PreparedStatement preparedStatement = connection.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, brand.getName());
        preparedStatement.setString(2, brand.getWebsite());
        preparedStatement.setString(3, brand.getDescription());
        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            brand.setId(resultSet.getInt(1));
        }
        return brand.getId();
    }

    @Override
    public int delete(long id) throws SQLException {
        String delete = "DELETE FROM brand WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setLong(1, id);
        return preparedStatement.executeUpdate();
    }

    @Override
    public int update(Brand brand) throws SQLException {
        String update = "UPDATE brand SET name = ?, website= ?, description = ? WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, brand.getName());
        preparedStatement.setString(2, brand.getWebsite());
        preparedStatement.setString(3, brand.getDescription());
        preparedStatement.setLong(4, brand.getId());
        return preparedStatement.executeUpdate();
    }

    @Override
    public boolean existByName(String name) throws SQLException {
        String query = "SELECT EXISTS (SELECT name FROM brand WHERE name = ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getBoolean(1);
    }

    @Override
    public Brand loadByName(String name) throws SQLException {
        String load = "SELECT * FROM brand WHERE name = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(load);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new Brand(resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4));
        } else
            return null;
    }

    @Override
    public Brand loadById(long id) throws SQLException {
        String load = "SELECT * FROM brand WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(load);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new Brand(id,
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4));
        } else
            return null;
    }

    @Override
    public Brand[] loadAll() throws SQLException {
        String findAll = "SELECT * FROM brand";
        PreparedStatement preparedStatement = connection.prepareStatement(findAll,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.last();
        int len = resultSet.getRow();
        Brand[] brands = new Brand[len];
        resultSet.beforeFirst();
        for (int i = 0; i < len; i++) {
            if (resultSet.next()) {
                brands[i] = new Brand(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4));
            }
        }
        return brands;
    }

    @Override
    public boolean isEmpty() {
        String query = "SELECT 1 FROM brand LIMIT 1";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            return resultSet.next();
        } catch (SQLException e) {
            // Table does not exist or other SQL error occurred
            return false;
        }
    }
}
