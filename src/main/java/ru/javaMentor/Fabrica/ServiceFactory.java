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

import java.io.FileInputStream;
import java.util.Properties;

public class ServiceFactory {
    public ServiceDAO getServiceDAO(String type, Properties properties) {

        String dbUrl = properties.getProperty("db.JDBC");
        String dbUr2 = properties.getProperty("db.Hibernate");
        System.out.println(dbUr2 + " 11 " + dbUrl);
        if (type.equals(dbUr2))
            return ServiceDaoHiberbate.getInstance();
        if (type.equals(dbUrl))
            return UserServiceJDBC.getInstance();
        throw new IllegalArgumentException("type=" + type);
    }
}

