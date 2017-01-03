package ua.goit.controller.hibernate;

import ua.goit.dao.hibernate.DeveloperDao;
import ua.goit.dao.hibernate.ModelDao;
import ua.goit.dao.hibernate.ProjectDao;
import ua.goit.factory.hibernate.ModelFactory;
import ua.goit.factory.hibernate.ProjectFactory;
import ua.goit.model.hibernate.DeveloperEntity;
import ua.goit.model.hibernate.ProjectEntity;
import ua.goit.view.ConsoleHelper;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ProjectCommand implements Command {
    @Override
    public void execute() {
        ModelFactory factory = new ProjectFactory();
        ModelDao<ProjectEntity> dao = new ProjectDao();
        ProjectEntity project = new ProjectEntity();
        Set<DeveloperEntity> developersSelected;

        int id;
        int developerId;
        Set<Integer> developersId = new HashSet<>();

        ConsoleHelper.writeMessage("* * * PROJECTS * * *" + "\n" +
                "1 - CREATE | 2 - DELETE | 3 - UPDATE | 4 - SHOW ALL PROJECTS | 5 - SHOW SELECTED PROJECT\n");
        try {
            int commandNumber = ConsoleHelper.readInt();
            switch (commandNumber) {
                case 1:
                    ConsoleHelper.writeMessage("Type name of Project: ");
                    project.setProjectName(ConsoleHelper.readString());
                    while (true) {
                        ConsoleHelper.writeMessage("Type developer id who working on this project OR type 0 if like to create project");
                        developerId = ConsoleHelper.readInt();
                        if (developerId != 0) {
                            developersId.add(developerId);
                        } else {
                            break;
                        }
                    }
                    if (!developersId.isEmpty()) {
                        developersSelected = checkJoinElements(developersId);
                        if (developersSelected != null) {
                            project.setDeveloperSet(developersSelected);
                            factory.createElement(project);
                            break;
                        } else {
                            break;
                        }
                    }
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
                    while (true) {
                        ConsoleHelper.writeMessage("Type developer id who working on this project OR type 0 if like to update project");
                        developerId = ConsoleHelper.readInt();
                        if (developerId != 0) {
                            developersId.add(developerId);
                        } else {
                            break;
                        }
                    }
                    if (!developersId.isEmpty()) {
                        developersSelected = checkJoinElements(developersId);
                        if (developersSelected != null) {
                            project.setDeveloperSet(developersSelected);
                            dao.updateElement(project);
                            break;
                        } else {
                            break;
                        }
                    }
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

    private Set<DeveloperEntity> checkJoinElements (Set<Integer> developersId) {
        boolean flag = false;
        ModelDao joinElements = new DeveloperDao();
        Set<DeveloperEntity> developersSelected = new HashSet<>();
        List<DeveloperEntity> developerList = joinElements.selectAllElements();
        if (developerList == null || developerList.isEmpty()) {
            return null;
        }
        for (Integer integer : developersId) {
            for (DeveloperEntity developer : developerList) {
                if (integer.equals(developer.getId())) {
                    developersSelected.add(developer);
                    flag = true;
                }
            }
            if (flag == false) {
                ConsoleHelper.writeMessage("Developer with id : " + integer + " does not exists!");
                return null;
            }
        }
        return developersSelected;
    }
}
