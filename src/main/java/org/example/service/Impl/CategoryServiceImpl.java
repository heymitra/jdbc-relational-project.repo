package org.example.service.Impl;

import org.example.entity.Category;
import org.example.repository.CategoryRepo;
import org.example.service.CategoryService;
import org.example.ui.ConsoleColor;

import java.sql.SQLException;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void add(String name, String description) {

        try {
            long insertedId = categoryRepo.insert(new Category(name, description));
            System.out.println(ConsoleColor.GREEN_BOLD + "You have successfully created new category.\nCategory ID is: " + insertedId + ConsoleColor.RESET);
        } catch (SQLException e) {
            // Check if the error is due to a unique constraint violation
            if (e.getSQLState().equals("23505")) {
                System.out.println(ConsoleColor.RED_BOLD + "This category already exists." +ConsoleColor.RESET);
                return;
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeById(long id) {
        int affectedRows = 0;
        try {
            affectedRows = categoryRepo.delete(id);
        } catch (SQLException e) {
            // Check if the error is due to a foreign key constraint violation
            if (e.getSQLState().equals("23503")) {
                System.out.println(ConsoleColor.GREEN_BOLD + "You cannot delete this category, since some products belongs to this category." + ConsoleColor.RESET);
                return;
            }
            e.printStackTrace();
        }
        if (affectedRows == 0)
            System.out.println(ConsoleColor.RED_BOLD + "There is not any category with this ID." +ConsoleColor.RESET);
        else
            System.out.println(ConsoleColor.GREEN_BOLD + "Category removed successfully." + ConsoleColor.RESET);
    }

    @Override
    public void editById(long id, String name, String description) throws SQLException {
        Category oldCategory = categoryRepo.loadById(id);
        if (oldCategory == null) {
            System.out.println(ConsoleColor.RED_BOLD + "There is not any category with this ID." +ConsoleColor.RESET);
            return;
        }

        if (description.trim().isEmpty())
            description = oldCategory.getDescription();
        if (name.trim().isEmpty())
            name = oldCategory.getName();

        try {
            categoryRepo.update(new Category(id, name, description));
            System.out.println(ConsoleColor.GREEN_BOLD + "Category is updated successfully." + ConsoleColor.RESET);
        } catch (SQLException e) {
            // Check if the error is due to a unique constraint violation
            if (e.getSQLState().equals("23505")) {
                System.out.println(ConsoleColor.RED_BOLD + "Category name is duplicated." +ConsoleColor.RESET);
                return;
            }
            e.printStackTrace();
        }
    }

    @Override
    public void findById(long id) throws SQLException {
        Category category = categoryRepo.loadById(id);
        if (category == null)
            System.out.println(ConsoleColor.RED_BOLD + "There is not any category with this ID." +ConsoleColor.RESET);
        else
            System.out.println(category);
    }

    @Override
    public void showAll() throws SQLException {
        Category[] categories = categoryRepo.loadAll();
        for (int i =0; i< categories.length; i++){
            System.out.println(ConsoleColor.PURPLE_BOLD_BRIGHT + categories[i] + ConsoleColor.RESET);
        }
    }

    @Override
    public boolean isEmpty() throws SQLException {
        return categoryRepo.isEmpty();
    }
}
