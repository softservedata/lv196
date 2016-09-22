package com.softserve.edu.delivery;

import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.service.OrderService;
import com.softserve.edu.delivery.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("getOrders")) {
//            request.setAttribute("smsList", smsController.getAll());
            request.getRequestDispatcher("smsList.jsp").forward(request, response);
        }
    }
}
