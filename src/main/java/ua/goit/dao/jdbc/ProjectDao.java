package ua.goit.dao.jdbc;

import ua.goit.model.jdbc.Developer;
import ua.goit.model.jdbc.Project;
import ua.goit.view.ConsoleHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ProjectDao<ModelObject> implements ModelDao<ModelObject> {
    @Override
    public void selectAllElements() {
        try {
            String sql = "SELECT * FROM projects";
            String sqlJoin = "SELECT * FROM projects_developers JOIN developers ON projects_developers.developers = developers.id";
            List<Project> projectList = resultSelect(sql, sqlJoin);
            for (Project project : projectList) {
                ConsoleHelper.writeMessage(project.toString());
            }
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
    }

    @Override
    public void selectElement(int id) {
        try {
            String sql = "SELECT * FROM projects WHERE id = " + id;
            String sqlJoin = "SELECT * FROM projects_developers JOIN developers ON projects_developers.developers = developers.id WHERE projects =" + id;
            List<Project> projectList = resultSelect(sql, sqlJoin);
            for (Project project : projectList) {
                ConsoleHelper.writeMessage(project.toString());
            }
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
    }

    @Override
    public void createElement(ModelObject object) {
        Project project = (Project) object;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        int id = 0;
        try {
            if (!checkJoinTable(project.getDeveloperSet())) {
                return;
            }
            ConnectDao.connection.setAutoCommit(false);
            String sql = "INSERT INTO projects (project_name) VALUES(?)";
            preparedStatement = ConnectDao.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt("id");
            }
            if (!project.getDeveloperSet().isEmpty()) {
                for (Developer developer : project.getDeveloperSet()) {
                    sql = "INSERT INTO projects_developers VALUES (?,?)";
                    preparedStatement = ConnectDao.connection.prepareStatement(sql);
                    preparedStatement.setInt(1, id);
                    preparedStatement.setInt(2, developer.getDelevoperId());
                    preparedStatement.executeUpdate();
                }
            }
            ConnectDao.connection.commit();
            ConsoleHelper.writeMessage("Project was successfully created!");
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }finally {
            try {
                generatedKeys.close();
                preparedStatement.close();
            } catch (SQLException ignore) {

            }
        }
    }

    @Override
    public void updateElement(ModelObject object) {
        Project project = (Project) object;
        PreparedStatement preparedStatement = null;
        int id = 0;
        try {
            if (!checkJoinTable(project.getDeveloperSet())) {
                return;
            }
            ConnectDao.connection.setAutoCommit(false);
            String sql = "UPDATE projects SET project_name = ? WHERE id =?";
            id = project.getProjectId();
            preparedStatement = ConnectDao.connection.prepareStatement(sql);
            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setInt(2, project.getProjectId());
            preparedStatement.executeUpdate();
            sql = "DELETE FROM projects_developers WHERE projects = ?";
            preparedStatement = ConnectDao.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            if (!project.getDeveloperSet().isEmpty()) {
                for (Developer developer : project.getDeveloperSet()) {
                    sql = "INSERT INTO projects_developers VALUES (?,?)";
                    preparedStatement = ConnectDao.connection.prepareStatement(sql);
                    preparedStatement.setInt(1, id);
                    preparedStatement.setInt(2, developer.getDelevoperId());
                    preparedStatement.executeUpdate();
                }
            }
            ConnectDao.connection.commit();
            ConsoleHelper.writeMessage("Project was successfully updated!");
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException ignore) {

            }
        }
    }

    @Override
    public void deleteElement(int id) {
        PreparedStatement preparedStatement = null;
        try {
            ConnectDao.connection.setAutoCommit(false);
            String sql = "DELETE FROM projects_developers WHERE projects = ?";
            preparedStatement = ConnectDao.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            sql = "DELETE FROM projects WHERE id = ?";
            preparedStatement = ConnectDao.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            ConnectDao.connection.commit();
            ConsoleHelper.writeMessage("Project was successfully deleted!");
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException ignore) {

            }
        }
    }

    private List<Project> resultSelect(String sql, String sqlJoin) throws SQLException {
        Developer developer = new Developer();
        List<Project> projectList = new ArrayList<>();
        Statement statement = ConnectDao.connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int id = result.getInt("id");
            String name = result.getString("project_name");
            projectList.add(new Project(id, name, new HashSet<Developer>()));
        }
        result = statement.executeQuery(sqlJoin);
        while (result.next()) {
            developer.setDelevoperId(result.getInt("developers"));
            developer.setSurname(result.getString("surname"));
            developer.setName(result.getString("name"));
            developer.setFatherName(result.getString("father_name"));
            developer.setDateOfBirth(result.getDate("date_of_birth"));
            developer.setDateOfJoin(result.getDate("date_of_join"));
            developer.setAddress(result.getString("address"));
            for (Project project : projectList) {
                if (project.getProjectId() == result.getInt("projects")) {
                    project.getDeveloperSet().add(developer);
                }
            }
        }
        result.close();
        statement.close();
        return projectList;
    }

    private boolean checkJoinTable(Set<Developer> developerSet) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        String sql = "SELECT * FROM projects_developers";
        Statement statement = ConnectDao.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            ids.add(resultSet.getInt("developers"));
        }
        resultSet.close();
        statement.close();
        for (Developer developer : developerSet) {
            if (!ids.contains(developer.getDelevoperId())) {
                ConsoleHelper.writeMessage("Project with id : " + developer.getDelevoperId() + " does not exists!");
                return false;
            }
        }
        return true;
    }
}
