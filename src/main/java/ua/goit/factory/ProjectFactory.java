package ua.goit.factory;

import ua.goit.dao.ModelDao;
import ua.goit.dao.ProjectDao;

import java.util.List;



public class ProjectFactory implements ModelFactory {
    @Override
    public void createElement(List list) {
        ModelDao modelDao = new ProjectDao();
        modelDao.createElement(list);
    }
}
