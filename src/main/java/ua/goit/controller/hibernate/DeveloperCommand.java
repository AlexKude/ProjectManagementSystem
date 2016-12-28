package ua.goit.controller.hibernate;

import ua.goit.dao.hibernate.CompanyDao;
import ua.goit.dao.hibernate.DeveloperDao;
import ua.goit.dao.hibernate.ModelDao;
import ua.goit.factory.hibernate.DeveloperFactory;
import ua.goit.factory.hibernate.ModelFactory;
import ua.goit.model.hibernate.CompanyEntity;
import ua.goit.model.hibernate.DeveloperEntity;
import ua.goit.view.ConsoleHelper;

import java.io.IOException;
import java.util.List;



public class DeveloperCommand implements Command {
    @Override
    public void execute() {
        ModelFactory factory = new DeveloperFactory();
        ModelDao<DeveloperEntity> dao = new DeveloperDao();

        DeveloperEntity developer = new DeveloperEntity();
        CompanyEntity company = null;

        int id;
        int companyId;
        ConsoleHelper.writeMessage("* * * DEVELOPERS * * *" + "\n" +
                "1 - CREATE | 2 - DELETE | 3 - UPDATE | 4 - SHOW ALL DEVELOPERS | 5 - SHOW SELECTED DEVELOPER\n");
        try {
            int commandNumber = ConsoleHelper.readInt();
            switch (commandNumber) {
                case 1:
                    ConsoleHelper.writeMessage("Type developer Family name:");
                    developer.setSurname(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type deleloper First name:");
                    developer.setName(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type developer Father name:");
                    developer.setFatherName(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type deleloper developer date of birth:");
                    ConsoleHelper.writeMessage("Date format - yyyy-MM-dd");
                    developer.setDateOfBirth(ConsoleHelper.readDate());
                    ConsoleHelper.writeMessage("Type deleloper developer date of join:");
                    ConsoleHelper.writeMessage("Date format - yyyy-MM-dd");
                    developer.setDateOfJoin(ConsoleHelper.readDate());
                    ConsoleHelper.writeMessage("Type developer address:");
                    developer.setAddress(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type developer company id:");
                    companyId = ConsoleHelper.readInt();
                    company = checkForeignKey(companyId);
                    if (company != null) {
                        developer.setCompany(company);
                        factory.createElement(developer);
                        break;
                    } else {
                        ConsoleHelper.writeMessage("Company with typed id does not exists.Please try again...");
                        break;
                    }
                case 2:
                    ConsoleHelper.writeMessage("Type developer Id which you like to delete:");
                    id = ConsoleHelper.readInt();
                    dao.deleteElement(id);
                    break;
                case 3:
                    ConsoleHelper.writeMessage("Type developer Id which you like to update:");
                    developer.setId(ConsoleHelper.readInt());
                    ConsoleHelper.writeMessage("Type developer Family name:");
                    developer.setSurname(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type deleloper First name:");
                    developer.setName(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type developer Father name:");
                    developer.setFatherName(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type deleloper developer date of birth:");
                    ConsoleHelper.writeMessage("Date format- yyyy-MM-dd");
                    developer.setDateOfBirth(ConsoleHelper.readDate());
                    ConsoleHelper.writeMessage("Type deleloper developer date of join:");
                    ConsoleHelper.writeMessage("Date format- yyyy-MM-dd");
                    developer.setDateOfJoin(ConsoleHelper.readDate());
                    ConsoleHelper.writeMessage("Type developer address:");
                    developer.setAddress(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type developer company id:");
                    companyId = ConsoleHelper.readInt();
                    company = checkForeignKey(companyId);
                    if (company != null) {
                        developer.setCompany(company);
                        factory.createElement(developer);
                        break;
                    } else {
                        ConsoleHelper.writeMessage("Company with typed id does not exists.Please try again...");
                        break;
                    }
                case 4:
                    List<DeveloperEntity> developerList = dao.selectAllElements();
                    for (DeveloperEntity developerEntity : developerList) {
                        ConsoleHelper.writeMessage(developerEntity.toString());
                    }
                    break;
                case 5:
                    ConsoleHelper.writeMessage("Type developer Id whom you like to find:");
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
        } catch (IllegalArgumentException e) {
            ConsoleHelper.writeMessage("Wrong data format.Please try again......");
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
