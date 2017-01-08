package ua.goit.dao.jdbc;

import ua.goit.model.jdbc.Company;
import ua.goit.model.jdbc.Developer;
import ua.goit.model.jdbc.Skill;
import ua.goit.view.ConsoleHelper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class DeveloperDao<ModelObject> implements ModelDao<ModelObject> {
    @Override
    public void selectAllElements() {
        try {
            String sql = "SELECT * FROM developers JOIN companies ON developers.company = companies.id";
            String sqlJoin = "SELECT * FROM developers_skills JOIN skills ON developers_skills.skills = skills.id";
            List<Developer> developerList = resultSelect(sql, sqlJoin);
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
            String sql = "SELECT * FROM developers JOIN companies ON developers.company = companies.id WHERE developers.id = " + id;
            String sqlJoin = "SELECT * FROM developers_skills JOIN skills ON developers_skills.skills = skills.id WHERE developers = " + id;
            List<Developer> developerList = resultSelect(sql, sqlJoin);
            for (Developer developer : developerList) {
                ConsoleHelper.writeMessage(developer.toString());
            }
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }

    }

    @Override
    public void createElement(ModelObject object) {
        Developer developer = (Developer) object;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        int id = 0;
        try {
            if (!checkForeignKey(developer.getCompany().getCompanyId())) {
                ConsoleHelper.writeMessage("Company with typed id does not exist. Please check and try again");
                return;
            }
            if (!checkJoinTable(developer.getSkills())) {
                return;
            }
            ConnectDao.connection.setAutoCommit(false);
            String sql = "INSERT INTO developers (surname, name, father_name, date_of_birth, date_of_join, address, company) VALUES(?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = ConnectDao.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, developer.getSurname());
            preparedStatement.setString(2, developer.getName());
            preparedStatement.setString(3, developer.getFatherName());
            preparedStatement.setDate(4, developer.getDateOfBirth());
            preparedStatement.setDate(5, developer.getDateOfJoin());
            preparedStatement.setString(6, developer.getAddress());
            preparedStatement.setInt(7, developer.getCompany().getCompanyId());
            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt("id");
            }
            if (!developer.getSkills().isEmpty()) {
                for (Skill skill : developer.getSkills()) {
                    sql = "INSERT INTO developers_skills VALUES (?,?)";
                    preparedStatement = ConnectDao.connection.prepareStatement(sql);
                    preparedStatement.setInt(1, id);
                    preparedStatement.setInt(2, skill.getSkillId());
                    preparedStatement.executeUpdate();
                }
            }
            ConnectDao.connection.commit();
            ConsoleHelper.writeMessage("Developer was successfully created!");
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
        Developer developer = (Developer) object;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        int id = 0;
        try {
            if (!checkForeignKey(developer.getCompany().getCompanyId())) {
                ConsoleHelper.writeMessage("Company with typed id does not exist. Please check and try again");
                return;
            }
            if (!checkJoinTable(developer.getSkills())) {
                return;
            }
            String sql = "UPDATE developers SET surname = ?,name = ?, father_name = ?, date_of_birth = ?, date_of_join = ?, address = ?, company = ? WHERE id =?";
            id = developer.getDelevoperId();
            preparedStatement = ConnectDao.connection.prepareStatement(sql);
            preparedStatement.setString(1, developer.getSurname());
            preparedStatement.setString(2, developer.getName());
            preparedStatement.setString(3, developer.getFatherName());
            preparedStatement.setDate(4, developer.getDateOfBirth());
            preparedStatement.setDate(5, developer.getDateOfJoin());
            preparedStatement.setString(6, developer.getAddress());
            preparedStatement.setInt(7, developer.getCompany().getCompanyId());
            preparedStatement.setInt(8, id);
            preparedStatement.executeUpdate();
            sql = "DELETE FROM developers_skills WHERE developers = ?";
            preparedStatement = ConnectDao.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            if (!developer.getSkills().isEmpty()) {
                for (Skill skill : developer.getSkills()) {
                    sql = "INSERT INTO developers_skills VALUES (?,?)";
                    preparedStatement = ConnectDao.connection.prepareStatement(sql);
                    preparedStatement.setInt(1, id);
                    preparedStatement.setInt(2, skill.getSkillId());
                    preparedStatement.executeUpdate();
                }
            }
            ConnectDao.connection.commit();
            ConsoleHelper.writeMessage("Developer was successfully updated!");
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
            String sql = "DELETE FROM developers_skills WHERE developers = ?";
            preparedStatement = ConnectDao.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            sql = "DELETE FROM developers WHERE id = ?";
            preparedStatement = ConnectDao.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            ConnectDao.connection.commit();
            ConsoleHelper.writeMessage("Developer was successfully deleted!");
        } catch (SQLException e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
        }finally {
            try {
                preparedStatement.close();
            } catch (SQLException ignore) {

            }
        }
    }

    private List<Developer> resultSelect(String sql, String sqlJoin) throws SQLException {
        Company company = new Company();
        Skill skill = new Skill();
        List<Developer> developerList = new ArrayList<>();
        Statement statement = ConnectDao.connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int id = result.getInt("id");
            String surname = result.getString("surname");
            String name = result.getString("name");
            String fatherName = result.getString("father_name");
            Date dateOfBirth = result.getDate("date_of_birth");
            Date dateOfJoin = result.getDate("date_of_join");
            String address = result.getString("address");
            company.setCompanyId(result.getInt("company"));
            company.setCompanyName(result.getString("company_name"));
            company.setCompanyAddress(result.getString("company_address"));
            developerList.add(new Developer(id, surname, name, fatherName, dateOfBirth,
                    dateOfJoin, address, company, new HashSet<Skill>()));
        }
        result = statement.executeQuery(sqlJoin);
        while (result.next()) {
            skill.setSkillId(result.getInt("skills"));
            skill.setSkillName(result.getString("skill_name"));
            for (Developer developer : developerList) {
                if (developer.getDelevoperId() == result.getInt("developers")) {
                    developer.getSkills().add(skill);
                }
            }
        }
        result.close();
        statement.close();
        return developerList;
    }

    private boolean checkForeignKey(Integer key) throws SQLException {
        String sql = "SELECT id FROM companies";
        Statement statement = ConnectDao.connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            if (key.equals(result.getInt("id"))) ;
            result.close();
            statement.close();
            return true;
        }
        result.close();
        statement.close();
        return false;
    }

    private boolean checkJoinTable(Set<Skill> skills) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        String sql = "SELECT * FROM developers_skills";
        Statement statement = ConnectDao.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            ids.add(resultSet.getInt("skills"));
        }
        resultSet.close();
        statement.close();
        for (Skill skill : skills) {
            if (!ids.contains(skill.getSkillId())) {
                ConsoleHelper.writeMessage("Skill with id : " + skill.getSkillId() + " does not exists!");
                return false;
            }
        }
        return true;
    }
}
