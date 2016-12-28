package ua.goit.dao.jdbc;

import ua.goit.model.jdbc.Project;
import ua.goit.view.ConsoleHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class ProjectDao implements ModelDao {
    @Override
    public void selectAllElements() {
        try {
            String sql = "SELECT * FROM projects";
            List<Project> projectList = resultSelect(sql);
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
            List<Project> projectList = resultSelect(sql);
            for (Project project : projectList) {
                ConsoleHelper.writeMessage(project.toString());
            }
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
    }

    @Override
    public void createElement(List list) {
        String sql = "INSERT INTO projects (project_name) VALUES(?)";
        try ( PreparedStatement preparedStatement = ConnectDao.connection.prepareStatement(sql)){
            preparedStatement.setString(1,(String)list.get(0));
            preparedStatement.executeUpdate();
            ConsoleHelper.writeMessage("Project was successfully created!");
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
    }

    @Override
    public void updateElement(List list) {
        String sql = "UPDATE projects SET project_name = ? WHERE id =?";
        try (PreparedStatement preparedStatement = ConnectDao.connection.prepareStatement(sql)) {
            preparedStatement.setString(1,(String)list.get(1));
            preparedStatement.setInt(2,(Integer)list.get(0));
            preparedStatement.executeUpdate();
            ConsoleHelper.writeMessage("Project was successfully updated!");
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
    }

    @Override
    public void deleteElement(int id) {
        String sql = "DELETE FROM projects WHERE id = ?";
        try (PreparedStatement preparedStatement = ConnectDao.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            ConsoleHelper.writeMessage("Project was successfully deleted!");
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
    }

    private List<Project> resultSelect(String sql) throws SQLException {
        Statement statement = ConnectDao.connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        List<Project> listSelect = new ArrayList<>();
        while (result.next()) {
            int id = result.getInt("id");
            String name = result.getString("project_name");
            listSelect.add(new Project(id, name));
        }
        result.close();
        return listSelect;
    }
}
