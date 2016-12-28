package ua.goit.dao.jdbc;

import ua.goit.model.jdbc.Developer;
import ua.goit.view.ConsoleHelper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class DeveloperDao implements ModelDao {
    @Override
    public void selectAllElements() {
        try {
            String sql = "SELECT * FROM developers";
            List<Developer> developerList = resultSelect(sql);
            for (Developer developer : developerList) {
                ConsoleHelper.writeMessage(developer.toString());
            }
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
    }

    @Override
    public void selectElement(int id) {
        try {
            String sql = "SELECT * FROM developers WHERE id = " + id;
            List<Developer> developerList = resultSelect(sql);
            for (Developer developer : developerList) {
                ConsoleHelper.writeMessage(developer.toString());
            }
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }

    }

    @Override
    public void createElement(List list) {
        try {
            if (!checkForeignKey((Integer) list.get(6))) {
                ConsoleHelper.writeMessage("Company with typed id does not exist. Please check and try again");
                return;
            }
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
        String sql = "INSERT INTO developers (surname, name, father_name, date_of_birth, date_of_join, address, company) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = ConnectDao.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, (String) list.get(0));
            preparedStatement.setString(2, (String) list.get(1));
            preparedStatement.setString(3, (String) list.get(2));
            preparedStatement.setDate(4, (Date) list.get(3));
            preparedStatement.setDate(5, (Date) list.get(4));
            preparedStatement.setString(6, (String) list.get(5));
            preparedStatement.setInt(7, (Integer) list.get(6));
            preparedStatement.executeUpdate();
            ConsoleHelper.writeMessage("Developer was successfully created!");
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }

    }

    @Override
    public void updateElement(List list) {
        try {
            if (!checkForeignKey((Integer) list.get(7))) {
                ConsoleHelper.writeMessage("Company with typed id does not exist. Please check and try again");
                return;
            }
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
        String sql = "UPDATE developers SET surname = ?,name = ?, father_name = ?, date_of_birth = ?, date_of_join = ?, address = ?, company = ? WHERE id =?";
        try (PreparedStatement preparedStatement = ConnectDao.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, (String) list.get(1));
            preparedStatement.setString(2, (String) list.get(2));
            preparedStatement.setString(3, (String) list.get(3));
            preparedStatement.setDate(4, (Date) list.get(4));
            preparedStatement.setDate(5, (Date) list.get(5));
            preparedStatement.setString(6, (String) list.get(6));
            preparedStatement.setInt(7, (Integer) list.get(7));
            preparedStatement.setInt(8, (Integer) list.get(0));
            preparedStatement.executeUpdate();
            ConsoleHelper.writeMessage("Company was successfully updated!");
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
    }

    @Override
    public void deleteElement(int id) {
        String sql = "DELETE FROM developers WHERE id = ?";
        try (PreparedStatement preparedStatement = ConnectDao.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            ConsoleHelper.writeMessage("Developer was successfully deleted!");
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
    }

    private List<Developer> resultSelect(String sql) throws SQLException {
        Statement statement = ConnectDao.connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        List<Developer> listSelect = new ArrayList<>();
        while (result.next()) {
            int id = result.getInt("id");
            String surname = result.getString("surname");
            String name = result.getString("name");
            String fatherName = result.getString("father_name");
            Date dateOfBirth = result.getDate("date_of_birth");
            Date dateOfJoin = result.getDate("date_of_join");
            String address = result.getString("address");
            int company = result.getInt("company");
            listSelect.add(new Developer(id, surname, name, fatherName, dateOfBirth, dateOfJoin, address, company));
        }
        result.close();
        return listSelect;
    }

    private boolean checkForeignKey(Integer key) throws SQLException {
        String sql = "SELECT id FROM companies";
        Statement statement = ConnectDao.connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            if (key.equals(result.getInt("id"))) ;
            return true;
        }
        return false;
    }
}
