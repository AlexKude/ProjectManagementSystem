package ua.goit.controller;

import ua.goit.dao.DeveloperDao;
import ua.goit.dao.ModelDao;
import ua.goit.factory.DeveloperFactory;
import ua.goit.factory.ModelFactory;
import ua.goit.view.ConsoleHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class DeveloperCommand implements Command {
    @Override
    public void execute() {
        ModelFactory factory = new DeveloperFactory();
        ModelDao dao = new DeveloperDao();

        int id;
        List list = new ArrayList();
        ConsoleHelper.writeMessage("* * * DEVELOPERS * * *" + "\n" +
                "1 - CREATE | 2 - DELETE | 3 - UPDATE | 4 - SHOW ALL DEVELOPERS | 5 - SHOW SELECTED DEVELOPER\n");
        try {
            int commandNumber = ConsoleHelper.readInt();
            switch (commandNumber) {
                case 1:
                    ConsoleHelper.writeMessage("Type developer Family name:");
                    list.add(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type deleloper First name:");
                    list.add(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type developer Father name:");
                    list.add(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type deleloper developer date of birth:");
                    ConsoleHelper.writeMessage("Date format - yyyy-MM-dd");
                    list.add(ConsoleHelper.readDate());
                    ConsoleHelper.writeMessage("Type deleloper developer date of join:");
                    ConsoleHelper.writeMessage("Date format - yyyy-MM-dd");
                    list.add(ConsoleHelper.readDate());
                    ConsoleHelper.writeMessage("Type developer address:");
                    list.add(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type developer company id:");
                    list.add(ConsoleHelper.readInt());
                    factory.createElement(list);
                    break;
                case 2:
                    ConsoleHelper.writeMessage("Type developer Id which you like to delete:");
                    id = ConsoleHelper.readInt();
                    dao.deleteElement(id);
                    break;
                case 3:
                    ConsoleHelper.writeMessage("Type developer Id which you like to update:");
                    list.add(ConsoleHelper.readInt());
                    ConsoleHelper.writeMessage("Type developer Family name:");
                    list.add(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type deleloper First name:");
                    list.add(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type developer Father name:");
                    list.add(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type deleloper developer date of birth:");
                    ConsoleHelper.writeMessage("Date format- yyyy-MM-dd");
                    list.add(ConsoleHelper.readDate());
                    ConsoleHelper.writeMessage("Type deleloper developer date of join:");
                    ConsoleHelper.writeMessage("Date format- yyyy-MM-dd");
                    list.add(ConsoleHelper.readDate());
                    ConsoleHelper.writeMessage("Type developer address:");
                    list.add(ConsoleHelper.readString());
                    ConsoleHelper.writeMessage("Type developer company id:");
                    list.add(ConsoleHelper.readInt());
                    dao.updateElement(list);
                    break;
                case 4:
                    dao.selectAllElements();
                    break;
                case 5:
                    ConsoleHelper.writeMessage("Type developer Id whom you like to find:");
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
        } catch (IllegalArgumentException e) {
            ConsoleHelper.writeMessage("Wrong data format.Please try again......");
        }
    }
}
