package com.softserve.edu.delivery.dto;

import com.softserve.edu.delivery.domain.Feedback;

import java.sql.Timestamp;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Ivan Rudnytskyi on 29.09.2016.
 */
public class FilterFeedbackDTO {

    private FilterFeedbackDTO(){}

    /*              feedbackId              */
    public static Predicate<Feedback> feedbackIdLessThan(long feedbackId){
        return f -> f.getFeedbackId() < feedbackId;
    }

    public static Predicate<Feedback> feedbackIdLessThanOrEqual(long id){
        return f -> f.getFeedbackId() <= id;
    }

    public static Predicate<Feedback> feedbackIdGreaterThan(long id){
        return f -> f.getFeedbackId() > id;
    }

    public static Predicate<Feedback> feedbackIdGreaterThanOrEqual(long id){
        return f -> f.getFeedbackId() >= id;
    }

    public static Predicate<Feedback> feedbackIdBetweenExcluding(long id0, long id1){
        return f -> (f.getFeedbackId() > id0 && f.getFeedbackId() < id1);
    }

    public static Predicate<Feedback> feedbackIdBetweenIncluding(long id0, long id1){
        return f -> (f.getFeedbackId() >= id0 && f.getFeedbackId() <= id1);
    }

    /*              orderId              */
    public static Predicate<Feedback> OrderIdLessThan(long OrderId){
        return f -> f.getOrder().getId() < OrderId;
    }

    public static Predicate<Feedback> OrderIdLessThanOrEqual(long id){
        return f -> f.getOrder().getId() <= id;
    }

    public static Predicate<Feedback> OrderIdGreaterThan(long id){
        return f -> f.getOrder().getId() > id;
    }

    public static Predicate<Feedback> OrderIdGreaterThanOrEqual(long id){
        return f -> f.getOrder().getId() >= id;
    }

    public static Predicate<Feedback> OrderIdBetweenExcluding(long id0, long id1){
        return f -> (f.getOrder().getId() > id0 && f.getOrder().getId() < id1);
    }

    public static Predicate<Feedback> OrderIdBetweenIncluding(long id0, long id1){
        return f -> (f.getOrder().getId() >= id0 && f.getOrder().getId() <= id1);
    }

    /*              text              */
    public static Predicate<Feedback> textContains(String text){
        return f -> f.getText().contains(text);
    }

    public static Predicate<Feedback> textContainsIgnoreCase(String text){
        return f -> f.getText().toLowerCase().contains(text.toLowerCase());
    }

    /*              user              */
    public static Predicate<Feedback> userFirstnameContains(String firstname){
        return f -> f.getUser().getFirstName().contains(firstname);
    }

    public static Predicate<Feedback> userFirstnameContainsIgnoreCase(String firstname){
        return f -> f.getUser().getFirstName().toLowerCase().contains(firstname.toLowerCase());
    }

    public static Predicate<Feedback> userLastnameContains(String lastname){
        return f -> f.getUser().getLastName().contains(lastname);
    }

    public static Predicate<Feedback> userLastnameContainsIgnoreCase(String lastname){
        return f -> f.getUser().getLastName().toLowerCase().contains(lastname.toLowerCase());
    }

    /*              rate              */
    public static Predicate<Feedback> rateLessThan(int rate){
        return f -> f.getRate() < rate;
    }

    public static Predicate<Feedback> rateLessThanOrEqual(int id){
        return f -> f.getRate() <= id;
    }

    public static Predicate<Feedback> rateGreaterThan(int id){
        return f -> f.getRate() > id;
    }

    public static Predicate<Feedback> rateGreaterThanOrEqual(int id){
        return f -> f.getRate() >= id;
    }

    public static Predicate<Feedback> rateBetweenExcluding(int id0, int id1){
        return f -> (f.getRate() > id0 && f.getRate() < id1);
    }

    public static Predicate<Feedback> rateBetweenIncluding(int id0, int id1){
        return f -> (f.getRate() >= id0 && f.getRate() <= id1);
    }

    /*              approved              */
    public static Predicate<Feedback> status(boolean status){
        return f -> f.getApproved() == status;
    }

    public static List<Feedback> filteredList (List<Feedback> inList, Predicate<Feedback> predicate){
        return inList.stream().filter(predicate).collect(Collectors.toList());
    }

    /*              created              */
    public static Predicate<Feedback> createdBefore(Timestamp date){
        return f -> f.getCreatedOn().before(date);
    }

    public static Predicate<Feedback>  createdAfter(Timestamp date){
        return f -> f.getCreatedOn().after(date);
    }

    public static Predicate<Feedback>  createdOn(Timestamp date){
        return f -> f.getCreatedOn().equals(date);
    }

}
