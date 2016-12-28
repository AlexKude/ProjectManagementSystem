package ua.goit.factory.hibernate;

import ua.goit.dao.hibernate.CompanyDao;
import ua.goit.dao.hibernate.ModelDao;



public class CompanyFactory<ModelObject> implements ModelFactory<ModelObject> {
    @Override
    public void createElement(ModelObject object) {
        ModelDao modelDao = new CompanyDao();
        modelDao.createElement(object);
    }
}
