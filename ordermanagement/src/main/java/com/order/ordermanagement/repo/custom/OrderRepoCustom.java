package com.order.ordermanagement.repo.custom;

import java.util.List;
import java.util.Map;

import com.order.ordermanagement.entity.OrderEntity;

public interface OrderRepoCustom {

	List<OrderEntity> findOrders(Map<String, String> filters);
}
