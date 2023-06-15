package org.example.service.Impl;

import org.example.entity.JoinTable;
import org.example.repository.JoinTableRepo;
import org.example.service.JoinTableService;
import org.example.ui.ConsoleColor;

import java.sql.SQLException;
import java.util.Arrays;

public class JoinTableServiceImpl implements JoinTableService {
    private final JoinTableRepo joinTableRepo;

    public JoinTableServiceImpl(JoinTableRepo joinTableRepo) {
        this.joinTableRepo = joinTableRepo;
    }

    @Override
    public void takeShare(long shareholder_id, long brand_id) {
        try {
            joinTableRepo.insert(new JoinTable(shareholder_id, brand_id));
            System.out.println(ConsoleColor.GREEN_BOLD + "Shareholder with ID " + shareholder_id +
                    " successfully took share of brand with ID " + brand_id +ConsoleColor.RESET);
        } catch (SQLException e) {
            // Check if the error is due to a foreign key constraint violation
            if (e.getSQLState().equals("23503"))
                System.out.println(ConsoleColor.RED_BOLD + "There is not any shareholder/brand with entered IDs." + ConsoleColor.RESET);
            else
                e.printStackTrace();
        }
    }

    @Override
    public void sellShare(long shareholderId, long brandId) throws SQLException {
        if (joinTableRepo.delete(new JoinTable(shareholderId, brandId)) == 0)
            System.out.println("This record is not valid.\nThis shareholder does not have share of mentioned brand.");
        else
            System.out.println(ConsoleColor.GREEN_BOLD + "Shareholder (id: " + shareholderId +") the share of brand (id: " + brandId + ") successfully." + ConsoleColor.RESET);
    }

    @Override
    public void changeShare(long shareholderId, long oldBrandId, long newBrandId) {
        JoinTable oldJoinTable = new JoinTable(shareholderId, oldBrandId );

        try {
            if (joinTableRepo.update(oldJoinTable, newBrandId) == 0) {
                System.out.println(ConsoleColor.RED_BOLD + "Mentioned record does not exist." + ConsoleColor.RESET);
            }
        } catch (SQLException e) {
            // Check if the error is due to a foreign key constraint violation
            if (e.getSQLState().equals("23503"))
                System.out.println(ConsoleColor.RED_BOLD + "There is not any shareholder/brand with entered IDs." + ConsoleColor.RESET);
            else
                e.printStackTrace();
        }
        System.out.println(ConsoleColor.GREEN_BOLD + "You changed the share of shareholder " + shareholderId +
                " from " + oldBrandId + " to " + newBrandId + " successfully." + ConsoleColor.RESET);
    }

    @Override
    public void loadAll() throws SQLException {
        System.out.println(Arrays.toString(joinTableRepo.loadAll()));
    }
}
