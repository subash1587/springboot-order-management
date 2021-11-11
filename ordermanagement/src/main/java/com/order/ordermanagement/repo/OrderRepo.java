package com.order.ordermanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.order.ordermanagement.entity.OrderEntity;

public interface OrderRepo extends JpaRepository<OrderEntity, Integer>{

}
