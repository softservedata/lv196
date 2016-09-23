package com.softserve.edu.delivery;

import com.softserve.edu.delivery.dao.*;
import com.softserve.edu.delivery.dao.impl.*;
import com.softserve.edu.delivery.domain.Offer;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.service.OrderService;
import com.softserve.edu.delivery.service.impl.OrderServiceImpl;

import javax.enterprise.context.SessionScoped;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class AppServlet extends HttpServlet {

    private OrderDao orderDao;
    private UserDao userDao;
    private CityDao cityDao;
    private FeedbackDao feedbackDao;
    private OfferDao offerDao;
    private OrderService os;
    private static Order lastViewOrder;

    public AppServlet() {
        super();
        orderDao = new OrderDaoImpl();
        userDao = new UserDaoImpl();
        cityDao = new CityDaoImpl();
        feedbackDao = new FeedbackDaoImpl();
        offerDao = new OfferDaoImpl();
        os=new OrderServiceImpl(orderDao,userDao,cityDao,feedbackDao);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equals("writeFeedback")) {
            final FeedbackDTO feedbackDTO=new FeedbackDTO();
            final Order order = new Order();
            String message = req.getParameter("message");
            String rate = req.getParameter("rate");
            feedbackDTO.setRate(Integer.parseInt(rate));
            feedbackDTO.setText(message);
            feedbackDTO.setOrder(lastViewOrder);
            os.addFeedback(feedbackDTO, lastViewOrder.getCustomer().getEmail());
            req.getRequestDispatcher("writeFeedback.jsp").forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("getAll")) {
            request.setAttribute("orderList", orderDao.findAll());
            request.getRequestDispatcher("ordersList.jsp").forward(request, response);
        }
        else if(action.equals("getLast")){
            response.getWriter().print("Please click on order in Order List");
        }
        else if(action.equals("showOrderById")){
            Long id = getId(request);
            lastViewOrder = orderDao.findOne(id).get();
//            List<Offer> offers = offerDao.getAllOffersByOrder(lastViewOrder);
//            List<Offer> offers = offerDao.findAll();
            request.setAttribute("order", lastViewOrder );
            request.setAttribute("offerList", offerDao.getAllOffersByOrder(lastViewOrder));
            request.getRequestDispatcher("orderInfo.jsp").forward(request, response);
        }

    }
    private Long getId(HttpServletRequest request) {
        String paramId = request.getParameter("id");
        return Long.valueOf(paramId);
    }
}
