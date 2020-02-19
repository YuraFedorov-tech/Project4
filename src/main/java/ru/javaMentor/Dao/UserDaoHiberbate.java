package ru.javaMentor.Dao;
/*
 *
 *@Data 02.02.2020
 *@autor Fedorov Yuri
 *@project CRUD_HIBERNATE
 *
 */

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.javaMentor.model.User;

import java.util.List;

public class UserDaoHiberbate implements CrudDao<User> {
    private Session session;

    public UserDaoHiberbate(Session session) {
        this.session = session;
    }

    @Override
    public User find(Long id) {
        return session.byId(User.class).load(id);
    }

    @Override
    public void save(User model) {
        session.save(model);
    }

    @Override
    public void update(User model) {
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
        Query query = session.createQuery("FROM User");
        List<User> users = query.list();
        return users;
    }
}
