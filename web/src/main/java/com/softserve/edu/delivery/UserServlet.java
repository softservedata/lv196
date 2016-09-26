package com.softserve.edu.delivery;

import com.softserve.edu.delivery.dao.UserDao;
import com.softserve.edu.delivery.dao.impl.UserDaoImpl;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.dto.UserProfileDto;
import com.softserve.edu.delivery.dto.UserProfileFilterDto;
import com.softserve.edu.delivery.service.FeedbackService;
import com.softserve.edu.delivery.service.UserService;
import com.softserve.edu.delivery.service.impl.FeedbackServiceImpl;
import com.softserve.edu.delivery.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class UserServlet extends HttpServlet {
	
	private UserDao userDao;
    private UserServiceImpl userService;

    public UserServlet() {
        super();
        userDao = new UserDaoImpl();
        userService = new UserServiceImpl(userDao);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        if(action==null) {
            //	 UserProfileFilterDto filter = new UserProfileFilterDto(null);
            List<User> usersList = userService.getAll();

            request.setAttribute("usersList", usersList);

            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/users.jsp");
            view.forward(request,response);
        }
        else if(action.equals("changeStatus")) {
            String status = request.getParameter("status");
            Boolean newStatus = Boolean.parseBoolean(status);
            String email = request.getParameter("id");
            User user = userService.changeUserStatus2(email, newStatus);
            List<User> usersList = userService.getAll();
            request.setAttribute("usersList", usersList);
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/users.jsp");
            view.forward(request, response);
        }
    }

}
