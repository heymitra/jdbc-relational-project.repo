package org.example.entity;

public class Shareholder {
    private long id;
    private String name;
    private String phoneNumber;
    private String nationalCode;

    public Shareholder(long id, String name, String phoneNumber, String nationalCode) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.nationalCode = nationalCode;
    }

    public Shareholder(String name, String phoneNumber, String nationalCode) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.nationalCode = nationalCode;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    @Override
    public String toString() {
        return "\nShareholder: " +
                "\n\tid: " + id +
                "\n\tname: " + name +
                "\n\tphone number: " + phoneNumber +
                "\n\tnational code: " + nationalCode;
    }
}
