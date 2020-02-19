package ru.javaMentor.service;

import org.hibernate.Session;
import ru.javaMentor.Dao.UserDaoHiberbate;
import ru.javaMentor.model.User;

import java.util.List;

/*
 *
 *@Data 06.02.2020
 *@autor Fedorov Yuri
 *@project CRUD_HIBERNATE
 *
 */
public interface ServiceDAO {
    public List<User> getAllUsers();

    public void addUser(User user);

    public void removeUser(Long id);

    public User findUserAtId(Long id);

    public void update(User user);
}
