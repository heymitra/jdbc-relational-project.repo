package org.example.service;

import org.example.entity.Brand;
import org.example.repository.BrandRepo;

import java.sql.SQLException;
import java.util.Objects;

public class BrandServiceImpl implements BrandService {
    private final BrandRepo brandRepo;

    public BrandServiceImpl(BrandRepo brandRepo) {
        this.brandRepo = brandRepo;
    }

    @Override
    public void create(String name, String website, String description) throws SQLException {
        Brand brand = new Brand(name, website, description);

        if (brandRepo.existByName(name))
            System.out.println("This brand name is reserved.");
        else if (!isValidWebAddress(website))
            System.out.println("This is not a valid address for a website.");
        else
            System.out.println("You have successfully created new brand.\nBrand ID is: " + brandRepo.insert(brand));
    }

    private boolean isValidWebAddress(String website) {
        return website.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    }

    @Override
    public void remove(String name) throws SQLException {
        Brand brand = new Brand(name);
        if (!brandRepo.existByName(name))
            System.out.println("There is not any brand with this name.");
        else {
            brandRepo.delete(brand);
            System.out.println("Brand removed successfully.");
        }
    }

    @Override
    public void edit(String oldName, String name, String website, String description) throws SQLException {

        Brand oldBrand = brandRepo.loadByName(oldName);

        if (oldBrand == null)
            System.out.println("There is not any brand with this name.");

        else {
            if (Objects.equals(name.trim(), "")) name = oldName;
            else {
                if (brandRepo.existByName(name)){
                    System.out.println("This brand name is reserved.");
                    return;
                }
            }

            if (Objects.equals(website.trim(), "")) website = oldBrand.getWebsite();
            else {
                if (!isValidWebAddress(website)) {
                    System.out.println("This is not a valid address for a website.");
                    return;
                }
            }

            if (Objects.equals(description.trim(), "")) description = oldBrand.getDescription();

            Brand newBrand = new Brand(name, website, description);
            brandRepo.update(oldName, newBrand);
            System.out.println("Brand updated successfully.");
        }
    }

    @Override
    public void show(String name) throws SQLException {
        if (!brandRepo.existByName(name))
            System.out.println("There is not any brand with this name.");
        else
            System.out.println(brandRepo.loadByName(name));
    }
}
