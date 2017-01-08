package ua.goit.controller.jdbc;

import ua.goit.dao.jdbc.DeveloperDao;
import ua.goit.dao.jdbc.ModelDao;
import ua.goit.factory.jdbc.DeveloperFactory;
import ua.goit.factory.jdbc.ModelFactory;
import ua.goit.model.jdbc.Company;
import ua.goit.model.jdbc.Developer;
import ua.goit.model.jdbc.Skill;
import ua.goit.view.ConsoleHelper;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class DeveloperCommand implements Command {
    @Override
    public void execute() {
        ModelFactory factory = new DeveloperFactory();
        ModelDao dao = new DeveloperDao();

        int id;
        int skillId;
        Developer developer = new Developer();
        Company company = new Company();
        Set<Skill> skills = new HashSet<>();
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
                    company.setCompanyId(ConsoleHelper.readInt());
                    developer.setCompany(company);
                    while (true) {
                        ConsoleHelper.writeMessage("Type developer skill id OR type 0 if like to create developer");
                        skillId = ConsoleHelper.readInt();
                        if (skillId != 0) {
                            Skill skill = new Skill();
                            skill.setSkillId(skillId);
                            skills.add(skill);
                        } else {
                            break;
                        }
                    }
                    developer.setSkills(skills);
                    factory.createElement(developer);
                    break;
                case 2:
                    ConsoleHelper.writeMessage("Type developer Id which you like to delete:");
                    id = ConsoleHelper.readInt();
                    dao.deleteElement(id);
                    break;
                case 3:
                    ConsoleHelper.writeMessage("Type developer Id which you like to update:");
                    developer.setDelevoperId(ConsoleHelper.readInt());
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
                    company.setCompanyId(ConsoleHelper.readInt());
                    developer.setCompany(company);
                    while (true) {
                        ConsoleHelper.writeMessage("Type developer skill id OR type 0 if like to update developer");
                        skillId = ConsoleHelper.readInt();
                        if (skillId != 0) {
                            Skill skill = new Skill();
                            skill.setSkillId(skillId);
                            skills.add(skill);
                        } else {
                            break;
                        }
                    }
                    developer.setSkills(skills);
                    dao.updateElement(developer);
                    break;
                case 4:
                    dao.selectAllElements();
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
}
