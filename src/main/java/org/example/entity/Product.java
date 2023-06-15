package org.example.entity;

public class Product {
    private long id;
    private String name;
    private String creationDate;
    private long categoryId;
    private long brandId;
    private Category category;
    private Brand brand;

    public Product(long id, String name, String creationDate, long categoryId, long brandId) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.categoryId = categoryId;
        this.brandId = brandId;
    }

    public Product(String name, String creationDate, long categoryId, long brandId) {
        this.name = name;
        this.creationDate = creationDate;
        this.categoryId = categoryId;
        this.brandId = brandId;
    }

    public Product(long id, String name, String creationDate, Category category, Brand brand) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.category = category;
        this.brand = brand;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "\nProduct:" +
                "\n\tid: " + id +
                "\n\tname: " + name +
                "\n\tcreationDate: " + creationDate +
                category +
                brand;
    }
}
