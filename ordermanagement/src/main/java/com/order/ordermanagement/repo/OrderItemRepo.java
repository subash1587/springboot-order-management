package com.order.ordermanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.ordermanagement.entity.OrderItemEntity;

public interface OrderItemRepo extends JpaRepository<OrderItemEntity, Integer>{

}
