package ua.goit.factory.jdbc;

import ua.goit.dao.jdbc.CustomerDao;
import ua.goit.dao.jdbc.ModelDao;

import java.util.List;



public class CustomerFactory implements ModelFactory {
    @Override
    public void createElement(List list) {
        ModelDao modelDao = new CustomerDao();
        modelDao.createElement(list);
    }
}
