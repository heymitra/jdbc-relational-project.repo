package org.example.service.Impl;

import org.example.entity.Brand;
import org.example.repository.BrandRepo;
import org.example.service.BrandService;
import org.example.ui.ConsoleColor;

import java.sql.SQLException;

public class BrandServiceImpl implements BrandService {
    private final BrandRepo brandRepo;

    public BrandServiceImpl(BrandRepo brandRepo) {
        this.brandRepo = brandRepo;
    }

    @Override
    public void add(String name, String website, String description) {

        if (!isValidWebAddress(website)) {
            System.out.println(ConsoleColor.RED_BOLD + "This is not a valid address for a website." + ConsoleColor.RESET);
            return;
        }

        try {
            long insertedId = brandRepo.insert(new Brand(name, website, description));
            System.out.println(ConsoleColor.GREEN_BOLD + "You have successfully created new brand.\nBrand ID is: " + insertedId + ConsoleColor.RESET);
        } catch (SQLException e) {
            // Check if the error is due to a unique constraint violation
            if (e.getSQLState().equals("23505")) {
                System.out.println(ConsoleColor.RED_BOLD + "This brand name is reserved." + ConsoleColor.RESET);
                return;
            }
            e.printStackTrace();
        }
    }

    private boolean isValidWebAddress(String website) {
        return website.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    }

    @Override
    public void removeById(long id) {
        int affectedRows = 0;
        try {
            affectedRows = brandRepo.delete(id);
        } catch (SQLException e) {
            // Check if the error is due to a foreign key constraint violation
            if (e.getSQLState().equals("23503")) {
                System.out.println(ConsoleColor.RED_BOLD + "You cannot delete this brand, since some products belongs to this brand." + ConsoleColor.RESET);
                return;
            }
            e.printStackTrace();
        }
        if (affectedRows == 0)
            System.out.println(ConsoleColor.RED_BOLD + "There is not any brand with this ID." + ConsoleColor.RESET);
        else
            System.out.println(ConsoleColor.GREEN_BOLD + "Brand is removed successfully." + ConsoleColor.RESET);
    }

    @Override
    public void editById(long id, String name, String website, String description) throws SQLException {
        Brand oldBrand = brandRepo.loadById(id);
        if (oldBrand == null) {
            System.out.println(ConsoleColor.RED_BOLD + "There is not any brand with this ID." + ConsoleColor.RESET);
            return;
        }

        if (description.trim().isEmpty())
            description = oldBrand.getDescription();

        if (website.trim().isEmpty())
            website = oldBrand.getWebsite();
        else {
            if (!isValidWebAddress(website)) {
                System.out.println(ConsoleColor.RED_BOLD + "This is not a valid address for a website." + ConsoleColor.RESET);
                return;
            }
        }

        if (name.trim().trim().isEmpty())
            name = oldBrand.getName();

        try {
            brandRepo.update(new Brand(id, name, website, description));
            System.out.println(ConsoleColor.GREEN_BOLD + "Brand is updated successfully." + ConsoleColor.RESET);
        } catch (SQLException e) {
            // Check if the error is due to a unique constraint violation
            if (e.getSQLState().equals("23505")) {
                System.out.println(ConsoleColor.RED_BOLD + "This brand name is reserved." +ConsoleColor.RESET);
                return;
            }
            e.printStackTrace();
        }
    }

    @Override
    public void findById(long id) throws SQLException {
        Brand brand = brandRepo.loadById(id);
        if (brand == null)
            System.out.println(ConsoleColor.RED_BOLD + "There is not any brand with this ID." +ConsoleColor.RESET);
        else
            System.out.println(brand);
    }

    @Override
    public void showAll() throws SQLException {
        Brand[] brands =brandRepo.loadAll();
        for (int i =0; i< brands.length; i++){
            System.out.println(ConsoleColor.PURPLE_BOLD_BRIGHT +  brands[i] + ConsoleColor.RESET);
        }
    }

    @Override
    public boolean isEmpty() throws SQLException {
        return brandRepo.isEmpty();
    }
}
