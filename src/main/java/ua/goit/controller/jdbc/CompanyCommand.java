package ua.goit.controller.jdbc;

import ua.goit.dao.jdbc.CompanyDao;
import ua.goit.dao.jdbc.ModelDao;
import ua.goit.factory.jdbc.CompanyFactory;
import ua.goit.factory.jdbc.ModelFactory;
import ua.goit.model.jdbc.Company;
import ua.goit.view.ConsoleHelper;

import java.io.IOException;




public class CompanyCommand implements Command {

    @Override
    public void execute() {
        ModelFactory factory = new CompanyFactory();
        ModelDao dao = new CompanyDao();

        int id;
        Company company = new Company();
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
                    company.setCompanyId(ConsoleHelper.readInt());
                    ConsoleHelper.writeMessage("Type name of Company:");
                    company.setCompanyName(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type address of Company:");
                    company.setCompanyAddress(ConsoleHelper.readString());
                    dao.updateElement(company);

                    break;
                case 4:
                    dao.selectAllElements();
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
