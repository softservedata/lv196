package com.softserve.edu.delivery.service;

import com.softserve.edu.delivery.dto.FeedbackDTO;
import com.softserve.edu.delivery.dto.OrderForAddDto;
import com.softserve.edu.delivery.dto.OrderForListDto;
import java.util.List;
/**
 * Created by Marina Chepurna
 */
public interface OrderService {
    List<OrderForListDto> findAllActiveOrders(String email, int page, int size);
    void addOrder(OrderForAddDto dto, String email);

/**
* Author - Taras Kurdiukov
*/
    //Method for user story - "As customer I want to write transporter feedback."
    void addFeedback (FeedbackDTO dto, String email);
    //Method for user story - "As customer I want to change offer status."
    void changeStatus(String order_id, Boolean offerStatus);
}
