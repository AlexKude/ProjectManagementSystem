package ua.goit.dao.jdbc;



import ua.goit.view.ConsoleHelper;

import java.sql.Connection;
import java.sql.SQLException;




public class ConnectDao {

   public static Connection connection;

    public static void ConnectDB() {
        try {
            connection = PostgresDataSource.getInstance().getConnection();
        } catch (Exception e) {
            ConsoleHelper.writeMessage("Connction to DB failed. Please try again...");
            System.exit(0);
        }
    }

    public static void closeConnect(){
        try {
            connection.close();
        } catch (SQLException ignore) {

        }
    }
}
