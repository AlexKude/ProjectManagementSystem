package ua.goit.factory;

import ua.goit.dao.ModelDao;
import ua.goit.dao.SkillDao;

import java.util.List;

/**
 * Created by Main Server on 20.12.2016.
 */
public class SkillFactory implements ModelFactory {
    @Override
    public void createElement(List list) {
        ModelDao modelDao = new SkillDao();
        modelDao.createElement(list);
    }
}
