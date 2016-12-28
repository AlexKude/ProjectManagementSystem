package ua.goit.factory.jdbc;

import ua.goit.dao.jdbc.DeveloperDao;
import ua.goit.dao.jdbc.ModelDao;

import java.util.List;



public class DeveloperFactory implements ModelFactory {
    @Override
    public void createElement(List list) {
        ModelDao modelDao = new DeveloperDao();
        modelDao.createElement(list);
    }
}
