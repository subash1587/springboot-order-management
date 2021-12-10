package com.order.ordermanagement.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.order.ordermanagement.entity.CustomerEntity;
import com.order.ordermanagement.entity.OrderEntity;
import com.order.ordermanagement.model.custom.OrdersWithTotalAmount;
import com.order.ordermanagement.repo.custom.OrderRepoCustom;

public interface OrderRepo extends JpaRepository<OrderEntity, Integer>, OrderRepoCustom{

	List<OrderEntity> findAllByCustomerEntity(CustomerEntity customerEntity);
	
	@Query(value = "select new com.order.ordermanagement.model.custom.OrdersWithTotalAmount(o.id, o.customerEntity, sum(i.price) as totalAmount) "
			+ "from OrderEntity o join OrderItemEntity oi on o.id = oi.order "
			+ "join ItemEntity i on oi.item = i.id group by o.id order by totalAmount")
	List<OrdersWithTotalAmount> sortOrdersByAmount();

	@Query(value = "select o from OrderEntity o")
	Page<OrderEntity> findOrdersWithPagination(PageRequest pageRequest);

}
