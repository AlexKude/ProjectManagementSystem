package ua.goit.dao;

import java.util.List;

/**
 * Created by Main Server on 19.12.2016.
 */
public interface ModelDao {
    void selectAllElements();

    void selectElement(int id);

    void createElement(List list);

    void updateElement(List list);

    void deleteElement(int id);
}
