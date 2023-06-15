package org.example.repository.Impl;

import org.example.entity.Brand;
import org.example.entity.JoinTable;
import org.example.entity.Shareholder;
import org.example.repository.JoinTableRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinTableRepoImpl implements JoinTableRepo {
    private final Connection connection;

    public JoinTableRepoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(JoinTable joinTable) throws SQLException {
        String insert = "INSERT INTO joinTable (shareholder_id, brand_id) VALUES (?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(insert);
        preparedStatement.setLong(1, joinTable.getShareholderId());
        preparedStatement.setLong(2, joinTable.getBrandId());
        preparedStatement.execute();
    }

    @Override
    public int delete(JoinTable joinTable) throws SQLException {
        String delete = "DELETE FROM join_table WHERE shareholder_id = ? AND brand_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(delete);
        preparedStatement.setLong(1, joinTable.getShareholderId());
        preparedStatement.setLong(2, joinTable.getBrandId());
        return preparedStatement.executeUpdate();
    }

    @Override
    public int update(JoinTable oldJoinTable, long newBrandId) throws SQLException {
        String update = "UPDATE join_table SET brand_id= ? WHERE shareholder_id = ? AND brand_id= ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setLong(1, newBrandId);
        preparedStatement.setLong(2, oldJoinTable.getShareholderId());
        preparedStatement.setLong(3, oldJoinTable.getBrandId());
        return preparedStatement.executeUpdate();
    }

    @Override
    public JoinTable[] loadAll() throws SQLException {
        String loadAll = "SELECT s.name, phone_number, national_code, b.name, website, description\n" +
                "FROM join_table\n" +
                "         JOIN shareholder s on join_table.shareholder_id = s.id\n" +
                "         JOIN brand b on b.id = join_table.brand_id;";
        PreparedStatement preparedStatement = connection.prepareStatement(loadAll,
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.last();
        int len = resultSet.getRow();
        JoinTable[] joinTables = new JoinTable[len];
        resultSet.beforeFirst();

        for (int i = 0; i < len; i++) {
            if (resultSet.next()) {
                Shareholder shareholder = new Shareholder(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
                Brand brand = new Brand(resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6));
                joinTables[i] = new JoinTable(shareholder, brand);
            }
        }
        return joinTables;
    }
}
