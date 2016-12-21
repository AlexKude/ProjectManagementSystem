package ua.goit.controller;


import ua.goit.dao.ModelDao;
import ua.goit.dao.ProjectDao;
import ua.goit.factory.ModelFactory;
import ua.goit.factory.ProjectFactory;
import ua.goit.view.ConsoleHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Main Server on 18.12.2016.
 */
public class ProjectCommand implements Command {
    @Override
    public void execute() {
        ModelFactory factory = new ProjectFactory();
        ModelDao dao = new ProjectDao();

        int id;
        List list = new ArrayList();
        ConsoleHelper.writeMessage("* * * PROJECTS * * *" + "\n" +
                "1 - CREATE | 2 - DELETE | 3 - UPDATE | 4 - SHOW ALL PROJECTS | 5 - SHOW SELECTED PROJECT\n");
        try {
            int commandNumber = ConsoleHelper.readInt();
            switch (commandNumber) {
                case 1:
                    ConsoleHelper.writeMessage("Type name of Project: ");
                    list.add(ConsoleHelper.readString());
                    factory.createElement(list);
                    break;
                case 2:
                    ConsoleHelper.writeMessage("Type project Id which you like to delete:");
                    id = ConsoleHelper.readInt();
                    dao.deleteElement(id);
                    break;
                case 3:
                    ConsoleHelper.writeMessage("Type project Id which you like to update:");
                    list.add(ConsoleHelper.readInt());
                    ConsoleHelper.writeMessage("Type name of Project:");
                    list.add(ConsoleHelper.readString());
                    dao.updateElement(list);
                    break;
                case 4:
                    dao.selectAllElements();
                    break;
                case 5:
                    ConsoleHelper.writeMessage("Type project Id which you like to find:");
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
