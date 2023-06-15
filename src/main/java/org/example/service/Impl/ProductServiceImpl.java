package org.example.service.Impl;

import org.example.entity.Product;
import org.example.repository.ProductRepo;
import org.example.service.ProductService;
import org.example.ui.ConsoleColor;

import java.sql.SQLException;

public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public void add(String name, String creationDate, Long categoryId, Long brandId) {

        try {
            System.out.println(ConsoleColor.GREEN_BOLD + "Product added successfully.\nProduct ID is: "
                    + productRepo.insert(new Product(name, creationDate, categoryId, brandId)) + ConsoleColor.RESET);
        } catch (SQLException e) {
            // Check if the error is due to a foreign key constraint violation
            if (e.getSQLState().equals("23503"))
                System.out.println(ConsoleColor.RED_BOLD + "There is no Brand/Category to match entered ID." + ConsoleColor.RESET);
            else
                e.printStackTrace();
        }
    }

    @Override
    public void removeById(Long id) throws SQLException {
        if (productRepo.delete(id) == 0)
            System.out.println("There is not any product with this ID number.");
        else
            System.out.println(ConsoleColor.GREEN_BOLD + "Product deleted successfully." + ConsoleColor.RESET);
    }

    @Override
    public void editById(Long id, String name, String creationDate, Long categoryId, Long brandId) throws SQLException {
        Product oldProduct = productRepo.loadById(id);
        if (oldProduct == null) {
            System.out.println(ConsoleColor.RED_BOLD + "There is not any product with this product ID to be edited." + ConsoleColor.RESET);
            return;
        }

        if (name.trim().isEmpty())
            name = oldProduct.getName();

        if (creationDate.trim().isEmpty())
            creationDate = oldProduct.getCreationDate();

        try {
            Product newProduct = new Product(id, name, creationDate, categoryId, brandId);
            productRepo.update(id, newProduct);
            System.out.println(ConsoleColor.GREEN_BOLD + "Brand is updated successfully." + ConsoleColor.RESET);
        } catch (SQLException e) {
            // Check if the error is due to a foreign key constraint violation
            if (e.getSQLState().equals("23503"))
                System.out.println(ConsoleColor.RED_BOLD + "There is not any category/brand with entered IDs to be assigned." + ConsoleColor.RESET);
            else
                e.printStackTrace();
        }
    }

    @Override
    public void findById(Long id) throws SQLException {
        Product product = productRepo.loadById(id);
        if(product == null)
            System.out.println(ConsoleColor.RED_BOLD + "There is not any product wit this ID." + ConsoleColor.RESET);
        else
            System.out.println(product);
    }
}
