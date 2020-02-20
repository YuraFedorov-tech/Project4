package ru.javaMentor.service;
/*
 *
 *@Data 02.02.2020
 *@autor Fedorov Yuri
 *@project CRUD_HIBERNATE
 *
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javaMentor.Dao.UserDaoHiberbate;
import ru.javaMentor.model.User;
import ru.javaMentor.util.HDBConnection;

import java.util.List;


public class ServiceDaoHiberbate implements ServiceDAO {
    private static ServiceDaoHiberbate serviceDaoHiberbate;
    private static SessionFactory sessionFactory;
    private static UserDaoHiberbate userDaoHiberbate;

    private ServiceDaoHiberbate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static ServiceDaoHiberbate getInstance() {
        if (serviceDaoHiberbate == null) {
            HDBConnection hdbConnection=HDBConnection.getHDBConnection();
            serviceDaoHiberbate = new ServiceDaoHiberbate(hdbConnection.getSessionFactory());
            userDaoHiberbate= UserDaoHiberbate.getInstance( sessionFactory);
        }
        return serviceDaoHiberbate;
    }


    public List<User> getAllUsers() {
      return   userDaoHiberbate.findAll();
    }

    public void addUser(User user) {userDaoHiberbate.save(user);
    }

    public void removeUser(Long id) {
        userDaoHiberbate.delete(id);
    }

    public User findUserAtId(Long id) {
        return userDaoHiberbate.find(id);
    }

    public void update(User user) {userDaoHiberbate.update(user); }
}
