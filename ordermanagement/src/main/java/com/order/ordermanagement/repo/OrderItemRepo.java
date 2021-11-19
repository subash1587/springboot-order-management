package com.order.ordermanagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.order.ordermanagement.entity.OrderItemEntity;
import com.order.ordermanagement.model.custom.ItemsPerOrderCount;

public interface OrderItemRepo extends JpaRepository<OrderItemEntity, Integer>{

	@Query("select new com.order.ordermanagement.model.custom.ItemsPerOrderCount(o.order, count(o.item))\r\n"
			+ "from OrderItemEntity o \r\n"
			+ "group by o.order")
	List<ItemsPerOrderCount> findItemsCountPerOrder();
}
