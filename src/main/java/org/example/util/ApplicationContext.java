package org.example.util;

import org.example.config.DatabaseConnector;
import org.example.repository.*;
import org.example.repository.Impl.*;
import org.example.service.*;
import org.example.service.Impl.*;

import java.sql.Connection;

public class ApplicationContext {
    private static Connection connection;
    private static UserRepo userRepo;
    private static UserService userService;
    private static BrandRepo brandRepo;
    private static BrandService brandService;
    private static CategoryRepo categoryRepo;
    private static CategoryService categoryService;
    private static ProductRepo productRepo;
    private static ProductService productService;
    private static ShareholderRepo shareholderRepo;
    private static ShareholderService shareholderService;
    private static JoinTableRepo joinTableRepo;
    private static JoinTableService joinTableService;

    static {
        connection = new DatabaseConnector().getConnection();
        userRepo = new UserRepoImpl(connection);
        userService = new UserServiceImpl(userRepo);
        brandRepo = new BrandRepoImpl(connection);
        brandService = new BrandServiceImpl(brandRepo);
        categoryRepo = new CategoryRepoImpl(connection);
        categoryService = new CategoryServiceImpl(categoryRepo);
        productRepo = new ProductRepoImpl(connection);
        productService = new ProductServiceImpl(productRepo);
        shareholderRepo = new ShareholderRepoImpl(connection);
        shareholderService = new ShareholderServiceImpl(shareholderRepo);
        joinTableRepo = new JoinTableRepoImpl(connection);
        joinTableService = new JoinTableServiceImpl(joinTableRepo);
    }

    public static UserService getUserService() {
        return userService;
    }

    public static BrandService getBrandService() {
        return brandService;
    }

    public static CategoryService getCategoryService() {
        return categoryService;
    }

    public static ProductService getProductService() {
        return productService;
    }

    public static ShareholderService getShareholderService() {
        return shareholderService;
    }

    public static JoinTableService getJoinTableService() {
        return joinTableService;
    }
}
