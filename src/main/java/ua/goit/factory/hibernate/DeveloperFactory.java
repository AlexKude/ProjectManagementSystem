package ua.goit.factory.hibernate;

import ua.goit.dao.hibernate.DeveloperDao;
import ua.goit.dao.hibernate.ModelDao;



public class DeveloperFactory<ModelObject> implements ModelFactory<ModelObject> {
    @Override
    public void createElement(ModelObject object) {
        ModelDao modelDao = new DeveloperDao();
        modelDao.createElement(object);
    }
}
