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
    private SessionFactory sessionFactory;


    private ServiceDaoHiberbate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static ServiceDaoHiberbate getInstance() {
        if (serviceDaoHiberbate == null) {
            HDBConnection hdbConnection=HDBConnection.getHDBConnection();
            serviceDaoHiberbate = new ServiceDaoHiberbate(hdbConnection.getSessionFactory());
        }
        return serviceDaoHiberbate;
    }


    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        List<User> cars = new UserDaoHiberbate(session).findAll();
        session.close();
        return cars;
    }

    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        new UserDaoHiberbate(session).save(user);
        session.close();
    }

    public void removeUser(Long id) {
        Session session = sessionFactory.openSession();
        new UserDaoHiberbate(session).delete(id);
        session.close();
    }

    public User findUserAtId(Long id) {
        Session  session =sessionFactory.openSession();
        User user = new UserDaoHiberbate(session).find(id);
        session.close();
        return user;
    }

    public void update(User user) {
        Session session = sessionFactory.openSession();
        new UserDaoHiberbate(session).update(user);
        session.close();

    }
}
