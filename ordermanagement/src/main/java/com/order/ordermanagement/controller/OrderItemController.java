package com.order.ordermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.order.ordermanagement.model.custom.ItemsPerOrderCount;
import com.order.ordermanagement.service.OrderItemService;

@RestController
public class OrderItemController {

	@Autowired
	OrderItemService orderItemService;
	
	@RequestMapping(path="order/itemcount", method=RequestMethod.GET)
	public ResponseEntity<?> getItemsPerOrderCount(){
		List<ItemsPerOrderCount> itemsPerOrderCountList = orderItemService.getItemsPerOrderCount();
		return ResponseEntity.ok(itemsPerOrderCountList);
	}
}
