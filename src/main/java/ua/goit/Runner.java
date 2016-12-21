package ua.goit;

import ua.goit.controller.Command;
import ua.goit.controller.CompanyCommand;
import ua.goit.controller.CustomerCommand;
import ua.goit.controller.DeveloperCommand;
import ua.goit.controller.ProjectCommand;
import ua.goit.controller.SkillCommand;
import ua.goit.dao.ConnectDao;
import ua.goit.view.ConsoleHelper;

import java.io.IOException;


/**
 * Created by Main Server on 18.12.2016.
 */
public class Runner {
    public static void main(String[] args) {
        ConnectDao.ConnectDB();
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
                        ConnectDao.closeConnect();
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
