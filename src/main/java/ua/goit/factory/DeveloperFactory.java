package ua.goit.factory;

import ua.goit.dao.DeveloperDao;
import ua.goit.dao.ModelDao;

import java.util.List;

/**
 * Created by Main Server on 20.12.2016.
 */
public class DeveloperFactory implements ModelFactory {
    @Override
    public void createElement(List list) {
        ModelDao modelDao = new DeveloperDao();
        modelDao.createElement(list);
    }
}
