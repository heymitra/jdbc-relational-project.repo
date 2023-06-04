package org.example.service;

import org.example.entity.User;
import org.example.repository.UserRepo;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public long register(String name,
                         String username,
                         String email,
                         String password) throws SQLException {

        User user = new User(name, username, email, password);

        if (userRepo.existByUsername(username)) {
            System.out.println("This username is reserved.");
            return 0;
        }

        if (userRepo.existByEmail(email)) {
            System.out.println("User with this email address has already been registered." +
                    "\nTry to login.");
            return 0;
        }

        if (!isValidPassword(password)) {
            System.out.println("This password is not valid");
            return 0;
        }

        if (!isValidEmail(email)) {
            System.out.println("This email is not valid.");
            return 0;
        }

        long userId = userRepo.register(user);
        System.out.println("You registered successfully" +
                "\nYour ID is: " + userId);
        return userId;
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    private boolean isValidPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$");
    }

    @Override
    public User login(String username, String password) {
        return null;
    }
}