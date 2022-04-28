package DAO;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;

public interface Dao <T>{

    T get(String id,String name);
    void create(T t);
    default ComboPooledDataSource getDataSource() throws PropertyVetoException{
        ComboPooledDataSource cpds= new ComboPooledDataSource();
        cpds.setJdbcUrl("jdbc:oracle:thin:STUDENT/STUDENT@localhost:1521/XE");
        cpds.setInitialPoolSize(5);
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(7);
        cpds.setMaxPoolSize(300);
        cpds.setMaxStatements(10);
        //cpds.setMaxIdleTime(2);
        cpds.setMaxConnectionAge(2);
        cpds.setNumHelperThreads(10);

        return  cpds;
    }
}
