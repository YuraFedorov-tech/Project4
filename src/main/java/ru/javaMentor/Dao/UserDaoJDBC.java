package ru.javaMentor.Dao;
/*
 *
 *@Data 29.01.2020
 *@autor Fedorov Yuri
 *@project CRUD
 *
 */


import ru.javaMentor.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBC implements CrudDao<User> {
    Connection connection;
    //language=SQL
    private final String CREATE_TABLE = "CREATE TABLE  IF NOT EXISTS users (id  SERIAL PRIMARY KEY ,name VARCHAR(30),color VARCHAR(30),age int);";
    //language=SQL
    private final String ADD_MODEL = "INSERT INTO users ( name, color, age) values (?,?,?)";
    //language=SQL
    private final String SELECT_ALL = "SELECT * FROM users";
    //language=SQL
    private final String DELETE_MODEL = "DELETE FROM users WHERE id=?";
    //language=SQL
    private final String UPDATE_MODEL = "UPDATE users SET name=?, color=?, age=? WHERE id=?";


    public UserDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_MODEL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getColor());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MODEL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getColor());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setLong(4, user.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Long id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DELETE_MODEL);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public List<User> findAll() {  // public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String color = resultSet.getString("color");
                int age = resultSet.getInt("age");
                users.add(new User(id, name, color, age));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

  //  @Override
    public void create() {
        try {
            Statement statement = connection.createStatement();
            statement.execute(CREATE_TABLE);
            //        statement.executeQuery(CREATE_TABLE);
            int y = 45;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User find(Long id) {// public User findUserAtId(Long id) {
        User user = null;
        try {
            String query = "SELECT * FROM users WHERE ID = ?";
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
             //   Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String color = resultSet.getString("color");
                int age = resultSet.getInt("age");
                user=new User(id, name, color, age);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
