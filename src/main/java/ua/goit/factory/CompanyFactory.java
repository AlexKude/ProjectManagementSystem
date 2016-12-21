package ua.goit.factory;

import ua.goit.dao.CompanyDao;
import ua.goit.dao.ModelDao;

import java.util.List;

/**
 * Created by Main Server on 18.12.2016.
 */
public class CompanyFactory implements ModelFactory {

    @Override
    public void createElement(List list) {
        ModelDao modelDao = new CompanyDao();
        modelDao.createElement(list);
    }
}
