package com.softserve.edu.delivery;

import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.service.FeedbackService;
import com.softserve.edu.delivery.service.impl.FeedbackServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Ivan Rudnytskyi on 23.09.2016.
 */
public class FeedbackServlet extends HttpServlet {

    private final FeedbackService fsi = new FeedbackServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {



        List<FeedbackDTO> allFeedbacksList = fsi.getAllFeedbacks();

        request.setAttribute("allFeedbacksList", allFeedbacksList);
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/feedback.jsp");
        view.forward(request, response);

    }

}
