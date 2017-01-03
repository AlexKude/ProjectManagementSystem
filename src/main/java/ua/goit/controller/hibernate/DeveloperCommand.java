package ua.goit.controller.hibernate;

import ua.goit.dao.hibernate.CompanyDao;
import ua.goit.dao.hibernate.DeveloperDao;
import ua.goit.dao.hibernate.ModelDao;
import ua.goit.dao.hibernate.SkillDao;
import ua.goit.factory.hibernate.DeveloperFactory;
import ua.goit.factory.hibernate.ModelFactory;
import ua.goit.model.hibernate.CompanyEntity;
import ua.goit.model.hibernate.DeveloperEntity;
import ua.goit.model.hibernate.SkillEntity;
import ua.goit.view.ConsoleHelper;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class DeveloperCommand implements Command {
    @Override
    public void execute() {
        ModelFactory factory = new DeveloperFactory();
        ModelDao<DeveloperEntity> dao = new DeveloperDao();
        Set<SkillEntity> skillSelected;
        DeveloperEntity developer = new DeveloperEntity();
        CompanyEntity company;

        int id;
        int companyId;
        int skillId;
        Set<Integer> skillsId = new HashSet<>();

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
                    } else {
                        ConsoleHelper.writeMessage("Company with typed id does not exists.Please try again...");
                        break;
                    }
                    while (true) {
                        ConsoleHelper.writeMessage("Type developer skill id OR type 0 if like to create developer");
                        skillId = ConsoleHelper.readInt();
                        if (skillId != 0) {
                            skillsId.add(skillId);
                        } else {
                            break;
                        }
                    }
                    if (!skillsId.isEmpty()) {
                        skillSelected = checkJoinElements(skillsId);
                        if (skillSelected != null) {
                            developer.setSkills(skillSelected);
                            factory.createElement(developer);
                            break;
                        } else {
                            break;
                        }
                    }
                    factory.createElement(developer);
                    break;
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
                    } else {
                        ConsoleHelper.writeMessage("Company with typed id does not exists.Please try again...");
                        break;
                    }
                    while (true) {
                        ConsoleHelper.writeMessage("Type developer skill id OR type 0 if like to update developer");
                        skillId = ConsoleHelper.readInt();
                        if (skillId != 0) {
                            skillsId.add(skillId);
                        } else {
                            break;
                        }
                    }
                    if (!skillsId.isEmpty()) {
                        skillSelected = checkJoinElements(skillsId);
                        if (skillSelected != null) {
                            developer.setSkills(skillSelected);
                            dao.updateElement(developer);
                            break;
                        } else {
                            break;
                        }
                    }
                    dao.updateElement(developer);
                    break;
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

    private Set<SkillEntity> checkJoinElements (Set<Integer> skillsId) {
        boolean flag = false;
        ModelDao joinElements = new SkillDao();
        Set<SkillEntity> skillSelected = new HashSet<>();
        List<SkillEntity> skillList = joinElements.selectAllElements();
        if (skillList == null || skillList.isEmpty()) {
            return null;
        }
        for (Integer integer : skillsId) {
            for (SkillEntity skill : skillList) {
                if (integer.equals(skill.getId())) {
                    skillSelected.add(skill);
                    flag = true;
                }
            }
            if (flag == false) {
                ConsoleHelper.writeMessage("Skill with id : " + integer + " does not exists!");
                return null;
            }
        }
        return skillSelected;
    }
}
