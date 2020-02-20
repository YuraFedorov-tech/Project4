package ru.javaMentor.Dao;
/*
 *
 *@Data 02.02.2020
 *@autor Fedorov Yuri
 *@project CRUD_HIBERNATE
 *
 */

import org.hibernate.*;
import org.hibernate.query.Query;
import ru.javaMentor.model.User;

import java.util.List;

public class UserDaoHiberbate implements CrudDao<User> {
    private static SessionFactory sessionFactory;
    private static UserDaoHiberbate userDaoHiberbate;

    private UserDaoHiberbate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public static UserDaoHiberbate getInstance(SessionFactory sessionFactory) {
        if (userDaoHiberbate == null) {
            userDaoHiberbate=new UserDaoHiberbate( sessionFactory);
        }
        return userDaoHiberbate;
    }

    @Override
    public User find(Long id) {
        Session session = sessionFactory.openSession();
        return session.byId(User.class).load(id);
    }

    @Override
    public void save(User model) {
        Session session = sessionFactory.openSession();
        session.save(model);
        session.close();
    }

    @Override
    public void update(User model) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int age9 = model.getAge();
        try {
            Query query = session.createQuery("update User set name=:name ,color=:color, age=:age where id = :id  ");
            query.setParameter("name", model.getName());
            query.setParameter("color", model.getColor());
            query.setParameter("id", model.getId());
            query.setParameter("age", age9);
            int result = query.executeUpdate();
            transaction.commit();
        } catch (HibernateException he) {
            transaction.rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        String hql = "DELETE User WHERE id = :id";
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            int rows = query.executeUpdate();
            transaction.commit();
        } catch (HibernateException he) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM User");
        List<User> users = query.list();
        return users;
    }
}
