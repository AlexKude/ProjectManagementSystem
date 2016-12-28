package ua.goit.factory.hibernate;

import ua.goit.dao.hibernate.CustomerDao;
import ua.goit.dao.hibernate.ModelDao;



public class CustomerFactory<ModelObject> implements ModelFactory<ModelObject> {
    @Override
    public void createElement(ModelObject object) {
        ModelDao modelDao = new CustomerDao();
        modelDao.createElement(object);

    }
}
