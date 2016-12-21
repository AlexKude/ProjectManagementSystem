package ua.goit.factory;

import ua.goit.dao.CustomerDao;
import ua.goit.dao.ModelDao;

import java.util.List;



public class CustomerFactory implements ModelFactory {
    @Override
    public void createElement(List list) {
        ModelDao modelDao = new CustomerDao();
        modelDao.createElement(list);
    }
}
