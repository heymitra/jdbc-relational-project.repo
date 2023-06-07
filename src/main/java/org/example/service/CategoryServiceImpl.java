package org.example.service;

import org.example.entity.Category;
import org.example.repository.CategoryRepo;

import java.sql.SQLException;
import java.util.Objects;

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void create(String name, String description) throws SQLException {
        Category category = new Category(name, description);

        if (categoryRepo.existByName(name))
            System.out.println("This category already exists.");
        else
            System.out.println("You have successfully created new category.\nCategory ID is: " + categoryRepo.insert(category));
    }

    @Override
    public void remove(String name) throws SQLException {
        Category category = new Category(name);
        if (!categoryRepo.existByName(name))
            System.out.println("This category does not exist.");
        else {
            categoryRepo.delete(category);
            System.out.println("Category removed successfully.");
        }
    }

    @Override
    public void edit(String oldName, String name, String description) throws SQLException {
        Category oldCategory = categoryRepo.loadByName(oldName);

        if (oldCategory == null)
            System.out.println("This category does not exist.");

        else {
            if (Objects.equals(name.trim(), ""))
                name = oldName;
            else {
                if (categoryRepo.existByName(name)) {
                    System.out.println("This category name exists.");
                    return;
                }
            }

            if (Objects.equals(description.trim(), ""))
                description = oldCategory.getDescription();

            Category newCategory = new Category(name, description);
            categoryRepo.update(oldName, newCategory);
            System.out.println("Category is updated successfully.");
        }
    }

    @Override
    public void show(String name) throws SQLException {
        if (!categoryRepo.existByName(name))
            System.out.println("There is not any category with this name.");
        else
            System.out.println(categoryRepo.loadByName(name));
    }
}
