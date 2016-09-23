package com.softserve.edu.delivery;

import com.softserve.edu.delivery.dao.CityDao;
import com.softserve.edu.delivery.dao.FeedbackDao;
import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.dao.impl.CityDaoImpl;
import com.softserve.edu.delivery.dao.impl.FeedbackDaoImpl;
import com.softserve.edu.delivery.dao.impl.OrderDaoImpl;
import com.softserve.edu.delivery.dao.impl.UserDaoImpl;
import com.softserve.edu.delivery.service.OrderService;
import com.softserve.edu.delivery.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppServlet extends HttpServlet {

    private OrderDao orderDao;
    private UserDao userDao;
    private CityDao cityDao;
    private FeedbackDao feedbackDao;
    private OrderService os;

    public AppServlet() {
        super();
        orderDao = new OrderDaoImpl();
        userDao = new UserDaoImpl();
        cityDao = new CityDaoImpl();
        feedbackDao = new FeedbackDaoImpl();
        os = new OrderServiceImpl(orderDao,userDao,cityDao,feedbackDao);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action==null) {
            request.setAttribute("orderList", orderDao.findAll());
            request.getRequestDispatcher("ordersList.jsp").forward(request, response);
        }
        else if(action.equals("showorderbyid")){
            Long id = getId(request);
            request.setAttribute("orderList", orderDao.findOne(id));
            request.getRequestDispatcher("orderList.jsp").forward(request, response);

        }

    }
    private Long getId(HttpServletRequest request) {
        String paramId = request.getParameter("id");
        return Long.valueOf(paramId);
    }
}
