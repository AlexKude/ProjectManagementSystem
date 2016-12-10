package ua.goit;

import java.util.List;

/**
 * Created by Main Server on 11.12.2016.
 */
public class CompanyDevelopersDao {
    private String driver = "org.postgresql.Driver";;
    private String url = "jdbc:postgresql://localhost:5432/company";;
    private String user = "user";;
    private String password = "111";

    public boolean insertRecord(){
        return false;
    }

    public List<String> selectRecords(){
        return null;
    }

    public boolean updateRecord(){
        return false;
    }

    public boolean deleteRecord(){
        return false;
    }


}
