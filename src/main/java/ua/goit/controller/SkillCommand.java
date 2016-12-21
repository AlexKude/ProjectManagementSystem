package ua.goit.controller;

import ua.goit.dao.ModelDao;
import ua.goit.dao.SkillDao;
import ua.goit.factory.ModelFactory;
import ua.goit.factory.SkillFactory;
import ua.goit.view.ConsoleHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Main Server on 18.12.2016.
 */
public class SkillCommand implements Command {
    @Override
    public void execute() {
        ModelFactory factory = new SkillFactory();
        ModelDao dao = new SkillDao();

        int id;
        List list = new ArrayList();
        ConsoleHelper.writeMessage("* * * SKILLS * * *" + "\n" +
                "1 - CREATE | 2 - DELETE | 3 - UPDATE | 4 - SHOW ALL SKILLS | 5 - SHOW SELECTED SKILLS\n");
        try {
            int commandNumber = ConsoleHelper.readInt();
            switch (commandNumber) {
                case 1:
                    ConsoleHelper.writeMessage("Type name of Skill: ");
                    list.add(ConsoleHelper.readString());
                    factory.createElement(list);
                    break;
                case 2:
                    ConsoleHelper.writeMessage("Type skill Id which you like to delete:");
                    id = ConsoleHelper.readInt();
                    dao.deleteElement(id);
                    break;
                case 3:
                    ConsoleHelper.writeMessage("Type skill Id which you like to update:");
                    list.add(ConsoleHelper.readInt());
                    ConsoleHelper.writeMessage("Type name of Skill:");
                    list.add(ConsoleHelper.readString());
                    dao.updateElement(list);
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
