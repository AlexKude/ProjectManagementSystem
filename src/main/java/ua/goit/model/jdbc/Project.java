package ua.goit.model.jdbc;


import java.util.Set;

public class Project implements Model {

    private int projectId;
    private String projectName;
    Set<Developer> developerSet;

    public Project() {
    }

    public Project(int projectId, String projectName, Set<Developer> developerSet) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.developerSet = developerSet;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Set<Developer> getDeveloperSet() {
        return developerSet;
    }

    public void setDeveloperSet(Set<Developer> developerSet) {
        this.developerSet = developerSet;
    }

    @Override
    public String toString() {
        return "Project : " +
                "Id : " + projectId +
                ", Name : " + projectName + '\'' +
                ", Developers who working on : " + developerSet;
    }
}
