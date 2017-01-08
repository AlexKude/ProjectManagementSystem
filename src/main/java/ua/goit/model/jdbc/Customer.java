package ua.goit.model.jdbc;


import java.util.Set;

public class Customer implements Model {
    private int customerId;
    private String surname;
    private String name;
    private String fatherName;
    private Company company;
    Set<Project> projectSet;

    public Customer() {
    }

    public Customer(int customerId, String surname, String name, String fatherName, Company company, Set<Project> projectSet) {
        this.customerId = customerId;
        this.surname = surname;
        this.name = name;
        this.fatherName = fatherName;
        this.company = company;
        this.projectSet = projectSet;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Project> getProjectSet() {
        return projectSet;
    }

    public void setProjectSet(Set<Project> projectSet) {
        this.projectSet = projectSet;
    }

    @Override
    public String toString() {
        return "Customer : " +
                "id : " + customerId +
                ", Family name : " + surname + '\'' +
                ", First name : " + name + '\'' +
                ", Father name : " + fatherName + '\'' +
                ", Company : " + company +
                ", Projects ; " + projectSet;
    }
}
