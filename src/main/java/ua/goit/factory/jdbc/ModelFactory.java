package ua.goit.factory.jdbc;



public interface ModelFactory <ModelObject> {
    void createElement(ModelObject object);
}
