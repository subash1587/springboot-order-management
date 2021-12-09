package com.order.ordermanagement.repo.custom;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.order.ordermanagement.entity.OrderEntity;
import com.order.ordermanagement.model.custom.OrderStatusConverter;

public class OrderRepoCustomImpl implements OrderRepoCustom{

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	OrderStatusConverter orderStatusConverter;
	
	public List<OrderEntity> findOrders(Map<String, String> filters) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<OrderEntity> cq = cb.createQuery(OrderEntity.class);
		Root<OrderEntity> order = cq.from(OrderEntity.class);
		List<Predicate> predicates = new ArrayList<>();
		for(Map.Entry<String, String> filter : filters.entrySet()) {
			switch(filter.getKey()) {
			case "status":
				predicates.add(cb.equal(order.get("status"), orderStatusConverter.convertToEntityAttribute(filter.getValue())));
				break;
			case "order_date":
				predicates.add(cb.equal(order.get("orderDate"), LocalDate.parse(filter.getValue())));
				break;
			case "accepted_date":
				predicates.add(cb.equal(order.get("acceptedDate"), LocalDate.parse(filter.getValue())));
				break;
			case "packaged_date":
				predicates.add(cb.equal(order.get("packagedDate"), LocalDate.parse(filter.getValue())));
				break;
			case "shipped_date":
				predicates.add(cb.equal(order.get("shippedDate"), LocalDate.parse(filter.getValue())));
				break;
			case "delivered_date":
				predicates.add(cb.equal(order.get("deliveredDate"), LocalDate.parse(filter.getValue())));
				break;
			case "cancelled_date":
				predicates.add(cb.equal(order.get("cancelledDate"), LocalDate.parse(filter.getValue())));
				break;
			case "estimated_delivery_date":
				predicates.add(cb.equal(order.get("estimatedDeliveryDate"), LocalDate.parse(filter.getValue())));
				break;
			case "actual_delivery_date":
				predicates.add(cb.equal(order.get("actualDeliveryDate"), LocalDate.parse(filter.getValue())));
				break;
			}
		}
		cq.select(order).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		return em.createQuery(cq).getResultList();
	}

}
