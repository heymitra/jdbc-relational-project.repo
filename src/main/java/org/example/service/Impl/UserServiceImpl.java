package org.example.service.Impl;

import org.example.entity.User;
import org.example.repository.UserRepo;
import org.example.service.UserService;
import org.example.ui.ConsoleColor;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User register(String name, String username, String email, String password) throws SQLException {
        User user = new User(name, username, email, password);
        boolean isValid = true;

        if (!isValidPassword(password)) {
            System.out.println(ConsoleColor.RED_BOLD + "This password is not valid." + ConsoleColor.RESET);
            isValid = false;
        }

        if (!isValidEmail(email)) {
            System.out.println(ConsoleColor.RED_BOLD + "This email is not valid." + ConsoleColor.RESET);
            isValid = false;
        }

        if (userRepo.existByUsername(username)) {
            System.out.println(ConsoleColor.RED_BOLD + "This username is reserved." + ConsoleColor.RESET);
            isValid = false;
        }

        if (userRepo.existByEmail(email)) {
            System.out.println(ConsoleColor.CYAN_BOLD_BRIGHT + "User with this email address has already been registered.\nTry to login." + ConsoleColor.RESET);
            isValid = false;
        }

        if (!isValid)
            return null;

        long userId = userRepo.register(user);
        System.out.println(ConsoleColor.GREEN_BOLD + "You registered and logged in successfully\nYour ID is: " + userId + ConsoleColor.RESET);

        return user;

    }

    private boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$");
    }


    @Override
    public User login(String username, String password) throws SQLException {
        return userRepo.login(new User(username, password));
    }

}
