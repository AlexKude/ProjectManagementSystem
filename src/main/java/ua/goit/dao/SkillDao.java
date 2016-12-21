package ua.goit.dao;

import ua.goit.model.Skill;
import ua.goit.view.ConsoleHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class SkillDao implements ModelDao {
    @Override
    public void selectAllElements() {
        try {
            String sql = "SELECT * FROM skills";
            List<Skill> skillList = resultSelect(sql);
            for (Skill skill : skillList) {
                ConsoleHelper.writeMessage(skill.toString());
            }
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
    }

    @Override
    public void selectElement(int id) {
        try {
            String sql = "SELECT * FROM skills WHERE id = " + id;
            List<Skill> skillList = resultSelect(sql);
            for (Skill skill : skillList) {
                ConsoleHelper.writeMessage(skill.toString());
            }
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
    }

    @Override
    public void createElement(List list) {
        String sql = "INSERT INTO skills (skill_name) VALUES(?)";
        try (PreparedStatement preparedStatement = ConnectDao.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, (String) list.get(0));
            preparedStatement.executeUpdate();
            ConsoleHelper.writeMessage("Skill was successfully created!");
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
    }

    @Override
    public void updateElement(List list) {
        String sql = "UPDATE skills SET skill_name = ? WHERE id =?";
        try (PreparedStatement preparedStatement = ConnectDao.connection.prepareStatement(sql)) {
            preparedStatement.setString(1,(String)list.get(1));
            preparedStatement.setInt(2,(Integer)list.get(0));
            preparedStatement.executeUpdate();
            ConsoleHelper.writeMessage("Skill was successfully updated!");
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
    }

    @Override
    public void deleteElement(int id) {
        String sql = "DELETE FROM skills WHERE id = ?";
        try (PreparedStatement preparedStatement = ConnectDao.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            ConsoleHelper.writeMessage("Skill was successfully deleted!");
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
    }

    private List<Skill> resultSelect(String sql) throws SQLException {
        Statement statement = ConnectDao.connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        List<Skill> listSelect = new ArrayList<>();
        while (result.next()) {
            int id = result.getInt("id");
            String name = result.getString("skill_name");
            listSelect.add(new Skill(id, name));
        }
        result.close();
        return listSelect;
    }
}
