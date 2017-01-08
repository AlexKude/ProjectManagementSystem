package ua.goit.factory.jdbc;

import ua.goit.dao.jdbc.CustomerDao;
import ua.goit.dao.jdbc.ModelDao;


public class CustomerFactory<ModelObject> implements ModelFactory<ModelObject> {
    @Override
    public void createElement(ModelObject object) {
        ModelDao modelDao = new CustomerDao();
        modelDao.createElement(object);
    }
}
