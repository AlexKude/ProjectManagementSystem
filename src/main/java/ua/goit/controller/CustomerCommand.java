package ua.goit.controller;

import ua.goit.dao.CustomerDao;
import ua.goit.dao.ModelDao;
import ua.goit.factory.CustomerFactory;
import ua.goit.factory.ModelFactory;
import ua.goit.view.ConsoleHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Main Server on 18.12.2016.
 */
public class CustomerCommand implements Command {
    @Override
    public void execute() {
        ModelFactory factory = new CustomerFactory();
        ModelDao dao = new CustomerDao();

        int id;
        List list = new ArrayList();

        ConsoleHelper.writeMessage("* * * CUSTOMERS * * *" + "\n" +
                "1 - CREATE | 2 - DELETE | 3 - UPDATE | 4 - SHOW ALL CUSTOMERS | 5 - SHOW SELECTED CUSTOMERS\n");
        try {
            int commandNumber = ConsoleHelper.readInt();
            switch (commandNumber) {
                case 1:
                    ConsoleHelper.writeMessage("Type customer Family name:");
                    list.add(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type customer First name:");
                    list.add(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type customer Father name:");
                    list.add(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type developer company id:");
                    list.add(ConsoleHelper.readInt());
                    factory.createElement(list);
                    break;
                case 2:
                    ConsoleHelper.writeMessage("Type customer Id which you like to delete:");
                    id = ConsoleHelper.readInt();
                    dao.deleteElement(id);
                    break;
                case 3:
                    ConsoleHelper.writeMessage("Type customer Id which you like to update:");
                    list.add(ConsoleHelper.readInt());
                    ConsoleHelper.writeMessage("Type customer Family name:");
                    list.add(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type customer First name:");
                    list.add(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type customer Father name:");
                    list.add(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type customer company id:");
                    list.add(ConsoleHelper.readInt());
                    dao.updateElement(list);
                    break;
                case 4:
                    dao.selectAllElements();
                    break;
                case 5:
                    ConsoleHelper.writeMessage("Type customer Id whom you like to find:");
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
