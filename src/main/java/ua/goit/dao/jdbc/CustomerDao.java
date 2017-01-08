package ua.goit.dao.jdbc;

import ua.goit.model.jdbc.Company;
import ua.goit.model.jdbc.Customer;
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



public class CustomerDao<ModelObject> implements ModelDao<ModelObject> {
    @Override
    public void selectAllElements() {
        try {
            String sql = "SELECT * FROM customers JOIN companies ON customers.company = companies.id";
            String sqlJoin = "SELECT * FROM customers_projects JOIN projects ON customers_projects.project = projects.id ";
            List<Customer> customerList = resultSelect(sql, sqlJoin);
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
            String sql = "SELECT * FROM customers JOIN companies ON customers.company = companies.id WHERE customers.id = " + id;
            String sqlJoin = "SELECT * FROM customers_projects JOIN projects ON customers_projects.project = projects.id WHERE customer = " + id;
            List<Customer> customerList = resultSelect(sql, sqlJoin);
            for (Customer customer : customerList) {
                ConsoleHelper.writeMessage(customer.toString());
            }
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }
    }

    @Override
    public void createElement(ModelObject object) {
        Customer customer = (Customer) object;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        int id = 0;
        try {
            if (!checkForeignKey(customer.getCompany().getCompanyId())) {
                ConsoleHelper.writeMessage("Company with typed id does not exist. Please check and try again");
                return;
            }
            if (!checkJoinTable(customer.getProjectSet())) {
                return;
            }
            ConnectDao.connection.setAutoCommit(false);
            String sql = "INSERT INTO customers (surname, name, father_name, company) VALUES(?, ?, ?, ?)";
            preparedStatement = ConnectDao.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, customer.getSurname());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getFatherName());
            preparedStatement.setInt(4, customer.getCompany().getCompanyId());
            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt("id");
            }
            if (!customer.getProjectSet().isEmpty()) {
                for (Project project : customer.getProjectSet()) {
                    sql = "INSERT INTO customers_projects VALUES (?,?)";
                    preparedStatement = ConnectDao.connection.prepareStatement(sql);
                    preparedStatement.setInt(1, id);
                    preparedStatement.setInt(2, project.getProjectId());
                    preparedStatement.executeUpdate();
                }
            }
            ConnectDao.connection.commit();
            ConsoleHelper.writeMessage("Customer was successfully created!");
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        } finally {
            try {
                generatedKeys.close();
                preparedStatement.close();
            } catch (SQLException ignore) {

            }
        }
    }

    @Override
    public void updateElement(ModelObject object) {
        Customer customer = (Customer) object;
        PreparedStatement preparedStatement = null;
        int id = 0;
        try {
            if (!checkForeignKey(customer.getCompany().getCompanyId())) {
                ConsoleHelper.writeMessage("Company with typed id does not exist. Please check and try again");
                return;
            }
            if (!checkJoinTable(customer.getProjectSet())) {
                return;
            }
            ConnectDao.connection.setAutoCommit(false);
            String sql = "UPDATE customers SET surname = ?,name = ?, father_name = ?, company = ? WHERE id =?";
            id = customer.getCustomerId();
            preparedStatement = ConnectDao.connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getSurname());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getFatherName());
            preparedStatement.setInt(4, customer.getCompany().getCompanyId());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
            sql = "DELETE FROM customers_projects WHERE customer = ?";
            preparedStatement = ConnectDao.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            if (!customer.getProjectSet().isEmpty()) {
                for (Project project : customer.getProjectSet()) {
                    sql = "INSERT INTO customers_projects VALUES (?,?)";
                    preparedStatement = ConnectDao.connection.prepareStatement(sql);
                    preparedStatement.setInt(1, id);
                    preparedStatement.setInt(2, project.getProjectId());
                    preparedStatement.executeUpdate();
                }
            }
            ConnectDao.connection.commit();
            ConsoleHelper.writeMessage("Customer was successfully updated!");
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
            String sql = "DELETE FROM customers_projects WHERE customer = ?";
            preparedStatement = ConnectDao.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            sql = "DELETE FROM customers WHERE id = ?";
            preparedStatement = ConnectDao.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            ConnectDao.connection.commit();
            ConsoleHelper.writeMessage("Customer was successfully deleted!");
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException ignore) {

            }
        }
    }

    private List<Customer> resultSelect(String sql, String sqlJoin) throws SQLException {
        Company company = new Company();
        Project project = new Project();
        List<Customer> customerList = new ArrayList<>();
        Statement statement = ConnectDao.connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int id = result.getInt("id");
            String surname = result.getString("surname");
            String name = result.getString("name");
            String fatherName = result.getString("father_name");
            company.setCompanyId(result.getInt("company"));
            company.setCompanyName(result.getString("company_name"));
            company.setCompanyAddress(result.getString("company_address"));
            customerList.add(new Customer(id, surname, name, fatherName, company, new HashSet<Project>()));
        }
        result = statement.executeQuery(sqlJoin);
        while (result.next()) {
            project.setProjectId(result.getInt("project"));
            project.setProjectName(result.getString("project_name"));
            for (Customer customer : customerList) {
                if (customer.getCustomerId() == result.getInt("customer")) {
                    customer.getProjectSet().add(project);
                }
            }
        }
        result.close();
        statement.close();
        return customerList;
    }

    private boolean checkForeignKey(Integer key) throws SQLException {
        String sql = "SELECT id FROM companies";
        Statement statement = ConnectDao.connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            if (key.equals(result.getInt("id"))) {
                result.close();
                statement.close();
                return true;
            }
        }
        result.close();
        statement.close();
        return false;
    }

    private boolean checkJoinTable(Set<Project> projectSet) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        String sql = "SELECT * FROM customers_projects";
        Statement statement = ConnectDao.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            ids.add(resultSet.getInt("project"));
        }
        resultSet.close();
        statement.close();
        for (Project project : projectSet) {
            if (!ids.contains(project.getProjectId())) {
                ConsoleHelper.writeMessage("Project with id : " + project.getProjectId() + " does not exists!");
                return false;
            }
        }
        return true;
    }
}
