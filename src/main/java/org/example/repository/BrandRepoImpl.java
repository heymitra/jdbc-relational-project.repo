package org.example.repository;

import org.example.entity.Brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public void delete(Brand brand) throws SQLException {
        String delete = "DELETE FROM brand WHERE name = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setString(1, brand.getName());
        preparedStatement.execute();
    }

    @Override
    public void update(String name, Brand brand) throws SQLException {
        String update = "UPDATE brand SET name = ?, website= ?, description = ? WHERE name = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, brand.getName());
        preparedStatement.setString(2, brand.getWebsite());
        preparedStatement.setString(3, brand.getDescription());
        preparedStatement.setString(4, name);
        preparedStatement.execute();
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
        }
        return null;
    }
}
