package ua.goit.controller.hibernate;

import ua.goit.dao.hibernate.CompanyDao;
import ua.goit.dao.hibernate.CustomerDao;
import ua.goit.dao.hibernate.ModelDao;
import ua.goit.factory.hibernate.CustomerFactory;
import ua.goit.factory.hibernate.ModelFactory;
import ua.goit.model.hibernate.CompanyEntity;
import ua.goit.model.hibernate.CustomerEntity;
import ua.goit.view.ConsoleHelper;

import java.io.IOException;
import java.util.List;



public class CustomerCommand implements Command {
    @Override
    public void execute() {
        ModelFactory factory = new CustomerFactory();
        ModelDao<CustomerEntity> dao = new CustomerDao();

        CustomerEntity customer = new CustomerEntity();
        CompanyEntity company = null;

        int id;
        int companyId;
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
                    ConsoleHelper.writeMessage("Type developer company id:");
                    companyId = ConsoleHelper.readInt();
                    company = checkForeignKey(companyId);
                    if (company != null) {
                        customer.setCompany(company);
                        factory.createElement(customer);
                        break;
                    } else {
                        ConsoleHelper.writeMessage("Company with typed id does not exists.Please try again...");
                        break;
                    }
                case 2:
                    ConsoleHelper.writeMessage("Type customer Id which you like to delete:");
                    id = ConsoleHelper.readInt();
                    dao.deleteElement(id);
                    break;
                case 3:
                    ConsoleHelper.writeMessage("Type customer Id which you like to update:");
                    customer.setId(ConsoleHelper.readInt());
                    ConsoleHelper.writeMessage("Type customer Family name:");
                    customer.setSurname(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type customer First name:");
                    customer.setName(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type customer Father name:");
                    customer.setName(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type customer company id:");
                    companyId = ConsoleHelper.readInt();
                    company = checkForeignKey(companyId);
                    if (company != null) {
                        customer.setCompany(company);
                        dao.updateElement(customer);
                        break;
                    } else {
                        ConsoleHelper.writeMessage("Company with typed id does not exists.Please try again...");
                        break;
                    }
                case 4:
                    List<CustomerEntity> customerList = dao.selectAllElements();
                    for (CustomerEntity customerEntity : customerList) {
                        ConsoleHelper.writeMessage(customerEntity.toString());
                    }
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

    private CompanyEntity checkForeignKey(int companyId) throws IOException {
        ModelDao fkey = new CompanyDao();
        List<CompanyEntity> companyList = fkey.selectAllElements();
        if (companyList == null || companyList.isEmpty()){
            return null;
        }
        for (CompanyEntity company : companyList) {
            if (companyId == company.getId()) {
                return company;
            }
        }
        return null;
    }
}
