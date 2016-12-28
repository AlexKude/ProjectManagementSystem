package ua.goit.dao.hibernate;

import ua.goit.model.hibernate.SkillEntity;
import ua.goit.view.ConsoleHelper;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;



public class SkillDao<ModelObject> implements ModelDao<ModelObject> {
    @Override
    public List<ModelObject> selectAllElements() {
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            Query nativeQuery = manager.createNativeQuery("SELECT * FROM skills", SkillEntity.class);
            tx.commit();
            List<ModelObject> resultList = nativeQuery.getResultList();
            return resultList;
        } catch (Exception e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
            tx.rollback();
            return null;
        }
    }

    @Override
    public void selectElement(int id) {
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            SkillEntity skill = manager.find(SkillEntity.class, id);
            tx.commit();
            ConsoleHelper.writeMessage(skill.toString());
        } catch (Exception e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
            tx.rollback();
        }
    }

    @Override
    public void createElement(ModelObject object) {
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            manager.merge(object);
            tx.commit();
            ConsoleHelper.writeMessage("Skill was successfully created!");
        } catch (Exception e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
            tx.rollback();
        }
    }

    @Override
    public void updateElement(ModelObject object) {
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            manager.merge(object);
            tx.commit();
            ConsoleHelper.writeMessage("Skill was successfully updated!");
        } catch (Exception e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
            tx.rollback();
        }
    }

    @Override
    public void deleteElement(int id) {
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            SkillEntity skill = manager.find(SkillEntity.class, id);
            manager.remove(skill);
            tx.commit();
            ConsoleHelper.writeMessage("Skill was successfully deleted!");
        } catch (Exception e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
            tx.rollback();
        }
    }
}
