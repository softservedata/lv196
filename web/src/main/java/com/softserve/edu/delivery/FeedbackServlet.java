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
 * @author Ivan Rudnytskyi,  23.09.2016.
 *
 * The servlet provides control of Feedback page
 */
public class FeedbackServlet extends HttpServlet {

    private final FeedbackService fsi = FeedbackServiceImpl.getInstance();

    /**
     *
     * @param list - with FeedbackDTO.class objects
     * @return Json-formatted String
     *
     * converts list of FeedbackDTO.class objects to Json-formatted String
     */
    private static String convertToJSON(List<FeedbackDTO> list){

        StringBuffer jsonFeedbackList = new StringBuffer();

        jsonFeedbackList.append("{\"feedbacks\": [");

        for (FeedbackDTO f : list) {
            jsonFeedbackList.append("{\"feedbackId\":").append(f.getFeedbackId()).append(",");
            jsonFeedbackList.append("\"orderId\":").append(f.getOrderId()).append(",");
            jsonFeedbackList.append("\"userName\":" + "\"").append(f.getUserName()).append("\",");
            jsonFeedbackList.append("\"transporterName\":" + "\"").append(f.getTransporterName()).append("\",");
            jsonFeedbackList.append("\"rate\":").append(f.getRate()).append(",");
            jsonFeedbackList.append("\"text\":" + "\"").append(f.getText()).append("\",");
            if (f.getApproved()) {
                jsonFeedbackList.append("\"approved\":" + "\"<select> <option value=true" +
                        " selected>True</option><option value=false>False</option></select>\"" + "},");
            } else {
                jsonFeedbackList.append("\"approved\":" + "\"<select> <option value=true" +
                        ">True</option><option value=false selected>False</option></select>\"" + "},");
            }
        }

        jsonFeedbackList.deleteCharAt(jsonFeedbackList.length() - 1);

        jsonFeedbackList.append("]}");

        return jsonFeedbackList.toString();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/feedback.jsp");
        view.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<FeedbackDTO> allFeedbacksList = fsi.getAllFeedbacks();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(convertToJSON(allFeedbacksList));
    }

}
