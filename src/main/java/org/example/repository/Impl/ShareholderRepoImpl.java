package org.example.repository.Impl;

import org.example.entity.Shareholder;
import org.example.repository.ShareholderRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShareholderRepoImpl implements ShareholderRepo {
    private final Connection connection;

    public ShareholderRepoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long insert(Shareholder shareholder) throws SQLException {
        String insert = "INSERT INTO shareholder (name, phone_number, national_code) VALUES (?, ?, ?);";

        PreparedStatement preparedStatement = connection.prepareStatement(insert,
                PreparedStatement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, shareholder.getName());
        preparedStatement.setString(2, shareholder.getPhoneNumber());
        preparedStatement.setString(3, shareholder.getNationalCode());
        preparedStatement.execute();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        if (resultSet.next()) {
            shareholder.setId(resultSet.getInt(1));
        }
        return shareholder.getId();
    }

    @Override
    public int delete(long id) throws SQLException {
        String delete = "DELETE FROM shareholder WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setLong(1, id);
        return preparedStatement.executeUpdate();
    }

    @Override
    public int update(Shareholder shareholder) throws SQLException {
        String update = "UPDATE shareholder SET name = ?, phone_number= ?, national_code = ? WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, shareholder.getName());
        preparedStatement.setString(2, shareholder.getPhoneNumber());
        preparedStatement.setString(3, shareholder.getNationalCode());
        preparedStatement.setLong(4, shareholder.getId());
        return preparedStatement.executeUpdate();
    }

    @Override
    public Shareholder loadById(long id) throws SQLException {
        String load = "SELECT * FROM shareholder WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(load);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return new Shareholder(id,
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4));
        } else
            return null;
    }
}
