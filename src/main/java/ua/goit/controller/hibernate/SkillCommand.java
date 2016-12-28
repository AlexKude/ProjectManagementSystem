package ua.goit.controller.hibernate;

import ua.goit.dao.hibernate.ModelDao;
import ua.goit.dao.hibernate.SkillDao;
import ua.goit.factory.hibernate.ModelFactory;
import ua.goit.factory.hibernate.SkillFactory;
import ua.goit.model.hibernate.SkillEntity;
import ua.goit.view.ConsoleHelper;

import java.io.IOException;
import java.util.List;



public class SkillCommand implements Command {
    @Override
    public void execute() {
        ModelFactory factory = new SkillFactory();
        ModelDao<SkillEntity> dao = new SkillDao();
        SkillEntity skill = new SkillEntity();

        int id;
        ConsoleHelper.writeMessage("* * * SKILLS * * *" + "\n" +
                "1 - CREATE | 2 - DELETE | 3 - UPDATE | 4 - SHOW ALL SKILLS | 5 - SHOW SELECTED SKILLS\n");
        try {
            int commandNumber = ConsoleHelper.readInt();
            switch (commandNumber) {
                case 1:
                    ConsoleHelper.writeMessage("Type name of Skill: ");
                    skill.setSkillName(ConsoleHelper.readString());
                    factory.createElement(skill);
                    break;
                case 2:
                    ConsoleHelper.writeMessage("Type skill Id which you like to delete:");
                    id = ConsoleHelper.readInt();
                    dao.deleteElement(id);
                    break;
                case 3:
                    ConsoleHelper.writeMessage("Type skill Id which you like to update:");
                    skill.setId(ConsoleHelper.readInt());
                    ConsoleHelper.writeMessage("Type name of Skill:");
                    skill.setSkillName(ConsoleHelper.readString());
                    dao.updateElement(skill);
                    break;
                case 4:
                    List<SkillEntity> skills = dao.selectAllElements();
                    for (SkillEntity skillEntity : skills) {
                        ConsoleHelper.writeMessage(skillEntity.toString());
                    }
                    break;
                case 5:
                    ConsoleHelper.writeMessage("Type skill Id which you like to find:");
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
