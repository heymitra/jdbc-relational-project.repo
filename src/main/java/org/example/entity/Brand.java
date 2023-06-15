package org.example.entity;

public class Brand {
    private long id;
    private String name;
    private String website;
    private String description;

    public Brand(long id, String name, String website, String description) {
        this.id = id;
        this.name = name;
        this.website = website;
        this.description = description;
    }

    public Brand(String name, String website, String description) {
        this.name = name;
        this.website = website;
        this.description = description;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "\nBrand: " +
                "\n\tid: " + id +
                "\n\tname: " + name +
                "\n\twebsite: " + website +
                "\n\tdescription: " + description;
    }
}
