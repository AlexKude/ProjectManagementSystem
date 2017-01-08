package ua.goit.controller.jdbc;

import ua.goit.dao.jdbc.CustomerDao;
import ua.goit.dao.jdbc.ModelDao;
import ua.goit.factory.jdbc.CustomerFactory;
import ua.goit.factory.jdbc.ModelFactory;
import ua.goit.model.jdbc.Company;
import ua.goit.model.jdbc.Customer;
import ua.goit.model.jdbc.Project;
import ua.goit.view.ConsoleHelper;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class CustomerCommand implements Command {
    @Override
    public void execute() {
        ModelFactory factory = new CustomerFactory();
        ModelDao dao = new CustomerDao();

        int id;
        int projectId;
        Customer customer = new Customer();
        Company company = new Company();
        Set<Project> projectSet = new HashSet<>();

        ConsoleHelper.writeMessage("* * * CUSTOMERS * * *" + "\n" +
                "1 - CREATE | 2 - DELETE | 3 - UPDATE | 4 - SHOW ALL CUSTOMERS | 5 - SHOW SELECTED CUSTOMERS\n");
        try {
            int commandNumber = ConsoleHelper.readInt();
            switch (commandNumber) {
                case 1:
                    ConsoleHelper.writeMessage("Type customer Family name:");
                    customer.setSurname(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type customer First name:");
                    customer.setName(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type customer Father name:");
                    customer.setFatherName(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type customer company id:");
                    company.setCompanyId(ConsoleHelper.readInt());
                    customer.setCompany(company);
                    while (true) {
                        ConsoleHelper.writeMessage("Type customer project id OR type 0 if like to create customer");
                        projectId = ConsoleHelper.readInt();
                        if (projectId != 0) {
                            Project project = new Project();
                            project.setProjectId(projectId);
                            projectSet.add(project);
                        } else {
                            break;
                        }
                    }
                    customer.setProjectSet(projectSet);
                    factory.createElement(customer);
                    break;
                case 2:
                    ConsoleHelper.writeMessage("Type customer Id which you like to delete:");
                    id = ConsoleHelper.readInt();
                    dao.deleteElement(id);
                    break;
                case 3:
                    ConsoleHelper.writeMessage("Type customer Id which you like to update:");
                    customer.setCustomerId(ConsoleHelper.readInt());
                    ConsoleHelper.writeMessage("Type customer Family name:");
                    customer.setSurname(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type customer First name:");
                    customer.setName(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type customer Father name:");
                    customer.setFatherName(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type customer company id:");
                    company.setCompanyId(ConsoleHelper.readInt());
                    customer.setCompany(company);
                    while (true) {
                        ConsoleHelper.writeMessage("Type customer project id OR type 0 if like to create customer");
                        projectId = ConsoleHelper.readInt();
                        if (projectId != 0) {
                            Project project = new Project();
                            project.setProjectId(projectId);
                            projectSet.add(project);
                        } else {
                            break;
                        }
                    }
                    customer.setProjectSet(projectSet);
                    dao.updateElement(customer);
                    break;
                case 4:
                    dao.selectAllElements();
                    break;
                case 5:
                    ConsoleHelper.writeMessage("Type customer Id whom you like to find:");
                    id = ConsoleHelper.readInt();
                    dao.selectElement(id);
                    break;
                default:
                    break;

            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Input error. Please try again....");
        } catch (NumberFormatException e) {
            ConsoleHelper.writeMessage("Wrong number format. Please try again......");
        }
    }
}
