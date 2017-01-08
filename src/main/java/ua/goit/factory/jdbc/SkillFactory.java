package ua.goit.factory.jdbc;

import ua.goit.dao.jdbc.ModelDao;
import ua.goit.dao.jdbc.SkillDao;



public class SkillFactory<ModelObject> implements ModelFactory<ModelObject> {
    @Override
    public void createElement(ModelObject object) {
        ModelDao modelDao = new SkillDao();
        modelDao.createElement(object);
    }
}
