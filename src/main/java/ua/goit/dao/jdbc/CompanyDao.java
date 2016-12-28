package ua.goit.dao.jdbc;

import ua.goit.model.jdbc.Company;
import ua.goit.view.ConsoleHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class CompanyDao implements ModelDao {

    @Override
    public void selectAllElements() {
        try {
            String sql = "SELECT * FROM companies";
            List<Company> companyList = resultSelect(sql);
            for (Company company : companyList) {
                ConsoleHelper.writeMessage(company.toString());
            }
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
    }

    @Override
    public void selectElement(int id) {
        try {
            String sql = "SELECT * FROM companies WHERE id = " + id;
            List<Company> companyList = resultSelect(sql);
            for (Company company : companyList) {
                ConsoleHelper.writeMessage(company.toString());
            }
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
    }

    @Override
    public void createElement(List list) {
        String sql = "INSERT INTO companies (company_name, company_address) VALUES(?, ?)";
        try ( PreparedStatement preparedStatement = ConnectDao.connection.prepareStatement(sql)){
            preparedStatement.setString(1,(String)list.get(0));
            preparedStatement.setString(2,(String)list.get(1));
            preparedStatement.executeUpdate();
            ConsoleHelper.writeMessage("Company was successfully created!");
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
    }

    @Override
    public void updateElement(List list) {
        String sql = "UPDATE companies SET company_name = ?,company_address = ? WHERE id =?";
        try (PreparedStatement preparedStatement = ConnectDao.connection.prepareStatement(sql)) {
            preparedStatement.setString(1,(String)list.get(1));
            preparedStatement.setString(2,(String)list.get(2));
            preparedStatement.setInt(3,(Integer)list.get(0));
            preparedStatement.executeUpdate();
            ConsoleHelper.writeMessage("Company was successfully updated!");
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
    }

    @Override
    public void deleteElement(int id) {
        String sql = "DELETE FROM companies WHERE id = ?";
        try (PreparedStatement preparedStatement = ConnectDao.connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            ConsoleHelper.writeMessage("Company was successfully deleted!");
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
    }

    private List<Company> resultSelect(String sql) throws SQLException {
            Statement statement = ConnectDao.connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            List<Company> listSelect = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("company_name");
                String address = result.getString("company_address");
                listSelect.add(new Company(id, name, address));
            }
           result.close();
           return listSelect;
    }
}
