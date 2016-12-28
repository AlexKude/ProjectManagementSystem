package ua.goit.factory.jdbc;

import ua.goit.dao.jdbc.ModelDao;
import ua.goit.dao.jdbc.ProjectDao;
import ua.goit.factory.jdbc.ModelFactory;

import java.util.List;



public class ProjectFactory implements ModelFactory {
    @Override
    public void createElement(List list) {
        ModelDao modelDao = new ProjectDao();
        modelDao.createElement(list);
    }
}
