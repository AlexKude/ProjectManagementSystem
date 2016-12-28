package ua.goit.model.hibernate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;



@Entity
@Table(name = "projects", schema = "public")
public class ProjectEntity implements ModelEntity {
    private int id;
    private String projectName;
    List<CustomerEntity> customerList;
    List<DeveloperEntity> developerList;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projects_seq")
    @SequenceGenerator(name = "projects_seq", sequenceName = "projects_id_seq", allocationSize = 1)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "project_name")
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    @ManyToMany(mappedBy = "projectList")
    public List<CustomerEntity> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<CustomerEntity> customerList) {
        this.customerList = customerList;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "projects_developers",
            joinColumns = @JoinColumn(name = "projects",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "developers",referencedColumnName = "id"))
    public List<DeveloperEntity> getDeveloperList() {
        return developerList;
    }

    public void setDeveloperList(List<DeveloperEntity> developerList) {
        this.developerList = developerList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectEntity that = (ProjectEntity) o;

        if (id != that.id) return false;
        if (projectName != null ? !projectName.equals(that.projectName) : that.projectName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (projectName != null ? projectName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Project : " +
                "id : " + id +
                ", Name : " + projectName + '\'' +
                ", Developers which working on : " + developerList;
    }
}
