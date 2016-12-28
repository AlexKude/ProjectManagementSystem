package ua.goit.dao.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;



public class PostgresDataSource {
    private static PostgresDataSource datasource;
    private ComboPooledDataSource source;

    private PostgresDataSource() throws IOException, SQLException, PropertyVetoException {
        source = new ComboPooledDataSource();
        source.setDriverClass("org.postgresql.Driver");
        source.setJdbcUrl("jdbc:postgresql://localhost:5432/company");
        source.setUser("postgres");
        source.setPassword("postgres");

        source.setMinPoolSize(3);
        source.setAcquireIncrement(5);
        source.setMaxPoolSize(20);
        source.setMaxStatements(180);

    }

    public static PostgresDataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            datasource = new PostgresDataSource();
            return datasource;
        } else {
            return datasource;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.source.getConnection();
    }

}
