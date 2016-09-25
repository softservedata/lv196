package com.softserve.edu.delivery;

import com.softserve.edu.delivery.dao.*;
import com.softserve.edu.delivery.dao.impl.*;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.dto.FeedbackDTO;
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
    private OfferDao offerDao;
    private OrderService orderService;
    private Order lastViewOrder;

    public AppServlet() {
        super();
        orderDao = new OrderDaoImpl();
        userDao = new UserDaoImpl();
        cityDao = new CityDaoImpl();
        feedbackDao = new FeedbackDaoImpl();
        offerDao = new OfferDaoImpl();
        orderService = new OrderServiceImpl(orderDao,userDao,cityDao,feedbackDao,offerDao);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equals("writeFeedback")) {
            final FeedbackDTO feedbackDTO=new FeedbackDTO();
            String message = req.getParameter("message");
            String rate = req.getParameter("rate");
            feedbackDTO.setRate(Integer.parseInt(rate));
            feedbackDTO.setText(message);
            feedbackDTO.setOrder(lastViewOrder);
            orderService.addFeedback(feedbackDTO, lastViewOrder.getCustomer().getEmail());
            req.getRequestDispatcher("writeFeedback.jsp").forward(req, resp);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("getAll")) {
            request.setAttribute("orderList", orderService.findAll());
            request.getRequestDispatcher("ordersList.jsp")
                    .forward(request, response);
        }
        else if(action.equals("getFirstPage")){
            response.getWriter()
                    .print("Please click on order in Order List");
        }
        else if(action.equals("changeOfferStatus")){
            String status = request.getParameter("status");
            orderService.changeStatus(getId(request),status);
            logicForOrderInfo(request,response);
        }
        else if(action.equals("showOrderById")){
            Long id = getId(request);
            lastViewOrder = orderService.findOne(id);
            logicForOrderInfo(request,response);
        }
    }

    private Long getId(HttpServletRequest request) {
        String paramId = request.getParameter("id");
        return Long.valueOf(paramId);
    }

    private void logicForOrderInfo(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("order", lastViewOrder );
        request.setAttribute("offerList", orderService.getAllOffersByOrder(lastViewOrder) );
        request.setAttribute("feedback", feedbackDao.findApprovedFeedbackByOrder(lastViewOrder) );
        request.getRequestDispatcher("orderInfo.jsp")
                .forward(request, response);
    }

}
