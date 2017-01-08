package ua.goit.dao.jdbc;




public interface ModelDao<ModelObject> {
    void selectAllElements();

    void selectElement(int id);

    void createElement(ModelObject object);

    void updateElement(ModelObject object);

    void deleteElement(int id);
}
