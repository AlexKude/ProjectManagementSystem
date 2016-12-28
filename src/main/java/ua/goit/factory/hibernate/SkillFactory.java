package ua.goit.factory.hibernate;

import ua.goit.dao.hibernate.ModelDao;
import ua.goit.dao.hibernate.SkillDao;



public class SkillFactory<ModelObject> implements ModelFactory<ModelObject> {
    @Override
    public void createElement(ModelObject object) {
        ModelDao modelDao = new SkillDao();
        modelDao.createElement(object);
    }
}
