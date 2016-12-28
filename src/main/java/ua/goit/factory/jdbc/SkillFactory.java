package ua.goit.factory.jdbc;

import ua.goit.dao.jdbc.ModelDao;
import ua.goit.dao.jdbc.SkillDao;
import ua.goit.factory.jdbc.ModelFactory;

import java.util.List;



public class SkillFactory implements ModelFactory {
    @Override
    public void createElement(List list) {
        ModelDao modelDao = new SkillDao();
        modelDao.createElement(list);
    }
}
