package ru.javaMentor.service;
/*
 *
 *@Data 29.01.2020
 *@autor Fedorov Yuri
 *@project CRUD
 *
 */


import ru.javaMentor.Dao.UserDaoJDBC;
import ru.javaMentor.model.User;
import ru.javaMentor.util.HDBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserServiceJDBC implements ServiceDAO {
    private Connection connection;
    private static UserServiceJDBC userServiceJDBC;

    private UserServiceJDBC(Connection connection) {
        this.connection = connection;
    }

    public static UserServiceJDBC getInstance() {
        if (userServiceJDBC == null) {
            userServiceJDBC = new UserServiceJDBC(HDBConnection.getHDBConnection().getConnection());
            userServiceJDBC.create();
            System.out.println("init JDBC");
        }
        return userServiceJDBC;
    }

    public static UserServiceJDBC getUserServiceJDBC() {
        return userServiceJDBC;
    }

    public List<User> getAllUsers() {
        return new UserDaoJDBC(connection).findAll();
    }

    public void create() {
        new UserDaoJDBC(connection).create();
    }

    public void addUser(User user) {
        new UserDaoJDBC(connection).save(user);
    }

    public void removeUser(Long id) {
        new UserDaoJDBC(connection).delete(id);
    }

    public void update(User user) {
        new UserDaoJDBC(connection).update(user);
    }

    public User findUserAtId(Long id) {
        return new UserDaoJDBC(connection).find(id);
    }
}
