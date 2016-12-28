package ua.goit.model.jdbc;


public class Customer implements Model {
    private int customerId;
    private String surname;
    private String name;
    private String fatherName;
    private int companyId;

     public Customer(int customerId, String surname, String name, String fatherName, int companyId) {
        this.customerId = customerId;
        this.surname = surname;
        this.name = name;
        this.fatherName = fatherName;
        this.companyId = companyId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    @Override
    public String toString() {
        return "Customer : " +
                "Id : " + customerId +
                ", Family Name : " + surname + '\'' +
                ", First Name : " + name + '\'' +
                ", Father Name : " + fatherName + '\'' +
                ", Company Id : " + companyId;
    }
}
