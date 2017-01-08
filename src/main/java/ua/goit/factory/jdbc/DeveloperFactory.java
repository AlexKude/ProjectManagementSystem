package ua.goit.factory.jdbc;

import ua.goit.dao.jdbc.DeveloperDao;
import ua.goit.dao.jdbc.ModelDao;


public class DeveloperFactory<ModelObject> implements ModelFactory<ModelObject> {
    @Override
    public void createElement(ModelObject object) {
        ModelDao modelDao = new DeveloperDao();
        modelDao.createElement(object);
    }
}
