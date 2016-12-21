package ua.goit.factory;

import ua.goit.dao.ModelDao;
import ua.goit.dao.SkillDao;

import java.util.List;



public class SkillFactory implements ModelFactory {
    @Override
    public void createElement(List list) {
        ModelDao modelDao = new SkillDao();
        modelDao.createElement(list);
    }
}
