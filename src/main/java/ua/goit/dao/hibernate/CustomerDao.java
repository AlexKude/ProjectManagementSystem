package ua.goit.dao.hibernate;

import ua.goit.model.hibernate.CustomerEntity;
import ua.goit.view.ConsoleHelper;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;



public class CustomerDao<ModelObject> implements ModelDao<ModelObject> {
    @Override
    public List<ModelObject> selectAllElements() {
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            Query nativeQuery = manager.createNativeQuery("SELECT * FROM customers", CustomerEntity.class);
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
            CustomerEntity customer = manager.find(CustomerEntity.class, id);
            tx.commit();
            ConsoleHelper.writeMessage(customer.toString());
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
            CustomerEntity customer = manager.find(CustomerEntity.class, id);
            manager.remove(customer);
            tx.commit();
        } catch (Exception e) {
            ConsoleHelper.writeMessage("Query failed. Please try again....");
            tx.rollback();
        }
    }
}
