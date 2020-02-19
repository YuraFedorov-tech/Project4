package ru.javaMentor.servlets;
/*
 *
 *@Data 02.02.2020
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

@WebServlet("/deleteUser")
public class DeletingUserServlet extends HttpServlet {
    private  ServiceDAO serviceDAO;

    @Override
    public void init() throws ServletException {
        ServiceFactory serviceFactory=new ServiceFactory();


       // serviceDAO = serviceFactory.getServiceDAO("Hibernate", properties);
        serviceDAO = serviceFactory.getServiceDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] items = req.getParameterValues("Delete");
//assuming Order is your order class and orderList is your item list

            for (String str : items) {
                try {
                    serviceDAO.removeUser(Long.parseLong(str));
                } catch (Throwable e) {
                    e.printStackTrace();
                }


            }

        resp.sendRedirect(req.getContextPath() + "/work");
    }
}
