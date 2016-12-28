package ua.goit.model.jdbc;


public class Company implements Model {
    private int companyId;
    private String companyName;
    private String companyAddress;

    public Company(int companyId, String companyName, String companyAddress) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    @Override
    public String toString() {
        return "Company: " +
                "Id : " + companyId +
                ", Name : " + companyName + '\'' +
                ", Address : " + companyAddress + '\'';
    }
}
