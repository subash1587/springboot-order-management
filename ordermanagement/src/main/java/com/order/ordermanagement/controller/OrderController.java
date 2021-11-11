package com.order.ordermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.order.ordermanagement.model.OrderModel;
import com.order.ordermanagement.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping(path="/order", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> placeOrder(@RequestBody OrderModel orderModel) {
		orderService.addOrder(orderModel);
		return ResponseEntity.accepted().build();
		
	}
	
	@RequestMapping(path="/order",method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getAllOrder() {
		List<OrderModel> orderList = orderService.getAllOrder();
		return ResponseEntity.ok(orderList);
	}
	
	@RequestMapping(path="/order/{id}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> searchOrder(@PathVariable int orderId) {
		OrderModel orderModel = orderService.searchOrder(orderId);
		return ResponseEntity.ok(orderModel);
	}

}
