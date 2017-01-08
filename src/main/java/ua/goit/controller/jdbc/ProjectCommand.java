package ua.goit.controller.jdbc;


import ua.goit.dao.jdbc.ModelDao;
import ua.goit.dao.jdbc.ProjectDao;
import ua.goit.factory.jdbc.ModelFactory;
import ua.goit.factory.jdbc.ProjectFactory;
import ua.goit.model.jdbc.Developer;
import ua.goit.model.jdbc.Project;
import ua.goit.view.ConsoleHelper;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class ProjectCommand implements Command {
    @Override
    public void execute() {
        ModelFactory factory = new ProjectFactory();
        ModelDao dao = new ProjectDao();

        int id;
        int developerId;
        Project project = new Project();
        Set<Developer> developerSet = new HashSet<>();

        ConsoleHelper.writeMessage("* * * PROJECTS * * *" + "\n" +
                "1 - CREATE | 2 - DELETE | 3 - UPDATE | 4 - SHOW ALL PROJECTS | 5 - SHOW SELECTED PROJECT\n");
        try {
            int commandNumber = ConsoleHelper.readInt();
            switch (commandNumber) {
                case 1:
                    ConsoleHelper.writeMessage("Type name of Project: ");
                    project.setProjectName(ConsoleHelper.readString());
                    while (true) {
                        ConsoleHelper.writeMessage("Type id of developer who working on this project OR type 0 if like to create project");
                        developerId = ConsoleHelper.readInt();
                        if (developerId != 0) {
                            Developer developer = new Developer();
                            developer.setDelevoperId(developerId);
                            developerSet.add(developer);
                        } else {
                            break;
                        }
                    }
                    project.setDeveloperSet(developerSet);
                    factory.createElement(project);
                    break;
                case 2:
                    ConsoleHelper.writeMessage("Type project Id which you like to delete:");
                    id = ConsoleHelper.readInt();
                    dao.deleteElement(id);
                    break;
                case 3:
                    ConsoleHelper.writeMessage("Type project Id which you like to update:");
                    project.setProjectId(ConsoleHelper.readInt());
                    ConsoleHelper.writeMessage("Type name of Project:");
                    project.setProjectName(ConsoleHelper.readString());
                    while (true) {
                        ConsoleHelper.writeMessage("Type id of developer who working on this project OR type 0 if like to update project");
                        developerId = ConsoleHelper.readInt();
                        if (developerId != 0) {
                            Developer developer = new Developer();
                            developer.setDelevoperId(developerId);
                            developerSet.add(developer);
                        } else {
                            break;
                        }
                    }
                    project.setDeveloperSet(developerSet);
                    dao.updateElement(project);
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
