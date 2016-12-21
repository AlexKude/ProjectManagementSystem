package ua.goit.model;

/**
 * Created by Main Server on 16.12.2016.
 */
public class Project implements Model {

    private int projectId;
    private String projectName;


    public Project(int projectId, String projectName) {
        this.projectId = projectId;
        this.projectName = projectName;
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

    @Override
    public String toString() {
        return "Project : " +
                "Id : " + projectId +
                ", Title : "  + projectName + '\'';
    }
}
