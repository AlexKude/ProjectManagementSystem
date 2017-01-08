package ua.goit.factory.jdbc;

import ua.goit.dao.jdbc.CompanyDao;
import ua.goit.dao.jdbc.ModelDao;



public class CompanyFactory<ModelObject> implements ModelFactory<ModelObject> {

    @Override
    public void createElement(ModelObject object) {
        ModelDao modelDao = new CompanyDao();
        modelDao.createElement(object);
    }
}
