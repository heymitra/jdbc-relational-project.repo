package org.example.service.Impl;

import org.example.entity.Shareholder;
import org.example.repository.ShareholderRepo;
import org.example.service.ShareholderService;
import org.example.ui.ConsoleColor;

import java.sql.SQLException;

public class ShareholderServiceImpl implements ShareholderService {
    private final ShareholderRepo shareholderRepo;

    public ShareholderServiceImpl(ShareholderRepo shareholderRepo) {
        this.shareholderRepo = shareholderRepo;
    }

    @Override
    public void add(String name, String phoneNumber, String nationalCode) {
        if (!isValidPhoneNumber(phoneNumber)) {
            System.out.println(ConsoleColor.RED_BOLD + "This is not a valid phone number." + ConsoleColor.RESET);
            return;
        }
        if (!isValidNationalCode(nationalCode)) {
            System.out.println(ConsoleColor.RED_BOLD + "This is not a valid national code." + ConsoleColor.RESET);
            return;
        }

        try {
            long insertedId = shareholderRepo.insert(new Shareholder(name, phoneNumber, nationalCode));
            System.out.println(ConsoleColor.GREEN_BOLD + "You have successfully added shareholder.\nShareholder ID is: " + insertedId + ConsoleColor.RESET);
        } catch (SQLException e) {
            // Check if the error is due to a unique constraint violation
            if (e.getSQLState().equals("23505")) {
                System.out.println(ConsoleColor.RED_BOLD + "Shareholder with this national code already exists." + ConsoleColor.RESET);
                return;
            }
            e.printStackTrace();
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("09(1[0-9]|3[1-9]|2[1-9])-?[0-9]{3}-?[0-9]{4}");
    }

    private boolean isValidNationalCode(String nationalCode) {
        return nationalCode.matches("^\\d{10}$") &&
                !nationalCode.matches("^(\\d)\\1{9}$") && isValidNationalCodeChecksum(nationalCode);
    }

    private boolean isValidNationalCodeChecksum(String nationalCode) {
        char[] digits = nationalCode.toCharArray();
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(digits[i]) * (10 - i);
        }
        int lastDigit = Character.getNumericValue(digits[9]);
        int remainder = sum % 11;
        if (remainder < 2) {
            return lastDigit == remainder;
        } else {
            return lastDigit == 11 - remainder;
        }
    }

    @Override
    public void removeById(long id) throws SQLException {
        if (shareholderRepo.delete(id) == 0)
            System.out.println(ConsoleColor.RED_BOLD + "No shareholder found with this ID." + ConsoleColor.RESET);
        else
            System.out.println(ConsoleColor.GREEN_BOLD + "Shareholder has been removed successfully." + ConsoleColor.RESET);
    }

    @Override
    public void editByID(long id, String name, String phoneNumber, String nationalCode) throws SQLException {
        Shareholder oldShareholder = shareholderRepo.loadById(id);
        if (oldShareholder == null) {
            System.out.println(ConsoleColor.RED_BOLD + "Shareholder with this ID does not exist." + ConsoleColor.RESET);
            return;
        }

        if (name.trim().isEmpty())
            name = oldShareholder.getName();

        if (phoneNumber.trim().isEmpty())
            phoneNumber = oldShareholder.getPhoneNumber();
        else {
            if (!isValidPhoneNumber(phoneNumber)) {
                System.out.println(ConsoleColor.RED_BOLD + "This is not a valid phone number." + ConsoleColor.RESET);
                return;
            }
        }

        if (nationalCode.trim().isEmpty())
            nationalCode = oldShareholder.getNationalCode();
        else {
            if (!isValidNationalCode(nationalCode)) {
                System.out.println(ConsoleColor.RED_BOLD + "This is not a valid national code." + ConsoleColor.RESET);
                return;
            }
        }

        try {
            shareholderRepo.update(new Shareholder(id, name, phoneNumber, nationalCode));
            System.out.println(ConsoleColor.GREEN_BOLD + "Shareholder info has been updated successfully." + ConsoleColor.RESET);
        } catch (SQLException e) {
            // Check if the error is due to a unique constraint violation
            if (e.getSQLState().equals("23505")) {
                System.out.println(ConsoleColor.RED_BOLD + "This national code already exists." + ConsoleColor.RESET);
                return;
            }
            e.printStackTrace();
        }
    }

    @Override
    public void findById(long id) throws SQLException {
        Shareholder shareholder = shareholderRepo.loadById(id);
        if (shareholder == null)
            System.out.println(ConsoleColor.RED_BOLD + "Shareholder with this ID does not exist." + ConsoleColor.RESET);
        else
            System.out.println(shareholder);
    }
}
