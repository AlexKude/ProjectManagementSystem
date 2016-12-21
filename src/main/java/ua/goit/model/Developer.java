package ua.goit.model;

import java.util.Date;

/**
 * Created by Main Server on 16.12.2016.
 */
public class Developer implements Model {
    private int delevoperId;
    private String surname;
    private String name;
    private String fatherName;
    private Date dateOfBirth;
    private Date dateOfJoin;
    private String Address;
    private int companyId;


    public Developer(int delevoperId, String surname, String name, String fatherName, Date dateOfBirth, Date dateOfJoin, String address, int companyId) {
        this.delevoperId = delevoperId;
        this.surname = surname;
        this.name = name;
        this.fatherName = fatherName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoin = dateOfJoin;
        Address = address;
        this.companyId = companyId;
    }

    public int getDelevoperId() {
        return delevoperId;
    }

    public void setDelevoperId(int delevoperId) {
        this.delevoperId = delevoperId;
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
                ", Company Id : " + companyId;
    }
}
