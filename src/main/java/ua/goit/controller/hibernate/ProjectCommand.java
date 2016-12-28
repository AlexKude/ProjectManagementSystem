package ua.goit.controller.hibernate;

import ua.goit.dao.hibernate.ModelDao;
import ua.goit.dao.hibernate.ProjectDao;
import ua.goit.factory.hibernate.ModelFactory;
import ua.goit.factory.hibernate.ProjectFactory;
import ua.goit.model.hibernate.ProjectEntity;
import ua.goit.view.ConsoleHelper;

import java.io.IOException;
import java.util.List;



public class ProjectCommand implements Command {
    @Override
    public void execute() {
        ModelFactory factory = new ProjectFactory();
        ModelDao<ProjectEntity> dao = new ProjectDao();
        ProjectEntity project = new ProjectEntity();

        int id;
        ConsoleHelper.writeMessage("* * * PROJECTS * * *" + "\n" +
                "1 - CREATE | 2 - DELETE | 3 - UPDATE | 4 - SHOW ALL PROJECTS | 5 - SHOW SELECTED PROJECT\n");
        try {
            int commandNumber = ConsoleHelper.readInt();
            switch (commandNumber) {
                case 1:
                    ConsoleHelper.writeMessage("Type name of Project: ");
                    project.setProjectName(ConsoleHelper.readString());
                    factory.createElement(project);
                    break;
                case 2:
                    ConsoleHelper.writeMessage("Type project Id which you like to delete:");
                    id = ConsoleHelper.readInt();
                    dao.deleteElement(id);
                    break;
                case 3:
                    ConsoleHelper.writeMessage("Type project Id which you like to update:");
                    project.setId(ConsoleHelper.readInt());
                    ConsoleHelper.writeMessage("Type name of Project:");
                    project.setProjectName(ConsoleHelper.readString());
                    dao.updateElement(project);
                    break;
                case 4:
                    List<ProjectEntity> projectList = dao.selectAllElements();
                    for (ProjectEntity projectEntity : projectList) {
                        ConsoleHelper.writeMessage(projectEntity.toString());
                    }
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
