package ua.goit.factory.jdbc;

import ua.goit.dao.jdbc.CompanyDao;
import ua.goit.dao.jdbc.ModelDao;

import java.util.List;



public class CompanyFactory implements ModelFactory {

    @Override
    public void createElement(List list) {
        ModelDao modelDao = new CompanyDao();
        modelDao.createElement(list);
    }
}
