package ua.goit.factory.hibernate;

import ua.goit.dao.hibernate.ModelDao;
import ua.goit.dao.hibernate.ProjectDao;



public class ProjectFactory<ModelObject> implements ModelFactory<ModelObject> {
    @Override
    public void createElement(ModelObject object) {
        ModelDao modelDao = new ProjectDao();
        modelDao.createElement(object);
    }
}
