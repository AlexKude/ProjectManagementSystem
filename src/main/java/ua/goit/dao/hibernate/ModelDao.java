package ua.goit.dao.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by Main Server on 28.12.2016.
 */
public interface ModelDao <ModelObject> {

    public static final EntityManager manager = Persistence.createEntityManagerFactory("NewPersistenceUnit").createEntityManager();

    public List<ModelObject> selectAllElements();

    public void selectElement(int id);

    public void createElement(ModelObject object);

    public void updateElement(ModelObject object);

    public void deleteElement(int id);
}
