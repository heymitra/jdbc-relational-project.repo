package org.example.repository.Impl;

import org.example.entity.Brand;
import org.example.entity.Category;
import org.example.entity.Product;
import org.example.repository.ProductRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRepoImpl implements ProductRepo {
    private final Connection connection;

    public ProductRepoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long insert(Product product) throws SQLException {
        String insert = "INSERT INTO product (name, create_date, category_id, brand_id) VALUES (?, ?, ?, ?);";

        PreparedStatement preparedStatement = connection.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2, product.getCreationDate());
        preparedStatement.setLong(3, product.getCategoryId());
        preparedStatement.setLong(4, product.getBrandId());
        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            product.setId(resultSet.getInt(1));
        }
        return product.getId();
    }

    @Override
    public int delete(Long id) throws SQLException {
        String delete = "DELETE FROM product WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setLong(1, id);
        return preparedStatement.executeUpdate();
    }

    @Override
    public void update(Long id, Product product) throws SQLException {
        String update = "UPDATE product SET name = ?, create_date = ?, category_id = ?, brand_id = ? WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2, product.getCreationDate());
        preparedStatement.setLong(3, product.getCategoryId());
        preparedStatement.setLong(4, product.getBrandId());
        preparedStatement.setLong(5, id);
        preparedStatement.execute();
    }

    @Override
    public boolean existById(Long id) throws SQLException {
        String query = "SELECT EXISTS (SELECT id FROM product WHERE id = ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getBoolean(1);
    }

    @Override
    public Product loadById(Long id) throws SQLException {
        String load = "SELECT p.id, p.name, create_date, " +
        "c.id, c.name, c.description, " +
        "b.id, b.name, b.website, b.description " +
        "FROM product p " +
        "JOIN category c ON p.category_id = c.id " +
        "JOIN brand b ON b.id = p.brand_id WHERE p.id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(load);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Category category = new Category(resultSet.getLong(4),
                    resultSet.getString(5),
                    resultSet.getString(6));
            Brand brand = new Brand(resultSet.getLong(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10));
            return new Product(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    category,
                    brand);
        } else
            return null;
    }
}
