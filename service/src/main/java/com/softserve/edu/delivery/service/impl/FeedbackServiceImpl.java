package com.softserve.edu.delivery.service.impl;

import com.softserve.edu.delivery.domain.Feedback;
import com.softserve.edu.delivery.domain.FeedbackFilter;
import com.softserve.edu.delivery.domain.Order;
import com.softserve.edu.delivery.domain.User;
import com.softserve.edu.delivery.dto.FeedbackDto;
import com.softserve.edu.delivery.dto.FeedbackFilterDTO;
import com.softserve.edu.delivery.repository.FeedbackRepository;
import com.softserve.edu.delivery.repository.OrderRepository;
import com.softserve.edu.delivery.repository.UserRepository;
import com.softserve.edu.delivery.repository.impl.FeedbackRepositoryCustomImpl;
import com.softserve.edu.delivery.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service("feedbackService")
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private FeedbackRepositoryCustomImpl feedbackRepositoryCustom;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private FeedbackFilter feedbackFilter;

    private long totalItemsNumber;

    public FeedbackServiceImpl() {
    }

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, FeedbackRepositoryCustomImpl
            feedbackRepositoryCustom, UserRepository userRepository, OrderRepository orderRepository) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackRepositoryCustom = feedbackRepositoryCustom;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    private String processRequestText(String text) {
        if (text.equals("undefined")) {
            return "%";
        } else {
            return "%" + text + "%";
        }
    }

    private Integer processRequestRate(String rate) {
        try {
            return Integer.parseInt(rate);
        } catch (IllegalArgumentException e) {
            return 0;
        }
    }

    private Timestamp processRequestCreatedOn(String createdOn) {
        try {
            return new Timestamp(Long.parseLong(createdOn));
        } catch (IllegalArgumentException e) {
            return Timestamp.valueOf(LocalDateTime.MIN);
        }
    }

    private Boolean processRequestApproved(String approved) {
        if (approved.toLowerCase().contains("tru")) {
            feedbackFilter.setApproved1(true);
            return true;
        } else if (approved.toLowerCase().contains("fal")) {
            feedbackFilter.setApproved1(false);
            return false;
        }
        feedbackFilter.setApproved1(false);
        return true;
    }

    private String processRequestSortOrder(String sortOrder) {
        if (sortOrder.toLowerCase().contains("tru")) {
            return "desc";
        }
        return "asc";
    }

    private List<FeedbackDto> copyFeedbackListToDTOList(List<Feedback> feedbackList) {
        List<FeedbackDto> listFeedbackDTO = new ArrayList<>();
        feedbackList.forEach(f ->
                listFeedbackDTO.add(copyFeedbackToDTO(f))
        );
        return listFeedbackDTO;
    }

    public FeedbackDto copyFeedbackToDTO(Feedback feedback) {
        FeedbackDto feedbackDTO = new FeedbackDto();
        feedbackDTO.setFeedbackId(feedback.getFeedbackId());
        feedbackDTO.setOrderId(feedback.getOrder().getId());
        feedbackDTO.setText(feedback.getText());
        feedbackDTO.setUserName(feedback.getUser().getFirstName() + " " + feedback.getUser().getLastName());
        feedbackDTO.setUserEmail(feedback.getUser().getEmail());
        feedbackDTO.setRate(feedback.getRate());
        feedbackDTO.setApproved(feedback.getApproved());
        Optional<String> oApprovedDriverName = feedbackRepository.getApprovedDriverName(feedback.getOrder().getId());
        if (oApprovedDriverName.isPresent()) {
            feedbackDTO.setTransporterName(oApprovedDriverName.get());
        } else {
            throw new NoSuchElementException("Driver name for order with id " + feedbackDTO.getOrderId() + " not found");
        }
        Optional<String> oApprovedDriverEmail = feedbackRepository.getApprovedDriverEmail(feedback.getOrder().getId());
        if (oApprovedDriverEmail.isPresent()) {
            feedbackDTO.setTransporterEmail(oApprovedDriverEmail.get());
        } else {
            throw new NoSuchElementException("Driver email for order with id " + feedbackDTO.getOrderId() + " not found");
        }
        feedbackDTO.setCreatedOn(feedback.getCreatedOn());
        return feedbackDTO;
    }


    public Feedback copyDTOToFeedback(FeedbackDto feedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(feedbackDTO.getFeedbackId());
        Optional<User> oUser = userRepository.findOneOpt(feedbackDTO.getUserEmail());
        if (oUser.isPresent()) {
            feedback.setUser(oUser.get());
        } else {
            throw new NoSuchElementException("User with id " + feedbackDTO.getUserEmail() + " not found");
        }
        Optional<Order> oOrder = orderRepository.findOneOpt(feedbackDTO.getOrderId());
        if (oOrder.isPresent()) {
            feedback.setOrder(oOrder.get());
        } else {
            throw new NoSuchElementException("Order with id " + feedbackDTO.getOrderId() + " not found");
        }
        feedback.setText(feedbackDTO.getText());
        feedback.setRate(feedbackDTO.getRate());
        feedback.setApproved(feedbackDTO.getApproved());
        feedback.setCreatedOn(feedbackDTO.getCreatedOn());
        return feedback;
    }


    @Override
    @Transactional
    public List<FeedbackDto> findAll() {
        return copyFeedbackListToDTOList(feedbackRepository.findAll());
    }

    @Override
    @Transactional
    public void changeFeedbackStatus(Long id, boolean status) {

        Optional<Feedback> oFeedback = feedbackRepository.findOneOpt(id);

        if (oFeedback.isPresent()) {
            Feedback feedback = oFeedback.get();
            feedback.setApproved(status);
            feedbackRepository.save(feedback);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    @Transactional
    public void save(FeedbackDto feedbackDTO) {
        Feedback feedback = copyDTOToFeedback(feedbackDTO);
        feedbackRepository.save(feedback);
    }

    @Override
    @Transactional
    public void update(FeedbackDto feedbackDTO) {
        Feedback feedback = copyDTOToFeedback(feedbackDTO);
        if (feedbackRepository.exists(feedback.getFeedbackId())) {
            feedbackRepository.save(feedback);
        } else {
            throw new NoSuchElementException("The feedback doesn't exist");
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {

        Optional<Feedback> oFeedback = feedbackRepository.findOneOpt(id);
        if (oFeedback.isPresent()) {
            feedbackRepository.delete(oFeedback.get());
        } else {
            throw new NoSuchElementException("The feedback doesn't exist");
        }

    }

    @Override
    @Transactional
    public FeedbackDto findOne(Long id) {

        Feedback feedback;

        Optional<Feedback> oFeedback = feedbackRepository.findOneOpt(id);

        if (oFeedback.isPresent()) {
            feedback = oFeedback.get();
        } else {
            throw new NoSuchElementException();
        }

        return copyFeedbackToDTO(feedback);
    }

    @Override
    @Transactional
    public User getUser(String email) {
        Optional<User> oUser = userRepository.findOneOpt(email);
        if (oUser.isPresent()) {
            return oUser.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    @Transactional
    public Order getOrder(Long id) {
        Optional<Order> oOrder = orderRepository.findOneOpt(id);
        if (oOrder.isPresent()) {
            return oOrder.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<FeedbackDto> findFiltered(FeedbackFilterDTO feedbackFilterDTO) {

        feedbackFilter.setText(processRequestText(feedbackFilterDTO.getText()));
        feedbackFilter.setRate(processRequestRate(feedbackFilterDTO.getRate()));
        feedbackFilter.setUserName(processRequestText(feedbackFilterDTO.getUserName()));
        feedbackFilter.setTransporterName(processRequestText(feedbackFilterDTO.getTransporterName()));
        feedbackFilter.setCreatedOn(processRequestCreatedOn(feedbackFilterDTO.getCreatedOn()));
        feedbackFilter.setApproved0(processRequestApproved(feedbackFilterDTO.getApproved()));
        feedbackFilter.setSortOrder(processRequestSortOrder(feedbackFilterDTO.getSortOrder()));
        feedbackFilter.setSortBy(feedbackFilterDTO.getSortBy());
        feedbackFilter.setCurrentPage(feedbackFilterDTO.getCurrentPage());
        feedbackFilter.setItemsPerPage(feedbackFilterDTO.getItemsPerPage());

        List<Feedback> feedbackList = feedbackRepositoryCustom.findFiltered(feedbackFilter);

        totalItemsNumber = feedbackRepositoryCustom.getTotalItemsNumber(feedbackFilter);

        return copyFeedbackListToDTOList(feedbackList);
    }

    @Override
    public long getTotalItemsNumber() {
        return totalItemsNumber;
    }

    @Override
    @Transactional
    public FeedbackDto findByFeedbackId(Long id) {
        Optional<Feedback> oFeedback = feedbackRepository.findOneOpt(id);
        if (oFeedback.isPresent()) {
            return copyFeedbackToDTO(oFeedback.get());
        } else {
            throw new NoSuchElementException("No feedback with id: " + id + " found.");
        }
    }

    @Override
    public FeedbackDto getCustomerFeedback(Long id) {
        Feedback feedback = feedbackRepository.getCustomerFeedback(id);
        if (feedback != null) {
            return copyFeedbackToDTO(feedback);
        }

        return null;
    }

    @Override
    public void addFeedback(FeedbackDto dto, String email) {
        if (dto == null) {
            throw new IllegalArgumentException("Feedback dto must not be null");
        }

        User user = userRepository.findOneOpt(email)
                .orElseThrow(() -> new IllegalArgumentException("No such user with email: " + email));

        Order order = orderRepository.findOneOpt(dto.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("No such order with id: " + dto.getOrderId()));

        Feedback feedback = new Feedback();
        feedback.setOrder(order);
        feedback.setUser(user);
        feedback.setRate(dto.getRate());
        feedback.setText(dto.getText());
        feedback.setApproved(false);
        feedback.setCreatedOn(new Timestamp(new Date().getTime()));
        feedbackRepository.save(feedback);
    }

    @Override
    public FeedbackDto getFeedback(Long orderId, String email) {
        Feedback feedback = feedbackRepository
                .getFeedbackByOrderIdAndEmail(orderId, email)
                .stream()
                .findFirst()
                .orElse(null);
        if (feedback == null) {
            return new FeedbackDto();
        } else {
            FeedbackDto feedbackDTO = new FeedbackDto();
            feedbackDTO.setCreatedOn(feedback.getCreatedOn());
            feedbackDTO.setApproved(feedback.getApproved());
            feedbackDTO.setText(feedback.getText());
            feedbackDTO.setRate(feedback.getRate());
            feedbackDTO.setFeedbackId(feedback.getFeedbackId());
            feedbackDTO.setOrderId(feedback.getOrder().getId());
            feedbackDTO.setUserEmail(feedback.getUser().getEmail());
            return feedbackDTO;
        }
    }

    @Override
    public void updateFeedback(FeedbackDto dto) {
        Feedback feedback = feedbackRepository.findOneOpt(dto.getFeedbackId())
                .orElseThrow(() -> new IllegalArgumentException("No such feedback with id: " + dto.getFeedbackId()));
        feedback.setRate(dto.getRate());
        feedback.setText(dto.getText());
        feedbackRepository.save(feedback);
    }

}