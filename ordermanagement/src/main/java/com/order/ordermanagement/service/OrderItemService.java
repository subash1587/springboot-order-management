package com.order.ordermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.ordermanagement.model.custom.ItemsPerOrderCount;
import com.order.ordermanagement.repo.OrderItemRepo;

@Service
public class OrderItemService {

	@Autowired
	OrderItemRepo orderItemRepo;
	
	public List<ItemsPerOrderCount> getItemsPerOrderCount() {
		List<ItemsPerOrderCount> itemsPerOrderCount = orderItemRepo.findItemsCountPerOrder();
		return itemsPerOrderCount;
	}
}
