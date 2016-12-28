package ua.goit.dao.jdbc;

import ua.goit.model.jdbc.Customer;
import ua.goit.view.ConsoleHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class CustomerDao implements ModelDao {
    @Override
    public void selectAllElements() {
        try {
            String sql = "SELECT * FROM customers";
            List<Customer> customerList = resultSelect(sql);
            for (Customer customer : customerList) {
                ConsoleHelper.writeMessage(customer.toString());
            }
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
    }

    @Override
    public void selectElement(int id) {
        try {
            String sql = "SELECT * FROM customers WHERE id = " + id;
            List<Customer> customerList = resultSelect(sql);
            for (Customer customer : customerList) {
                ConsoleHelper.writeMessage(customer.toString());
            }
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
    }

    @Override
    public void createElement(List list) {
        try {
            if (!checkForeignKey((Integer) list.get(3))) {
                ConsoleHelper.writeMessage("Company with typed id does not exist. Please check and try again");
                return;
            }
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
        String sql = "INSERT INTO customers (surname, name, father_name, company) VALUES(?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = ConnectDao.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, (String) list.get(0));
            preparedStatement.setString(2, (String) list.get(1));
            preparedStatement.setString(3, (String) list.get(2));
            preparedStatement.setInt(4, (Integer) list.get(3));
            preparedStatement.executeUpdate();
            ConsoleHelper.writeMessage("Customer was successfully created!");
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
    }

    @Override
    public void updateElement(List list) {
        try {
            if (!checkForeignKey((Integer) list.get(4))) {
                ConsoleHelper.writeMessage("Company with typed id does not exist. Please check and try again");
                return;
            }
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
        String sql = "UPDATE customers SET surname = ?,name = ?, father_name = ?, company = ? WHERE id =?";
        try (PreparedStatement preparedStatement = ConnectDao.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, (String) list.get(1));
            preparedStatement.setString(2, (String) list.get(2));
            preparedStatement.setString(3, (String) list.get(3));
            preparedStatement.setInt(4, (Integer) list.get(4));
            preparedStatement.setInt(5, (Integer) list.get(0));
            preparedStatement.executeUpdate();
            ConsoleHelper.writeMessage("Customer was successfully updated!");
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }

    }

    @Override
    public void deleteElement(int id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        try (PreparedStatement preparedStatement = ConnectDao.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            ConsoleHelper.writeMessage("Customer was successfully deleted!");
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }

    }

    private List<Customer> resultSelect(String sql) throws SQLException {
        Statement statement = ConnectDao.connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        List<Customer> listSelect = new ArrayList<>();
        while (result.next()) {
            int id = result.getInt("id");
            String surname = result.getString("surname");
            String name = result.getString("name");
            String fatherName = result.getString("father_name");
            int company = result.getInt("company");
            listSelect.add(new Customer(id, surname, name, fatherName, company));
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
