package com.order.ordermanagement.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.order.ordermanagement.model.OrderModel;
import com.order.ordermanagement.model.custom.OrderStatus;
import com.order.ordermanagement.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping(path="/order", method=RequestMethod.POST)
	public ResponseEntity<?> placeOrder(@RequestBody OrderModel orderModel) {
		orderService.addOrder(orderModel);
		return ResponseEntity.accepted().build();
	}
	
	@RequestMapping(path="/order",method=RequestMethod.GET)
	public ResponseEntity<?> getOrders(@RequestBody(required=false) Map<String, String> filters, @RequestParam(name="page",required=false) Integer index) {
		return ResponseEntity.ok(orderService.getOrders(filters, index));
	}
	
	@RequestMapping(path="/order/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> getOrder(@PathVariable int id) {
		return ResponseEntity.ok(orderService.getOrder(id));
	}
	
	@RequestMapping(path="/customer/{id}/order", method=RequestMethod.GET)
	public ResponseEntity<?> getOrdersByCustomer(@PathVariable("id") int id){
		return ResponseEntity.ok(orderService.getOrdersByCustomer(id));
	}
	
	@RequestMapping(path="/order/sort", method=RequestMethod.GET)
	public ResponseEntity<?> sortOrders(@RequestParam(name="sort_by",required=false) String sortBy){
		return ResponseEntity.ok(orderService.sortOrdersByAmount());
	}
	
	@RequestMapping(path="/order/{id}", method=RequestMethod.PATCH)
	public ResponseEntity<?> updateOrder(@PathVariable int id, @RequestParam("status") OrderStatus status){
		orderService.updateOrder(id, status);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(path="/order/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteOrder(@PathVariable int id){
		orderService.deleteOrder(id);
		return ResponseEntity.ok().build();
	}
}
