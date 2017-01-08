package ua.goit.model.hibernate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Set;


@Entity
@Table(name = "skills", schema = "public")
public class SkillEntity implements ModelEntity {
    private int id;
    private String skillName;
    Set<DeveloperEntity> developerSet;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skills_seq")
    @SequenceGenerator(name = "skills_seq", sequenceName = "skills_id_seq", allocationSize = 1)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "skill_name")
    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    @ManyToMany(mappedBy = "skills")
    public Set<DeveloperEntity> getDeveloperSet() {
        return developerSet;
    }

    public void setDeveloperSet(Set<DeveloperEntity> developerSet) {
        this.developerSet = developerSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SkillEntity that = (SkillEntity) o;

        if (id != that.id) return false;
        if (skillName != null ? !skillName.equals(that.skillName) : that.skillName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (skillName != null ? skillName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Skill : " +
                "id : " + id +
                ", Name : " + skillName;
    }
}
