package ru.javaMentor.servlets;
/*
 *
 *@Data 03.02.2020
 *@autor Fedorov Yuri
 *@project CRUD_HIBERNATE
 *
 */

import ru.javaMentor.Fabrica.ServiceFactory;
import ru.javaMentor.model.User;
import ru.javaMentor.service.ServiceDAO;
import ru.javaMentor.service.ServiceDaoHiberbate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebServlet("/updateUser")
public class UpdatingUserServlet extends HttpServlet {
    private  ServiceDAO serviceDAO;
    Long id;

    @Override
    public void init() throws ServletException {
        ServiceFactory serviceFactory=new ServiceFactory();


        // serviceDAO = serviceFactory.getServiceDAO("Hibernate", properties);
        serviceDAO = serviceFactory.getServiceDAO();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          id = Long.valueOf((req.getParameter("id")));
        User user = serviceDAO.findUserAtId(id);
        List<User> users = new ArrayList<>();
        users.add(user);
        req.setAttribute("usersInJDBC", users);
        req.getServletContext().getRequestDispatcher("/jsp/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String color = req.getParameter("color");
        String ageString = req.getParameter("age");
        int age = Integer.parseInt(ageString);
        User user = new User(id, name, color, age);
        serviceDAO.update(user);
        resp.sendRedirect(req.getContextPath() + "/work");

    }
}
