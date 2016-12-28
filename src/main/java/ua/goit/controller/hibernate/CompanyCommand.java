package ua.goit.controller.hibernate;

import ua.goit.dao.hibernate.CompanyDao;
import ua.goit.dao.hibernate.ModelDao;
import ua.goit.factory.hibernate.CompanyFactory;
import ua.goit.factory.hibernate.ModelFactory;
import ua.goit.model.hibernate.CompanyEntity;
import ua.goit.view.ConsoleHelper;

import java.io.IOException;
import java.util.List;


public class CompanyCommand implements Command {
    @Override
    public void execute() {
        ModelFactory factory = new CompanyFactory();
        ModelDao<CompanyEntity> dao = new CompanyDao();
        CompanyEntity company = new CompanyEntity();

        int id;
        ConsoleHelper.writeMessage("* * * COMPANIES * * *" + "\n" +
                "1 - CREATE | 2 - DELETE | 3 - UPDATE | 4 - SHOW ALL COMPANIES | 5 - SHOW SELECTED COMPANY\n");
        try {
            int commandNumber = ConsoleHelper.readInt();
            switch (commandNumber) {
                case 1:
                    ConsoleHelper.writeMessage("Type name of Company: ");
                    company.setCompanyName(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type address of Company:");
                    company.setCompanyAddress(ConsoleHelper.readString());
                    factory.createElement(company);
                    break;
                case 2:
                    ConsoleHelper.writeMessage("Type company Id which you like to delete:");
                    id = ConsoleHelper.readInt();
                    dao.deleteElement(id);
                    break;
                case 3:
                    ConsoleHelper.writeMessage("Type company Id which you like to update:");
                    company.setId(ConsoleHelper.readInt());
                    ConsoleHelper.writeMessage("Type name of Company:");
                    company.setCompanyName(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type address of Company:");
                    company.setCompanyAddress(ConsoleHelper.readString());
                    dao.updateElement(company);

                    break;
                case 4:
                    List<CompanyEntity> companyList = dao.selectAllElements();
                    for (CompanyEntity companyEntity : companyList) {
                        ConsoleHelper.writeMessage(companyEntity.toString());
                    }
                    break;
                case 5:
                    ConsoleHelper.writeMessage("Type company Id which you like to find:");
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
