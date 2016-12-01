package com.softserve.edu.delivery.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.softserve.edu.delivery.dto.DataDto;

@Repository
public class DataRepositoryCustomImpl {
	
	private final String COUNT_ORDERS_PER_HOUR = "select hd.hour, count(o.id) "
			+ "from hour_dimension hd left outer join orders o on hd.hour = date_format(arrival_date, '%H') "
			+ "and arrival_date like concat('%',:date,'%') group by hd.hour ";

	private final String COUNT_ORDERS_PER_DAY = "select dd.day, count(o.id) "
			+ "from day_dimension dd left outer join orders o on dd.day = date_format(arrival_date, '%e') "
			+ "and arrival_date like concat(date_format(:month,'%Y-%m'), '%') group by dd.day "
			+ "having dd.day <= datediff(date_add(:month,interval 1 month), :month) ";

	private final String COUNT_ORDERS_PER_MONTH = "select md.month, count(o.id) "
			+ "from month_dimension md left outer join orders o on md.month = date_format(arrival_date, '%M') "
			+ "and arrival_date like concat(date_format(now(),'%Y'), '%') group by md.month_id ";
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<DataDto> countClosedOrdersPerHour(String date) {
		return entityManager
				.createNativeQuery(COUNT_ORDERS_PER_HOUR)
				.setParameter("date", date)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<DataDto> countClosedOrdersPerDay(String month) {
		return entityManager
				.createNativeQuery(COUNT_ORDERS_PER_DAY)
				.setParameter("month", month)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<DataDto> countClosedOrdersMonth() {
		return entityManager
				.createNativeQuery(COUNT_ORDERS_PER_MONTH)
				.getResultList();
	}

}
