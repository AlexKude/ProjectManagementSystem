package ua.goit.dao.jdbc;

import java.util.List;



public interface ModelDao {
    void selectAllElements();

    void selectElement(int id);

    void createElement(List list);

    void updateElement(List list);

    void deleteElement(int id);
}
