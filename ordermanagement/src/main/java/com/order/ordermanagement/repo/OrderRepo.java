package com.order.ordermanagement.repo;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.order.ordermanagement.entity.CustomerEntity;
import com.order.ordermanagement.entity.OrderEntity;

public interface OrderRepo extends JpaRepository<OrderEntity, Integer>{

	List<OrderEntity> findAllByCustomerEntity(CustomerEntity customerEntity);
	
	@Query(value = "select oi.order_id, sum(i.price) as total\r\n"
			+ "from user_order o, item i, order_item oi\r\n"
			+ "where o.id = oi.order_id\r\n"
			+ "and i.id = oi.item_id\r\n"
			+ "group by oi.order_id\r\n"
			+ "order by total desc\r\n"
			+ "limit :limit",nativeQuery = true)
	int[] findTopOrders(@Param("limit") int limit);

	@Query(value = "select o from OrderEntity o")
	Page<OrderEntity> findOrdersWithPagination(PageRequest pageRequest);

}
