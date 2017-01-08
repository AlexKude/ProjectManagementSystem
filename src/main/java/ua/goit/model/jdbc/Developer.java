package ua.goit.model.jdbc;

import java.sql.Date;
import java.util.Set;


public class Developer implements Model {
    private int delevoperId;
    private String surname;
    private String name;
    private String fatherName;
    private Date dateOfBirth;
    private Date dateOfJoin;
    private String Address;
    private Company company;
    private Set<Skill> skills;

    public Developer() {
    }

    public Developer(int delevoperId, String surname, String name, String fatherName, Date dateOfBirth,
                     Date dateOfJoin, String address, Company company, Set<Skill> skills) {
        this.delevoperId = delevoperId;
        this.surname = surname;
        this.name = name;
        this.fatherName = fatherName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoin = dateOfJoin;
        Address = address;
        this.company = company;
        this.skills = skills;
    }

    public int getDelevoperId() {
        return delevoperId;
    }

    public void setDelevoperId(int delevoperId) {
        this.delevoperId = delevoperId;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfJoin() {
        return dateOfJoin;
    }

    public void setDateOfJoin(Date dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Developer : " +
                "Id : " + delevoperId +
                ", Family Name : " + surname + '\'' +
                ", First Name : " + name + '\'' +
                ", Father Name : " + fatherName + '\'' +
                ", Date of Birth : " + dateOfBirth +
                ", Date of Join : " + dateOfJoin +
                ", Address : " + Address + '\'' +
                ", Company : " + company +
                ", Skills : " + skills;
    }
}
