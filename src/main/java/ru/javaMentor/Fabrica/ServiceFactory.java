package ru.javaMentor.Fabrica;
/*
 *
 *@Data 06.02.2020
 *@autor Fedorov Yuri
 *@project CRUD_HIBERNATE
 *
 */

import ru.javaMentor.service.ServiceDAO;
import ru.javaMentor.service.ServiceDaoHiberbate;
import ru.javaMentor.service.UserServiceJDBC;
import ru.javaMentor.util.HDBConnection;
import ru.javaMentor.util.PropertyReader;

import java.io.*;
import java.util.Properties;

public class ServiceFactory {
    private final String dbPropPath = "resources" + File.separator + "db.properties";
    private Properties properties;

    public ServiceDAO getServiceDAO( ) {
        Properties properties = PropertyReader.getProperties(HDBConnection.class.getClassLoader().getResourceAsStream("db.properties"));


        String dbUrl = properties.getProperty("userDao");
        if (dbUrl.equals("Hibernate"))
            return ServiceDaoHiberbate.getInstance();
        if (dbUrl.equals("JDBC"))
            return UserServiceJDBC.getInstance();
        throw new IllegalArgumentException("type=" );
    }
}

