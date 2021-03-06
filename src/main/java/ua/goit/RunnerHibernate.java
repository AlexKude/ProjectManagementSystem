package ua.goit;

import ua.goit.controller.hibernate.Command;
import ua.goit.controller.hibernate.CompanyCommand;
import ua.goit.controller.hibernate.CustomerCommand;
import ua.goit.controller.hibernate.DeveloperCommand;
import ua.goit.controller.hibernate.ProjectCommand;
import ua.goit.controller.hibernate.SkillCommand;
import ua.goit.view.ConsoleHelper;

import java.io.IOException;


public class RunnerHibernate {
    public static void main(String[] args) {
        Command command;
        int commandNumber;
        ConsoleHelper.writeMessage("CREATE READ UPDATE DELETE DATA BASE");

        while (true) {
            ConsoleHelper.writeMessage("\nPlease make your choice: ");
            ConsoleHelper.writeMessage("\n1 - COMPANIES  | 2 - DEVELOPERS | 3 - SKILLS | 4 - CUSTOMERS | 5 - PROJECTS | 6 - EXIT");
            try {
                commandNumber = ConsoleHelper.readInt();
                switch (commandNumber) {
                    case 1:
                        command = new CompanyCommand();
                        command.execute();
                        break;
                    case 2:
                        command = new DeveloperCommand();
                        command.execute();
                        break;
                    case 3:
                        command = new SkillCommand();
                        command.execute();
                        break;
                    case 4:
                        command = new CustomerCommand();
                        command.execute();
                        break;
                    case 5:
                        command = new ProjectCommand();
                        command.execute();
                        break;
                    case 6:
                        System.exit(0);
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
}
