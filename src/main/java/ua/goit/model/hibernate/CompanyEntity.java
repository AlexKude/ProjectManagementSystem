package ua.goit.model.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Set;


@Entity
@Table(name = "companies", schema = "public")
public class CompanyEntity implements ModelEntity {
    private int id;
    private String companyName;
    private String companyAddress;
    private Set<CustomerEntity> customerSet;
    private Set<DeveloperEntity> developerSet;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companies_seq")
    @SequenceGenerator(name = "companies_seq", sequenceName = "companies_id_seq", allocationSize = 1)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Column(name = "company_address")
    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    @OneToMany(mappedBy = "company")
    public Set<CustomerEntity> getCustomerSet() {
        return customerSet;
    }

    public void setCustomerSet(Set<CustomerEntity> customerList) {
        this.customerSet = customerList;
    }

    @OneToMany(mappedBy = "company")
    public Set<DeveloperEntity> getDeveloperSet() {
        return developerSet;
    }

    public void setDeveloperSet(Set<DeveloperEntity> developerList) {
        this.developerSet = developerList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanyEntity that = (CompanyEntity) o;

        if (id != that.id) return false;
        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) return false;
        if (companyAddress != null ? !companyAddress.equals(that.companyAddress) : that.companyAddress != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (companyAddress != null ? companyAddress.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Company : " +
                "id : " + id +
                ", Company Name : " + companyName + '\'' +
                ", Company Address : " + companyAddress;
    }
}
