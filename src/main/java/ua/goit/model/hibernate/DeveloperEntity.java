package ua.goit.model.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Set;


@Entity
@Table(name = "developers", schema = "public")
public class DeveloperEntity implements ModelEntity {
    private int id;
    private String surname;
    private String name;
    private String fatherName;
    private Date dateOfBirth;
    private Date dateOfJoin;
    private String address;
    private CompanyEntity company;
    Set<SkillEntity> skills;
    Set<ProjectEntity> projectSet;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "developers_seq")
    @SequenceGenerator(name = "developers_seq", sequenceName = "developers_id_seq", allocationSize = 1)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "father_name")
    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    @Column(name = "date_of_birth")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "date_of_join")
    public Date getDateOfJoin() {
        return dateOfJoin;
    }

    public void setDateOfJoin(Date dateOfJoin) {
        this.dateOfJoin = dateOfJoin;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company")
    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "developers_skills",
            joinColumns = @JoinColumn(name = "developers",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "skills",referencedColumnName = "id"))
    public Set<SkillEntity> getSkills() {
        return skills;
    }

    public void setSkills(Set<SkillEntity> skills) {
        this.skills = skills;
    }
    @ManyToMany(mappedBy = "developerSet")
    public Set<ProjectEntity> getProjectSet() {
        return projectSet;
    }

    public void setProjectSet(Set<ProjectEntity> projectList) {
        this.projectSet = projectList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeveloperEntity that = (DeveloperEntity) o;

        if (id != that.id) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (fatherName != null ? !fatherName.equals(that.fatherName) : that.fatherName != null) return false;
        if (dateOfBirth != null ? !dateOfBirth.equals(that.dateOfBirth) : that.dateOfBirth != null) return false;
        if (dateOfJoin != null ? !dateOfJoin.equals(that.dateOfJoin) : that.dateOfJoin != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (fatherName != null ? fatherName.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (dateOfJoin != null ? dateOfJoin.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Developer : " +
                "id : " + id +
                ", Family Name : " + surname + '\'' +
                ", First Name : " + name + '\'' +
                ", Father Name : " + fatherName + '\'' +
                ", Date of Birth : " + dateOfBirth +
                ", Date of Join : " + dateOfJoin +
                ", Address : " + address + '\'' +
                ", Company : " + company +
                ", Skills : " + skills;
    }
}
