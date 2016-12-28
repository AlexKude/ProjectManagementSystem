package ua.goit.factory.hibernate;

/**
 * Created by Main Server on 28.12.2016.
 */
public interface ModelFactory <ModelObject> {
    public void createElement(ModelObject object);
}
