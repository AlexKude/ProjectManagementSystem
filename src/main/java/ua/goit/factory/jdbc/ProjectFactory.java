package ua.goit.factory.jdbc;

import ua.goit.dao.jdbc.ModelDao;
import ua.goit.dao.jdbc.ProjectDao;



public class ProjectFactory<ModelObject> implements ModelFactory<ModelObject> {
    @Override
    public void createElement(ModelObject object) {
        ModelDao modelDao = new ProjectDao();
        modelDao.createElement(object);
    }
}
