package ua.goit.controller.jdbc;

import ua.goit.dao.jdbc.ModelDao;
import ua.goit.dao.jdbc.SkillDao;
import ua.goit.factory.jdbc.ModelFactory;
import ua.goit.factory.jdbc.SkillFactory;
import ua.goit.model.jdbc.Skill;
import ua.goit.view.ConsoleHelper;

import java.io.IOException;


public class SkillCommand implements Command {
    @Override
    public void execute() {
        ModelFactory factory = new SkillFactory();
        ModelDao dao = new SkillDao();

        int id;
        Skill skill = new Skill();
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
                    skill.setSkillId(ConsoleHelper.readInt());
                    ConsoleHelper.writeMessage("Type name of Skill:");
                    skill.setSkillName(ConsoleHelper.readString());
                    dao.updateElement(skill);
                    break;
                case 4:
                    dao.selectAllElements();
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
