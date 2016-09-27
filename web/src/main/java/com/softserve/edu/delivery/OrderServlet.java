package com.softserve.edu.delivery;

import com.softserve.edu.delivery.dao.CityDao;
import com.softserve.edu.delivery.dao.FeedbackDao;
import com.softserve.edu.delivery.dao.OrderDao;
import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.dao.impl.CityDaoImpl;
import com.softserve.edu.delivery.dao.impl.FeedbackDaoImpl;
import com.softserve.edu.delivery.dao.impl.OrderDaoImpl;
import com.softserve.edu.delivery.dao.impl.UserDaoImpl;
import com.softserve.edu.delivery.domain.Offer;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.dto.OrderForListDto;
import com.softserve.edu.delivery.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Ivan Synyshyn on 24.09.2016.
 */
public class OrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        OrderDao orderDao = new OrderDaoImpl();
        UserDao userDao = new UserDaoImpl();
        CityDao cityDao = new CityDaoImpl();
        FeedbackDao feedbackDao = new FeedbackDaoImpl();

        OrderServiceImpl ord = new OrderServiceImpl(orderDao, userDao, cityDao, feedbackDao);
        List<OrderForListDto> orderList = new ArrayList<>();
//        orderList = ord.getOrdersByCityFrom("Lviv");
        /*----------------------------------------*/
//        orderList = ord.getOrdersByCityTo("Dnipro");
        /*----------------------------------------*/
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = dateFormat.parse("28/09/2016");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp arrTime = new Timestamp(date.getTime());
        orderList = ord.getOrdersByArriwalDate(arrTime);
//        /*----------------------------------------*/
//        BigDecimal mass = BigDecimal.valueOf(60);
//        orderList = ord.getOrdersByWeight(mass);
        /*----------------------------------------*/
        req.setAttribute("orderList", orderList);

        req.getRequestDispatcher("/WEB-INF/jsps/orderFiltr.jsp").forward(req, resp);
    }
}
