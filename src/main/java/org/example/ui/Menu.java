package org.example.ui;

import org.example.entity.User;
import org.example.util.ApplicationContext;
import org.example.util.Constant;
import org.example.util.SecurityContext;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);
    public static boolean stop = false;

    public static void run() throws SQLException {
        System.out.println(ConsoleColor.GREEN_BOLD_BRIGHT + "WELCOME \uD83D\uDC4B\uD83D\uDE0A" + ConsoleColor.RESET);
        int choice;
        do {
            System.out.println(ConsoleColor.YELLOW_BOLD + Constant.startMenu + ConsoleColor.RESET);
            System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter your choice: " + ConsoleColor.RESET);
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3: {
                    stop = true;
                    System.out.println(ConsoleColor.GREEN_BOLD + "Exiting the program. Goodbye! \uD83D\uDC4B" + ConsoleColor.RESET);
                    break;
                }
                default:
                    invalidInput();
                    break;
            }
        } while (choice != 0);
    }

    public static void register() throws SQLException {
        String name = askForInfo("name");
        String username = askForInfo("username");
        String email = askForInfo("email");
        String password = askForInfo("password");

        User toBeRegisteredUser = ApplicationContext.getUserService().register(name, username, email, password);

        if (toBeRegisteredUser == null) {
            System.out.println(ConsoleColor.RED_BOLD + "Registration failed." + ConsoleColor.RESET);
            return;
        }

        SecurityContext.username = username;
        SecurityContext.id = toBeRegisteredUser.getId();

        menu();
    }

    public static void login() throws SQLException {
        String username = askForInfo("username");
        String password = askForInfo("password");

        User toBeLoggedInUser = ApplicationContext.getUserService().login(username, password);

        if (toBeLoggedInUser == null) {
            System.out.println(ConsoleColor.RED_BOLD + "Username or Password is incorrect." + ConsoleColor.RESET);
            return;
        }

        SecurityContext.username = username;
        SecurityContext.id = toBeLoggedInUser.getId();
        System.out.println(ConsoleColor.GREEN_BOLD + "Successfully logged in." + ConsoleColor.RESET);

        menu();
    }

    private static String askForInfo(String info) {
        System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter " + info + ": " + ConsoleColor.RESET);
        return scanner.next();
    }

    private static void menu() throws SQLException {
        int choice;
        do {
            System.out.println(ConsoleColor.YELLOW_BOLD + Constant.menu + ConsoleColor.RESET);
            System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter your choice: " + ConsoleColor.RESET);
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    categoryMenu();
                    break;
                case 2:
                    brandMenu();
                    break;
                case 3:
                    productMenu();
                    break;
                case 4:
                    shareholderMenu();
                    break;
                case 0: {
                    SecurityContext.id = 0;
                    SecurityContext.username = null;
                    break;
                }
                default:
                    invalidInput();
                    break;
            }
            System.out.println();
        } while (choice != 0);
    }

    private static void categoryMenu() throws SQLException {
        int choice;
        do {
            System.out.println(ConsoleColor.YELLOW_BOLD + Constant.categoryMenu + ConsoleColor.RESET);
            System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter your choice: " + ConsoleColor.RESET);
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: {
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Category Name: " + ConsoleColor.RESET);
                    String name = scanner.nextLine();
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Category Description: " + ConsoleColor.RESET);
                    String description = scanner.nextLine();
                    ApplicationContext.getCategoryService().add(name, description);
                    break;
                }
                case 2: {
                    System.out.println(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter the ID: " + ConsoleColor.RESET);
                    ApplicationContext.getCategoryService().removeById(scanner.nextLong());
                    break;
                }
                case 3: {
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Please enter the ID of category you want to edit: " + ConsoleColor.RESET);
                    long id = scanner.nextInt();
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter new name or leave it unchanged: " + ConsoleColor.RESET);
                    String name = scanner.nextLine();
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter new description or leave it unchanged: " + ConsoleColor.RESET);
                    String description = scanner.nextLine();
                    ApplicationContext.getCategoryService().editById(id, name, description);
                    break;
                }
                case 4: {
                    System.out.println(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter the ID: " + ConsoleColor.RESET);
                    ApplicationContext.getCategoryService().findById(scanner.nextLong());
                    break;
                }
                case 5: {
                    ApplicationContext.getCategoryService().showAll();
                    break;
                }
                case 6: {
                    menu();
                    break;
                }
                case 0:
                    break;
                default:
                    invalidInput();
                    break;
            }
            System.out.println();
        } while (choice != 0);
    }

    private static void brandMenu() throws SQLException {
        int choice;
        do {
            System.out.println(ConsoleColor.YELLOW_BOLD + Constant.brandMenu + ConsoleColor.RESET);
            System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter your choice: " + ConsoleColor.RESET);
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: {
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Brand Name: " + ConsoleColor.RESET);
                    String name = scanner.nextLine();
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Brand Website: " + ConsoleColor.RESET);
                    String website = scanner.nextLine();
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Brand Description: " + ConsoleColor.RESET);
                    String description = scanner.nextLine();
                    ApplicationContext.getBrandService().add(name, website, description);
                    break;
                }
                case 2: {
                    System.out.println(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter the ID: " + ConsoleColor.RESET);
                    ApplicationContext.getBrandService().removeById(scanner.nextLong());
                    break;
                }
                case 3: {
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Please enter the ID of brand you want to edit: " + ConsoleColor.RESET);
                    long id = scanner.nextInt();
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter new name or leave it unchanged: " + ConsoleColor.RESET);
                    String name = scanner.nextLine();
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter new website or leave it unchanged: " + ConsoleColor.RESET);
                    String website = scanner.nextLine();
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter new description or leave it unchanged: " + ConsoleColor.RESET);
                    String description = scanner.nextLine();
                    ApplicationContext.getBrandService().editById(id, name, website, description);
                    break;
                }
                case 4: {
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter the ID: " + ConsoleColor.RESET);
                    ApplicationContext.getBrandService().findById(scanner.nextLong());
                    break;
                }
                case 5: {
                    ApplicationContext.getBrandService().showAll();
                    break;
                }
                case 6: {
                    menu();
                    break;
                }
                case 0:
                    break;
                default:
                    invalidInput();
                    break;
            }
            System.out.println();
        } while (choice != 0);
    }

    private static void productMenu() throws SQLException {
        int choice;
        do {
            System.out.println(ConsoleColor.YELLOW_BOLD + Constant.productMenu + ConsoleColor.RESET);
            System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter your choice: " + ConsoleColor.RESET);
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: {
                    if (!ApplicationContext.getBrandService().isEmpty() || !ApplicationContext.getCategoryService().isEmpty()) {
                        System.out.println(ConsoleColor.CYAN_BOLD_BRIGHT + "Cannot add a new product. Not any Brands and/or Categories have been created yet." + ConsoleColor.RESET);
                        return;
                    }
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Product Name: " + ConsoleColor.RESET);
                    String name = scanner.nextLine();
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Creation Date: " + ConsoleColor.RESET);
                    String creationDate = scanner.nextLine();
                    System.out.print(ConsoleColor.CYAN_BOLD_BRIGHT + "You are provided with category list below, check and select one by entering category ID: " + ConsoleColor.RESET);
                    ApplicationContext.getCategoryService().showAll();
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Category ID: " + ConsoleColor.RESET);
                    long categoryId = scanner.nextLong();
                    System.out.print(ConsoleColor.CYAN_BOLD_BRIGHT + "You are provided with brand list below, check and select one by entering brand ID: " + ConsoleColor.RESET);
                    ApplicationContext.getBrandService().showAll();
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Select a brand by entering brand ID: " + ConsoleColor.RESET);
                    long brandId = scanner.nextLong();
                    ApplicationContext.getProductService().add(name, creationDate, categoryId, brandId);
                    break;
                }
                case 2: {
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter the ID: " + ConsoleColor.RESET);
                    ApplicationContext.getProductService().removeById(scanner.nextLong());
                    break;
                }
                case 3: {
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Please enter the ID of product you want to edit: " + ConsoleColor.RESET);
                    long id = scanner.nextInt();
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter new name or leave it unchanged: " + ConsoleColor.RESET);
                    String name = scanner.nextLine();
                    scanner.next();
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter new creation date or leave it unchanged: " + ConsoleColor.RESET);
                    String creationDate = scanner.nextLine();
                    scanner.next();
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter new category ID or leave it unchanged: " + ConsoleColor.RESET);
                    long categoryId = scanner.nextLong();
                    System.out.println(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter new brand ID or leave it unchanged: " + ConsoleColor.RESET);
                    long brandId = scanner.nextLong();
                    ApplicationContext.getProductService().editById(id, name, creationDate, categoryId, brandId);
                    break;
                }
                case 4: {
                    System.out.println(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter the ID: " + ConsoleColor.RESET);
                    long id = scanner.nextLong();
                    ApplicationContext.getProductService().findById(id);
                    break;
                }
                case 0: {
                    menu();
                    break;
                }
                default:
                    invalidInput();
                    break;
            }
            System.out.println();
        } while (choice != 0);
    }


    private static void shareholderMenu() throws SQLException {
        int choice;
        do {
            System.out.println(ConsoleColor.YELLOW_BOLD + Constant.shareholderMenu + ConsoleColor.RESET);
            System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter your choice: " + ConsoleColor.RESET);
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: {
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter name: " + ConsoleColor.RESET);
                    String name = scanner.nextLine();
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter national code: " + ConsoleColor.RESET);
                    String nationalCode = scanner.nextLine();
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter phone number: " + ConsoleColor.RESET);
                    String phoneNumber = scanner.nextLine();
                    ApplicationContext.getShareholderService().add(name, phoneNumber, nationalCode);
                    break;
                }
                case 2: {
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter the ID: " +ConsoleColor.RESET);
                    ApplicationContext.getShareholderService().removeById(scanner.nextLong());
                    break;
                }
                case 3: {
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter the ID of shareholder you want to edite: " + ConsoleColor.RESET);
                    long id = scanner.nextLong();
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter new name or leave it unchanged: " + ConsoleColor.RESET);
                    String name = scanner.nextLine();
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter new national code or leave it unchanged: " + ConsoleColor.RESET);
                    String nationalCode = scanner.nextLine();
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter new phone number or leave it unchanged: " + ConsoleColor.RESET);
                    String phoneNumber = scanner.nextLine();
                    ApplicationContext.getShareholderService().editByID(id, name, nationalCode, phoneNumber);
                    break;
                }
                case 4: {
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter the ID: " + ConsoleColor.RESET);
                    ApplicationContext.getShareholderService().findById(scanner.nextLong());
                    break;
                }
                case 5: {
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter the ID of shareholder who wants to take share: " + ConsoleColor.RESET);
                    long shareholderId = scanner.nextLong();
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter brand ID: " + ConsoleColor.RESET);
                    long brandId = scanner.nextLong();
                    ApplicationContext.getJoinTableService().takeShare(shareholderId, brandId);
                    break;
                }
                case 6: {
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter the ID of shareholder who wants to sell share: " + ConsoleColor.RESET);
                    long shareholderId = scanner.nextLong();
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter brand ID: " + ConsoleColor.RESET);
                    long brandId = scanner.nextLong();
                    ApplicationContext.getJoinTableService().sellShare(shareholderId, brandId);
                    break;
                }
                case 7: {
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter the shareholder ID: " + ConsoleColor.RESET);
                    long shareholderId = scanner.nextLong();
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter to-be-changed brand ID: " + ConsoleColor.RESET);
                    long oldBrandId = scanner.nextLong();
                    System.out.print(ConsoleColor.BLUE_BOLD_BRIGHT + "Enter the new brand ID that the shareholder wants to change the share to: " + ConsoleColor.RESET);
                    long newBrandId = scanner.nextLong();
                    ApplicationContext.getJoinTableService().changeShare(shareholderId, oldBrandId, newBrandId);
                    break;
                }
                case 8: {
                    ApplicationContext.getJoinTableService().loadAll();
                    break;
                }
                case 0: {
                    menu();
                    break;
                }
                default:
                    invalidInput();
                    break;
            }
            System.out.println();
        } while (choice != 0);
    }

    private static void invalidInput() {
        System.out.println(ConsoleColor.RED_BOLD + "Invalid input. Please try again." + ConsoleColor.RESET);
    }

}
